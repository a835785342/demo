package spring.容器实现;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;
import java.util.List;

public class ApplicationContextUtils {

    private Document document;

    public ApplicationContextUtils() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        this.document = saxReader.read(getClass().getResourceAsStream("/books.xml"));
    }

    public <T> T getBean(String id, Class<T> tc) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Element rootElement = document.getRootElement();

        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            String myId = element.attributeValue("id");
            String className = element.attributeValue("class");

            if (myId.equals(id)) {
                Class c = Class.forName(className);
                if (c == tc) {
                    T object = tc.newInstance();
                    List<Element> props = element.elements();
                    for (Element prop : props) {
                        String name = prop.attributeValue("name");
                        String value = prop.attributeValue("value");
                        setAttribute(tc.getDeclaredFields(), object, name, value);
                    }
                    return object;
                }
            }
        }
        return null;
    }

    private <T> void setAttribute(Field[] declaredFields, T object, String name, String value) throws IllegalAccessException {
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (name.equals(field.getName())) {
                Class type = field.getType();
                if (type == Integer.class||type==int.class) {
                    field.set(object, Integer.valueOf(value));
                } else if (type == double.class || type == Double.class) {
                    field.set(object, Double.valueOf(value));
                } else if (type == String.class) {
                    field.set(object, value);
                }
            }
        }
    }
}
