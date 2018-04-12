
public class SubThreadMulMatrix extends Thread {
	private int part;
	public SubThreadMulMatrix(int part) {
		this.part = part;
	}
	
	public void run() {
		int m = Main.matrix_a.length;
		int n = Main.matrix_b[0].length;
		int start = (this.part*m)/4;
		int end = start + m/4;
		if (this.part == 3) end = m;
		
		for (int i = start; i < end; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < Main.matrix_b.length; l++) {
                	Main.matrix_rs[i][j] = Main.matrix_rs[i][j] + Main.matrix_a[i][l] * Main.matrix_b[l][j];
                }

            }
        }
	}
}
