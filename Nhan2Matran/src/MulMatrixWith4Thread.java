import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author ahmed
 */
public class MulMatrixWith4Thread {

    public static int[][] c;
    public static int[][] a;
    public static int[][] b;
    
    public static class SubThread extends Thread {
    	private int part;
    	public SubThread(int part) {
    		this.part = part;
    	}
    	
    	public void run() {
    		int m = a.length;
    		int n = b[0].length;
    		int start = (this.part*m)/4;
    		int end = start + m/4;
    		if (this.part == 3) end = m;
    		
    		for (int i = start; i < end; i++) {
                for (int j = 0; j < n; j++) {
                    for (int l = 0; l < b.length; l++) {
                        c[i][j] = c[i][j] + a[i][l] * b[l][j];
                    }

                }
            }
    	}
    }

    public static void main(String[] args) throws Exception {
        long t1 = System.currentTimeMillis();
        ThreadCreateAndStoreMaxtrix threadA = new ThreadCreateAndStoreMaxtrix("a.txt", 10, 3);
        ThreadCreateAndStoreMaxtrix threadB = new ThreadCreateAndStoreMaxtrix("b.txt", 3, 15);
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        
        a = MatrixControl.getMatrixByFileName("a.txt");
        b = MatrixControl.getMatrixByFileName("b.txt");
        c= new int [a.length][b[0].length];
        
        SubThread[] sub = new SubThread[4];
        for (int i = 0; i < 4; i++) {
        	sub[i] = new SubThread(i);
        	sub[i].start();
        }
        
//        int m = a.length;
//        int n = b[0].length;
//        int e = a[0].length;
//		for (int i = 0; i < m; i++) {
//			for (int j = 0; j < n; j++ ) {
//				int s = 0;
//				for (int k = 0; k < e; k++) {
//					s += a[i][k]*b[k][j];
//				}
//				c[i][j] = s;
//			}
//		}
		
        MatrixControl.storeMatrix(c, "results.txt");
		long t2 = System.currentTimeMillis();
		System.out.println("the time is "+(t2-t1));
    }
}