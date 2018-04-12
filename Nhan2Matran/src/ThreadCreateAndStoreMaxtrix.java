
public class ThreadCreateAndStoreMaxtrix extends Thread {
	private String name;
	private int row;
	private int col;
	public ThreadCreateAndStoreMaxtrix (String name, int row, int col) {
		this.name = name;
		this.row = row;
		this.col = col;
	}
	
	public void run() {
		int[][] a= new int[this.row][this.col];
		a = MatrixControl.randomMatrix(this.row, this.col);
		try {
			MatrixControl.storeMatrix(a, this.name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
