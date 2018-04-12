import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener {
	GridBagLayout gbLayout;
	GridBagConstraints gbConstraints;
	
	// Variable
	private String file_A, file_B;
	public static final String file_log = "logtime.txt", file_Rs = "rs.txt";
	private int row_A, row_B, col_A, col_B;
	public static int[][] matrix_a, matrix_b, matrix_rs;
	
	//Components
	JLabel lb_status = new JLabel();
	JLabel lb_message = new JLabel();
	
	JTextField tf_nhapFileA = new JTextField("a.txt");
	JTextField tf_nhapFileB = new JTextField("b.txt");
	JTextField tf_nhapRowA = new JTextField(5);
	JTextField tf_nhapColA = new JTextField(5);
	JTextField tf_nhapRowB = new JTextField(5);
	JTextField tf_nhapColB = new JTextField(5);
	
	JButton btn_random = new JButton("Random Matrix");
	JButton btn_mul = new JButton("A x B");
	JButton btn_show_chart = new JButton("Show Chart");
	
	public Main() {
		gbLayout = new GridBagLayout();
		gbConstraints = new GridBagConstraints();
		setLayout(gbLayout);
		
		// Set theme for component
		lb_status.setForeground(Color.GREEN);
		btn_mul.setVisible(false);
		btn_show_chart.setVisible(false);
		
		// Add component at here
		// row 0
		this.addComponent(new Label("File A:"), 0, 0, 1, 1);
		this.addComponent(tf_nhapFileA, 0, 1, 1, 2);
		this.addComponent(new Label("File B:"), 0, 3, 1, 1);
		this.addComponent(tf_nhapFileB, 0, 4, 1, 2);
		// row 1
		this.addComponent(new Label("Rows, Cols:"), 1, 0, 1, 1);
		this.addComponent(tf_nhapRowA, 1, 1, 1, 1);
		this.addComponent(tf_nhapColA, 1, 2, 1, 1);
		this.addComponent(new Label("Rows, Cols:"), 1, 3, 1, 1);
		this.addComponent(tf_nhapRowB, 1, 4, 1, 1);
		this.addComponent(tf_nhapColB, 1, 5, 1, 1);
		// row 2
		this.addComponent(btn_random, 2, 0, 1, 1);
		this.addComponent(btn_mul, 2, 1, 1, 1);
		this.addComponent(btn_show_chart, 2, 2, 1, 2);
		// row 3
		this.addComponent(new Label("Status:"), 3, 0, 1, 1);
		this.addComponent(lb_status, 3, 1, 1, 2);
		// row 4
		this.addComponent(new Label("Message:"), 4, 0, 1, 1);
		this.addComponent(lb_message, 4, 1, 1, 5);
		//Add Event Listener at here
		btn_random.addActionListener(this);
		btn_mul.addActionListener(this);
		btn_show_chart.addActionListener(this);
		// INIT UI
		setBounds(200,200,800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void addComponent(Component c, int row, int col, int nrow, int ncol)
	{
		gbConstraints.gridx=col;
		gbConstraints.gridy=row;
	
		gbConstraints.gridwidth=ncol;
		gbConstraints.gridheight=nrow;
		
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.ipadx = 5;
		gbConstraints.ipady = 5;
		gbConstraints.insets = new Insets(10,10,10,10);
		gbConstraints.anchor = GridBagConstraints.LINE_END;
		
		gbLayout.setConstraints(c, gbConstraints);
		add(c);
	}
	
	public void setStatus(int status, String message) {
		if (status == 1) {
			lb_status.setText("SUCCESSFUL");
			lb_status.setForeground(Color.GREEN);
		} else {
			lb_status.setText("ERROR");
			lb_status.setForeground(Color.RED);
		}
		lb_message.setText(message);
	}
	
	public void validateFileName (String file) throws Exception {
		String[] file_split = file.split(Pattern.quote("."));
		if (file_split.length > 1 && !file_split[0].equals("") && file_split[file_split.length - 1].equals("txt")) {
		} else {
			throw new Exception("File \"" + file + "\" is not format. Ex: abc.txt");
		}
	}
	
	public void validateNumRowCol (int row1, int row2, int col1, int col2) throws Exception{
		int max = 1500;
		if ((row1 == 0 || row2 == 0 || col1 == 0 || col2 == 0) || (col1 != row2)) {
			 throw new Exception("Rows and Cols is not fomat");
		} else if (row1 > max || row2 > max || col1 > max || col2 > max) {
			throw new Exception("Rows and Cols must be between 1 and " + max);
		}
	}
	
	public void saveMatrixAandB() throws Exception {
		ThreadCreateAndStoreMaxtrix threadA = new ThreadCreateAndStoreMaxtrix(this.file_A, this.row_A, this.col_A);
        ThreadCreateAndStoreMaxtrix threadB = new ThreadCreateAndStoreMaxtrix(this.file_B, this.row_B, this.col_B);
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// Information Variable
		String file_A, file_B;
		int row_A, row_B, col_A, col_B;
		
		if (obj == btn_random) {
			try {
				file_A = tf_nhapFileA.getText();
				file_B = tf_nhapFileB.getText();
				validateFileName(file_A);
				validateFileName(file_B);
				
				row_A = Integer.parseInt(tf_nhapRowA.getText());
				row_B = Integer.parseInt(tf_nhapRowB.getText());
				col_A = Integer.parseInt(tf_nhapColA.getText());
				col_B = Integer.parseInt(tf_nhapColB.getText());
				validateNumRowCol(row_A, row_B, col_A, col_B);
				
				this.file_A = file_A;
				this.file_B = file_B;
				this.row_A = row_A;
				this.row_B = row_B;
				this.col_A = col_A;
				this.col_B = col_B;
				
				saveMatrixAandB();
				setStatus(1, "Ready to start");
				btn_mul.setVisible(true);
				btn_show_chart.setVisible(false);
			} catch (Exception e1) {
				btn_mul.setVisible(false);
				btn_show_chart.setVisible(false);
				setStatus(0, e1.getMessage());
			}
		}
		
		if (obj == btn_mul) {
			try {
				matrix_a = MatrixControl.getMatrixByFileName(this.file_A);
				matrix_b = MatrixControl.getMatrixByFileName(this.file_B);
				matrix_rs= new int [this.row_A][this.col_B];
				long t1, t2;
				int time_thread = 0, time_not_thread = 0;
				
				// Running using thread
				t1 = System.currentTimeMillis();
				SubThreadMulMatrix[] sub = new SubThreadMulMatrix[4];
		        for (int i = 0; i < 4; i++) {
		        	sub[i] = new SubThreadMulMatrix(i);
		        	sub[i].start();
		        }
		        for (int i = 0; i < 4; i++) {
		        	sub[i].join();
		        }
		        t2 = System.currentTimeMillis();
		        time_thread = (int)(t2 - t1);
		        
		        // Running without using thread
		        t1 = System.currentTimeMillis();
				for (int i = 0; i < this.row_A; i++) {
					for (int j = 0; j < this.col_B; j++ ) {
						int s = 0;
						for (int k = 0; k < this.col_A; k++) {
							s += matrix_a[i][k]*matrix_b[k][j];
						}
						matrix_rs[i][j] = s;
					}
				}
				t2 = System.currentTimeMillis();
				time_not_thread = (int)(t2 - t1);
		        
				int [][] log_time_matrix = null;
				log_time_matrix = MatrixControl.getMatrixByFileName(Main.file_log);
				if (log_time_matrix.length == 0) {
					log_time_matrix  = new int[1][6];
				} else {
					int [][] tmp_log_time = log_time_matrix;
					log_time_matrix = new int[tmp_log_time.length + 1][6];
					Common.copyArray(log_time_matrix, tmp_log_time);
				}
				
				log_time_matrix[log_time_matrix.length - 1][0] = this.row_A;
				log_time_matrix[log_time_matrix.length - 1][1] = this.col_A;
				log_time_matrix[log_time_matrix.length - 1][2] = this.row_B;
				log_time_matrix[log_time_matrix.length - 1][3] = this.col_B;
				log_time_matrix[log_time_matrix.length - 1][4] = time_thread;
				log_time_matrix[log_time_matrix.length - 1][5] = time_not_thread;
				
		        MatrixControl.storeMatrix(matrix_rs, Main.file_Rs);
		        MatrixControl.storeMatrix(log_time_matrix, Main.file_log);
		        setStatus(1, "Time running with has_Thread/not_Thread: " + time_thread + "/" + time_not_thread);
		        btn_show_chart.setVisible(true);
			} catch (Exception e1) {
				btn_show_chart.setVisible(false);
				setStatus(0, e1.getMessage());
			}
		}
		
		if (obj == btn_show_chart) {
			this.setVisible(false);
			Chart.show();
		}
	}
	
	public static void main(String[] args) {
		Main main = new Main();

	}
}
