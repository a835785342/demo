package spring.容器实现;

import org.dom4j.DocumentException;

public class Main {
    public static void main(String[] args) throws DocumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ApplicationContextUtils applicationContext = new ApplicationContextUtils();
        Book book = applicationContext.getBean("book", Book.class);
        System.out.println(book);
    }
}
