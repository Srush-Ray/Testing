package application;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			BorderPane root = new BorderPane();
//			 VBox vbox = new VBox(); 
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root,1000,1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			primaryStage.setX(primaryScreenBounds.getMinX());
			primaryStage.setY(primaryScreenBounds.getMinY());
			primaryStage.setWidth(primaryScreenBounds.getWidth());
	        primaryStage.setHeight(primaryScreenBounds.getHeight());
			primaryStage.show();	
			try 
			{	    	
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/covid","root","password");  
	            }catch(Exception e)
	            {
	         		Alert a1=new Alert(Alert.AlertType.ERROR);
	            	a1.setTitle("ERROR");
	            	a1.setContentText("Uable to not connect to database.");
	                a1.setTitle("DATABASE NOT CONNECTED!");
	                a1.setHeaderText(null);
	                a1.showAndWait();
	           	}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
