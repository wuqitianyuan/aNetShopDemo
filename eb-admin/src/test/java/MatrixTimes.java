import java.util.Scanner;

public class MatrixTimes {

    public static int[][] times(int[][] a, int[][] b){
        int line = a.length;
        int row = b[0].length;
        int[][] result=new int[line][row];
        if(a.length != b[0].length){
            return result;
        }
        for(int i=0;i<a.length;i++){
            for(int j=0;j<b[0].length;j++){
                for(int k=0;k<b.length;k++){
                    result[i][j]+=a[i][k]*b[k][j];
                }
            }
        }
        return result;
    }

    public static int[][] inputMatrix(String name){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入矩阵" + name + "行数：");
        int linaA = scanner.nextInt();
        System.out.println("请输入矩阵" + name + "列数：");
        int rowA = scanner.nextInt();
        int[][] a= new int[linaA][rowA];
        System.out.println("请输入矩阵" + name + "：");
        for(int i=0;i<linaA;i++){
            for(int j=0;j<rowA;j++){
                a[i][j]=scanner.nextInt();
            }
        }
        //打印矩阵
        System.out.println("输入的矩阵" + name + "为：");
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                System.out.printf("%d" ,a[i][j]);
                if(j==a[0].length-1){
                    System.out.println();
                }else{
                    System.out.print(" ");
                }
            }
        }
        return a;
    }
    public static void main(String[] args) {
        int[][] a =  inputMatrix("A");
        int[][] b =  inputMatrix("B");
        if(a.length != b[0].length){
            System.out.println("矩阵行数与列数不一致无法相乘！");
        }
        int[][] c= times(a,b);
        System.out.println("矩阵AXB的结果为：");
        for(int i=0;i<c.length;i++){
            for(int j=0;j<c[0].length;j++){
                System.out.printf("%d" ,c[i][j]);
                if(j==c[0].length-1){
                    System.out.println();
                }else{
                    System.out.print(" ");
                }
            }
        }
    }
}
