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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class HomeController {
	 @FXML
	    private Button getDetailsbtn;


    @FXML
    private Button new_entry;

    @FXML
    private Button delete_entry;

    @FXML
    private Button update_entry;

    @FXML
    private VBox homepage;

    @FXML
    private Button new_admin;

    @FXML
    private Button logoutBtn;
    
    @FXML
    private TextArea details;

    @FXML
    private TextField total;
   
    
    public HomeController() {
		// TODO Auto-generated constructor stub
    	
    	}
    @FXML
    void getDetails(ActionEvent event) {
    	 int count=0;
 		try 
 		{	    	
 			Class.forName("com.mysql.jdbc.Driver");  
 			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/covid","root","password");  
             Statement stmt = null;
             if(con!=null) {
             	String search = "select * from patientdata";
             	stmt=con.createStatement();
             	ResultSet resultSet=stmt.executeQuery(search);
             	String data="";
             	if(resultSet.next()) {
             		do {
                     	count++;
             			data=data+Integer.toString(count)+"\n";
                 		data=data+"ID: "+resultSet.getString("id")+"\n";
                 		data=data+"Fullname: "+resultSet.getString("fullname")+"\n";
                 		data=data+"Email: "+resultSet.getString("email")+"\n";
                 		data=data+"Age: "+resultSet.getString("age")+"\n";
                 		data=data+"Address: "+resultSet.getString("address")+"\n";
                 		data=data+"Ward Number: "+resultSet.getString("ward")+"\n";
                 		data=data+"Phone Number: "+resultSet.getString("phonenumber")+"\n";
                 		data=data+"Emergency Number: "+resultSet.getString("emergency")+"\n";
                 		data=data+"Quarantine Type: "+resultSet.getString("type")+"\n";
                 		data=data+"Temperature: "+resultSet.getString("temperature")+"\n";
                 		data=data+"Oxygen Level: "+resultSet.getString("oxygenlevel")+"\n";
                 		data=data+"Admitted Date: "+resultSet.getString("admindate")+"\n";
                 		data=data+"Discharged Date: "+resultSet.getString("dischargedate")+"\n";
                 		data=data+"Total Bill: "+resultSet.getString("totalbill")+"\n";
                 		data=data+"\n\n\n";
                 		details.setText(data);   
             		}while(resultSet.next());                	
             		total.setText(Integer.toString(count));
             	}else {
             		details.setText("No Reords Yet");
             	}

             	System.out.println("here3");
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
    void addNewAdmin(ActionEvent event) {
    	VBox pane;
		try {
			pane = (VBox)FXMLLoader.load(getClass().getResource("AddNewAdmin.fxml"));
			homepage.getChildren().setAll(pane);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }

    @FXML
    void addNewEntry(ActionEvent event) {
    	VBox pane;
		try {
			System.out.println("here");
			pane = (VBox)FXMLLoader.load(getClass().getResource("AddForm.fxml"));
			homepage.getChildren().setAll(pane);

			System.out.println("here1");
		} catch (IOException e) {

			System.out.println("her2");
			System.out.println(e.getMessage());
		}
    }

    @FXML
    void deleteEntry(ActionEvent event) {
    	VBox pane;
		try {
			pane = (VBox)FXMLLoader.load(getClass().getResource("DeleteEntry.fxml"));
			homepage.getChildren().setAll(pane);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }

    @FXML
    void updateEntry(ActionEvent event) {
    	VBox pane;
		try {
			pane = (VBox)FXMLLoader.load(getClass().getResource("AddForm.fxml"));
			homepage.getChildren().setAll(pane);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }
    @FXML
    void logout(ActionEvent event) {
    	VBox pane;
		try {
			pane = (VBox)FXMLLoader.load(getClass().getResource("Login.fxml"));
			homepage.getChildren().setAll(pane);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }

}
//insert into patientdata values("WEDNESDAYSahil","Sahil","30","Sahil@gmail.com","Baner","8","7412589630","9874563210","Hospitalized","120 deg","98","2020-10-26","2020-11-26","5000");