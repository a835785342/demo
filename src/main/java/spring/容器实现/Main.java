package spring.容器实现;

import org.dom4j.DocumentException;

public class Main {
    public static void main(String[] args) throws DocumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        Book book = ApplicationContextUtils.getBean("book", Book.class);
        System.out.println(book);

    }
}
