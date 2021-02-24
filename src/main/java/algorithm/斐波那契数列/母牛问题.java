package algorithm.斐波那契数列;

public class 母牛问题 {
    // 递归
    public static int f(int n) {

        if (n <= 1) {
            return 1;
        }

        if (n <= 2) {
            return 2;
        }

        if (n <= 3) {
            return 3;
        }

        return f(n - 1) + f(n - 3);
    }

    public static void main(String[] args) {
        int year = 5;

        System.out.println(f(year));
    }
}
