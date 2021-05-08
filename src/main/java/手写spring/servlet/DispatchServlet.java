package 手写spring.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import 手写spring.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class DispatchServlet extends HttpServlet {

    //ioc容器
    private Map<String, Object> ioc = new HashMap<String, Object>();
    //配置文件内容
    private Properties configProperties = new Properties();
    //相关类集合
    private List<String> classList = new ArrayList<String>();
    //URL映射
    private Map<String, Method> urlMapping = new HashMap<String, Method>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            dispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception" + e.getMessage());
        }
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        String requestURI = req.getRequestURI();
        Method method = urlMapping.get(requestURI);
        if (null == method) {
            resp.getWriter().write("404 Not Found");
            return;
        }

        //构建动态方法入参
        Map<String, String[]> parameterMap = req.getParameterMap();
        Object[] parameters = new Object[parameterMap.size()];

        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        for (int i = 0; i < method.getParameters().length; i++) {
            String reqName = "";
            if(parameterAnnotations[i].length>0){
                for (int j = 0; j < parameterAnnotations[i].length; j++) {
                    Class<? extends Annotation> requestAnnotation = parameterAnnotations[i][j].annotationType();
                    if (requestAnnotation == RequestParam.class) {
                        reqName = ((RequestParam) parameterAnnotations[i][j]).value();

                    }
                }
            }else{
                reqName = method.getParameters()[i].getName();
            }
            String[] reqValue = parameterMap.getOrDefault(reqName, null);
            parameters[i] = Arrays.toString(reqValue).replaceAll("\\[|\\]", "")
                    .replaceAll("\\s+", ",");

        }


        String beanName = lowerClassCase(method.getDeclaringClass().getSimpleName());
        Object invokeClass = ioc.get(beanName);
        Object returnResult = method.invoke(invokeClass, parameters);
        if (method.getReturnType() == String.class) {
            resp.getWriter().write(String.valueOf(returnResult));
        }

    }

    @Override
    public void init(ServletConfig config) {

        //加载配置文件
        loadConfig(config.getInitParameter("contextConfigLocation"));

        //扫描相关的类
        scanClass(configProperties.getProperty("scan.package"));

        //将扫描到的类通过反射进行实例化放入容器中
        instance();

        //对容器中的实例属性进行DI操作
        autowired();

        //初始化HandlerMapping进行url与方法的关联映射
        urlMapping();

        System.out.println("SpringFramework is inited");
    }

    private void loadConfig(String contextConfigLocation) {

        try (
                InputStream configIs = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        ) {
            configProperties.load(configIs);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void scanClass(String directory) {

        URL url = this.getClass().getClassLoader().getResource("/" + directory.replaceAll("\\.", "/"));
        File classDirectory = new File(url.getFile());
        for (File file : classDirectory.listFiles()) {


            if (file.isDirectory()) {
                scanClass(directory + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String fileName = directory + "." + file.getName().replaceAll(".class", "");
                classList.add(fileName);
            }
        }


    }

    /**
     * 把类名第一个字母小写化
     *
     * @param name
     */
    private String lowerClassCase(String name) {
        char[] chars = name.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private void instance() {
        if (classList.isEmpty()) {
            return;
        }
        for (String className : classList) {
            try {
                Class<?> bean = Class.forName(className);
                if (bean.isAnnotationPresent(Controller.class)) {

                    String beanName = bean.getAnnotation(Controller.class).value();
                    if ("".equals(beanName.trim())) {
                        beanName = bean.getSimpleName();
                    }
                    beanName = lowerClassCase(beanName);
                    Object newBean = bean.newInstance();
                    ioc.put(beanName, newBean);
                } else if (bean.isAnnotationPresent(Service.class)) {
                    String beanName = bean.getAnnotation(Service.class).value();
                    if ("".equals(beanName.trim())) {
                        beanName = bean.getSimpleName();
                    }
                    beanName = lowerClassCase(beanName);

                    Object newBean = bean.newInstance();
                    ioc.put(beanName, newBean);

                    for (Class<?> i : bean.getInterfaces()) {
                        String iName = lowerClassCase(i.getSimpleName());
                        if (ioc.containsKey(iName)) {
                            throw new Exception("The " + i.getSimpleName() + "is exists!");
                        }
                        ioc.put(iName, newBean);
                    }
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void autowired() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> beanKV : ioc.entrySet()) {

            Object bean = beanKV.getValue();
            Class<?> beanClass = bean.getClass();

            for (Field declaredField : beanClass.getDeclaredFields()) {
                if (!declaredField.isAnnotationPresent(AutoWired.class)) {
                    continue;
                }
                String beanName = declaredField.getAnnotation(AutoWired.class).value().trim();
                if ("".equals(beanName)) {
                    beanName = lowerClassCase(declaredField.getType().getSimpleName());
                }
                declaredField.setAccessible(true);
                try {
                    declaredField.set(bean, ioc.getOrDefault(beanName, null));
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        }

    }

    private void urlMapping() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> beanKV : ioc.entrySet()) {
            Class<?> beanClass = beanKV.getValue().getClass();
            if (!beanClass.isAnnotationPresent(Controller.class)) {
                continue;
            }

            String baseUrl = "/" + beanClass.getAnnotation(RequestMapping.class).value();

            for (Method method : beanClass.getMethods()) {
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    String reqUrl = "/" + method.getAnnotation(RequestMapping.class).value();
                    String url = (baseUrl + reqUrl).replaceAll("/+", "/");
                    urlMapping.put(url, method);
                }
            }
        }


    }
}
