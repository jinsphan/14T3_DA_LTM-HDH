import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
//import javafx.application.*;
//import javafx.scene.*;
//import javafx.stage.*;

public class Chart extends Application{
	@Override public void start(Stage stage) throws Exception {
		stage.setTitle("Multiple Matrix");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Multiple Matrix");
        xAxis.setLabel("Matrix");       
        yAxis.setLabel("Time (ms)");
 
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        series1.setName("Time with Thread"); 
        series2.setName("Time without Thread");

        int[][] arLogtime = MatrixControl.getMatrixByFileName(Main.file_log);
        for (int i = 0; i < arLogtime.length; i++) {
        	String nameX = i + ": ["+ arLogtime[i][0] + "," + arLogtime[i][1] + "]x[" + arLogtime[i][2] + "," + arLogtime[i][3] + "]";
        	series1.getData().add(new XYChart.Data(nameX, arLogtime[i][4]));
        	series2.getData().add(new XYChart.Data(nameX, arLogtime[i][5]));
        }
        
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1, series2);
        stage.setScene(scene);
        stage.show();
       
    }

	public static void show() {
		launch();
	}
	
	public static void main(String[] arg) {
		launch(arg);
	}

}
