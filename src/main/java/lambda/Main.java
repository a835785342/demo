package lambda;

import lambda.Interface.MyInterface10;
import lambda.Interface.MyInterface7;
import lambda.Interface.MyInterface8;
import lambda.Interface.MyInterface9;

public class Main {
    public static void main(String[] args) {
        /**
         * 用 lambda 表达式实现 无参数无返回值的接口
         */
        MyInterface7 myInterface7 = () -> {
            System.out.println("用 lambda 表达式实现 无参数无返回值的接口");
        };

        /**
         * 用 lambda 表达式实现 无参数有返回值的接口
         * lambda 表达式的返回值类型和接口方法中的返回值类型一样
         */
        MyInterface8 myInterface8 = () -> {
            System.out.println("用 lambda 表达式实现 无参数有返回值的接口");
            return 0;
        };

        /**
         * 用 lambda 表达式实现 有参数无返回值的接口
         * 这里的 lambda 表达式函数中参数的个数和参数类型要与接口中方法的参数个数和参数类型一一对应
         */
        MyInterface9 myInterface9 = (int x, int y) -> {
            int sum = x + y;
            System.out.println("用 lambda 表达式实现 有参数无返回值的接口，sum的值为" + sum);
        };

        /**
         * 用 lambda 表达式实现 有参数有返回值的接口
         * 这里的 lambda 表达式函数中参数的个数和参数类型要与接口中方法的参数个数和参数类型一一对应
         * lambda 表达式的返回值类型和接口方法中的返回值类型一样
         */
        MyInterface10 myInterface10 = (int x, int y) -> {
            return x - y;
        };

        myInterface7.test();
        int result = myInterface8.test();
        myInterface9.test(1,2);
        int result2 = myInterface10.test(1,2);
    }
}
