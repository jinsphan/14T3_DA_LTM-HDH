
public class Common {
	public static void copyArray(int[][] a, int[][] b) {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				a[i][j] = b[i][j];
			}
		}
	}
}
