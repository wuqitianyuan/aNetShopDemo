import java.util.Scanner;

public class MatrixTrans {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m, n;
        int i, j;

        System.out.println("请输入矩阵M*N中的M值");
        m = in.nextInt();
        System.out.println("请输入矩阵M*N中的N值");
        n = in.nextInt();

        int[][] a = new int[m][n];      //存储转置前的矩阵
        int[][] b = new int[n][m];      //存储转置后的矩阵

        System.out.println("请输入" + m + " * " + n + "矩阵中的所有元素");
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
                System.out.println("a[" +i + "][" + j + "]=" + a[i][j]);
            }
        }

        //矩阵转置
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                b[j][i] = a[i][j];
            }
        }

        System.out.println("转置前");
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                System.out.printf("%6d", a[i][j]);
            }
            System.out.println();
        }

        System.out.println("转置后");
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                System.out.printf("%6d", b[i][j]);
            }
            System.out.println();
        }
    }
}
