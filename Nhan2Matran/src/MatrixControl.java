import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class MatrixControl {
	public static int[][] getMatrixByFileName(String filename) throws Exception {
        Scanner s = new Scanner(new FileInputStream(filename));
        int rows = s.nextInt();
        int cols = s.nextInt();
        int mat[][] = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = s.nextInt();
            }

        }
        return mat;
    }
	
	public static int[][] randomMatrix(int m, int n) {
    	int[][] matrixRandom = new int[m][n];
    	Random rand = new Random();
    	for (int i = 0; i < m ; i++) {
    		for (int j = 0; j < n ; j++) {
    			matrixRandom[i][j] = rand.nextInt(10);
    		}
    	}
    	return matrixRandom;
    }
	
	public static void storeMatrix(int c[][], String filename) throws Exception {
        int rows = c.length;
        int cols = c[0].length;
        PrintWriter pw = new PrintWriter(new FileOutputStream(filename));
        pw.println(rows + "      " + cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                pw.print(c[i][j] + "    ");

            }
            pw.println();
        }
        pw.close();
    }
	
	public static void showMatrix(int [][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
        	for (int j = 0; j < matrix[0].length; j++) {
        		System.out.print(matrix[i][j] + " ");
        	}
        	System.out.println();
        }
	}

}
