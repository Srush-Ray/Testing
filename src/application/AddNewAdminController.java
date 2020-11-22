package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddNewAdminController {

    @FXML
    private VBox addAdminPage;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button addNewAdminBtn;

    @FXML
    private Button back;

    @FXML
    void addNewAdmin(ActionEvent event) {
    	String name = username.getText().toString();
    	String pass = password.getText().toString();
    	try 
		{	    	
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/covid","root","password");  
            
            Statement stmt = null;
            if(con!=null) {
//            	String create = "CREATE TABLE IF NOT EXISTS AdminDetails (\n"
//                        + "	Username text PRIMARY KEY,\n"
//                        + "	Password text NOT NULL\n"
//                        + ");";            	
//               	stmt = con.createStatement();
//                stmt.execute(create);
            	if(name.isEmpty()) {
            		Alert a1=new Alert(Alert.AlertType.WARNING);
                	a1.setTitle("ERROR");
                    a1.setHeaderText("Enter Username!");
                    a1.showAndWait();
            	}else if(pass.isEmpty()){
            		Alert a1=new Alert(Alert.AlertType.WARNING);
                	a1.setTitle("ERROR");
//                    a1.setContentText("!!");
                    a1.setHeaderText("Enter Password!");
                    a1.showAndWait();
            	}
            	String search = "SELECT * FROM loginDetails WHERE username = '"+name+"';";
            	stmt=con.createStatement();
            	ResultSet resultSet=stmt.executeQuery(search);
            	if(resultSet.next()) {
            		Alert a1=new Alert(Alert.AlertType.ERROR);
                	a1.setTitle("ERROR");
//                    a1.setContentText("Username Already Exist!");
                    a1.setHeaderText("Username Already Exist");
                    a1.showAndWait();
            	}else {
            		String add = "INSERT INTO loginDetails VALUES ('"+name+"','"+pass+"');";
            		int resultAdd=stmt.executeUpdate(add);
                	if(resultAdd>0) {
                		Alert a1=new Alert(Alert.AlertType.INFORMATION);
                    	a1.setTitle("Admin Added");
                    	 a1.setHeaderText("New Admin Added!");
                        a1.showAndWait();
                	}
            	}
                }else {
            	Alert a1=new Alert(Alert.AlertType.ERROR);
            	a1.setTitle("ERROR");
                a1.setContentText("Could not establish connection with database.");
                a1.setHeaderText("Connection Lost!");
                a1.showAndWait();
            }	           
            stmt.close();
            con.close();              
            }catch(Exception e)
            {
         		Alert a1=new Alert(Alert.AlertType.ERROR);
            	a1.setTitle("ERROR");
            	a1.setContentText("Uable to not connect to database.");
                a1.setTitle("DATABASE NOT CONNECTED!");
                a1.setHeaderText(null);
                a1.showAndWait();
                System.out.println(e.getMessage());
           	}		
    }


    @FXML
    void goBack(ActionEvent event) {
    	VBox pane;
		try {
			pane = (VBox)FXMLLoader.load(getClass().getResource("HomePage.fxml"));
			addAdminPage.getChildren().setAll(pane);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }

}
