package algorithm.斐波那契数列;

public class Fbo {

    // 递归
    public static int f(int n){
        if(n <= 0){
            return 0;
        }

        if(n <= 2){
            return 1;
        }

        return f(n - 1) + f(n - 2);
    }

    // 动态规划
    public static int dp(int n){
        if(n <= 0){
            return 0;
        }

        if(n <= 2){
            return 1;
        }

        int one = 1;
        int two = 1;
        int cur = 0;
        for (int i = 2; i < n; i++) {
            cur = one + two;
            one = two;
            two = cur;
        }
        return cur;
    }

    // 快速幂方法
    // 有线性代数德 => |Fnfn-1| = |F2F1|*(矩阵)^n-k(k=2)
    public static int quickM(int n){
        if(n <= 0){
            return 0;
        }
        if(n <= 2){
            return 1;
        }
        int[][] matrix = new int[][]{{1,1},{1,0}};
        int[][] ints = matrixPower(matrix, n - 2);

        return ints[0][0] + ints[1][0];

    }

    // 求矩阵的n次幂
    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }

        // res = 矩阵中的1
        int[][] tmp = m;// 矩阵1次方
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
        }
        return res;
    }

    // 两个矩阵相乘
    public static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int year = 20;

        System.out.println(f(year));
        System.out.println(dp(year));
        System.out.println(quickM(year));
    }
}

