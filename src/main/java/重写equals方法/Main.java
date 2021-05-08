package 重写equals方法;

public class Main {

    public static void main(String[] args) {
        Student s1 = new Student("张三","1");
        Student s2 = new Student("张三","1");

        System.out.println(s1==s2);
        System.out.println(s1.equals(new String()));
    }


}
