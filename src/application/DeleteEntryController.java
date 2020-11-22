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

public class DeleteEntryController {
	  @FXML
	    private VBox deletepage;

	    @FXML
	    private TextField pid;

	    @FXML
	    private Button getDetailsBtn;

	    @FXML
	    private TextArea details;

	    @FXML
	    private Button deletebtn;

	    @FXML
	    private Button back;

	    @FXML
	    void deletefunct(ActionEvent event) {
	    	
				String pidString;
		    	pidString=pid.getText().toString().trim();
		    	if(pidString.isEmpty()) {
		    		Alert a1=new Alert(Alert.AlertType.WARNING);
	            	a1.setTitle("ERROR");
	                a1.setHeaderText("Id is empty!");
	                a1.showAndWait();
		    	}else {
		    		try 
		    		{	    	
		    			Class.forName("com.mysql.jdbc.Driver");  
		    			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/covid","root","password");  
		                Statement stmt = null;
		                if(con!=null) {
		                	String search = "SELECT * FROM patientdata WHERE id ='"+pidString+"';";
		                	System.out.println(search);
		                	stmt=con.createStatement();
		                	ResultSet resultSet=stmt.executeQuery(search);
		                	if(resultSet.next()) {
		                		String drop="Delete from patientdata where id='"+pidString+"';";
		            			int result=stmt.executeUpdate(drop);
		            				if(result>0) {
		            					Alert a=new Alert(Alert.AlertType.INFORMATION);
			            				a.setTitle("Patient DELETED");
			            				a.setContentText("Record is deleted.");
			            				a.setHeaderText(null);
			            				a.showAndWait();
		            				}
		            			
		                	}else {
		                    		Alert a1=new Alert(Alert.AlertType.INFORMATION);
		                        	a1.setTitle("Error");
		                        	a1.setHeaderText("No data found!");
		                            a1.showAndWait();
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


	    }

	    @FXML
	    void getDetails(ActionEvent event) {
	    	String pidString;
	    	pidString=pid.getText().toString().trim();
	    	if(pidString.isEmpty()) {
	    		Alert a1=new Alert(Alert.AlertType.WARNING);
            	a1.setTitle("ERROR");
                a1.setHeaderText("Id is empty!");
                a1.showAndWait();
	    	}else {
	    		try 
	    		{	    	
	    			Class.forName("com.mysql.jdbc.Driver");  
	    			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/covid","root","password");  
	                Statement stmt = null;
	                if(con!=null) {
	                	String search = "SELECT * FROM patientdata WHERE id ='"+pidString+"';";
	                	stmt=con.createStatement();
	                	ResultSet resultSet=stmt.executeQuery(search);
	                	if(resultSet.next()) {
	                		String data;
	                		data="ID: "+resultSet.getString("id")+"\n";
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
	                		details.setText(data);
	                	}else {
	                    		Alert a1=new Alert(Alert.AlertType.INFORMATION);
	                        	a1.setTitle("Error");
	                        	a1.setHeaderText("No data found!");
	                            a1.showAndWait();
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

	    }

    @FXML
    void goBack(ActionEvent event) {
    	VBox pane;
		try {
			pane = (VBox)FXMLLoader.load(getClass().getResource("HomePage.fxml"));
			deletepage.getChildren().setAll(pane);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }

}
