package application;

import java.awt.TextArea;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddFormController {
		@FXML
	    private VBox formpage;

	    @FXML
	    private TextField fullname;

	    @FXML
	    private TextField age;

	    @FXML
	    private TextField email;

	    @FXML
	    private TextField addressbox;

	    @FXML
	    private TextField ward;

	    @FXML
	    private TextField phonenumber;

	    @FXML
	    private TextField other;

	    @FXML
	    private TextField emergencynumber;

	    @FXML
	    private TextField temperature;

	    @FXML
	    private TextField oxygenlevel;

	    @FXML
	    private DatePicker admindate;

	    @FXML
	    private DatePicker dischargedate;

	    @FXML
	    private TextField billamount;

	    @FXML
	    private Button save;

	    @FXML
	    private Button clear;

	    @FXML
	    private Button back;

	    @FXML
	    void clearData(ActionEvent event) {
	    	fullname.setText("");
	    	age.setText("");
	    	email.setText("");
	    	ward.setText("");
	    	addressbox.setText("");
	    	phonenumber.setText("");
	    	emergencynumber.setText("");
	    	other.setText("");
	    	temperature.setText("");
	    	oxygenlevel.setText("");
	    	admindate.setValue(null);
	    	dischargedate.setValue(null);
	    	billamount.setText("");
	    }

	    @FXML
	    void saveData(ActionEvent event) {
	    		String id,fullnameString,emailString,ageString,wardString,addressString,phonenumberString,emergencyString,otherString,temperString,oxyString,adminString,dischargeString,billamountString;
	    		fullnameString=fullname.getText().toString().trim();
	    		emailString=email.getText().toString().trim();
	    		ageString=age.getText().toString().trim();
	    		wardString=ward.getText().toString().trim();
	    		addressString=addressbox.getText().toString().trim();
	    		phonenumberString=phonenumber.getText().toString().trim();
	    		emergencyString=emergencynumber.getText().toString().trim();
	    		otherString=other.getText().toString().trim();
	    		temperString=temperature.getText().toString().trim();
	    		oxyString=oxygenlevel.getText().toString().trim();
	    		adminString=admindate.getValue().toString().trim();
	    		dischargeString=dischargedate.getValue().toString().trim();
	    		billamountString=billamount.getText().toString().trim();
	    		
	    		if(fullnameString.isEmpty() || checkfornum(fullnameString)) {
	    			Alert a1=new Alert(Alert.AlertType.WARNING);
                	a1.setTitle("WARNING");
                    a1.setHeaderText("Fullname contains numbers or is empty!");
                    a1.showAndWait();
	    		}else if(emailString.isEmpty()) {
	    			Alert a1=new Alert(Alert.AlertType.WARNING);
                	a1.setTitle("WARNING");
                    a1.setHeaderText("Email is empty!");
                    a1.showAndWait();
	    		}else if(ageString.isEmpty() || checkforstr(ageString)) {
	    			Alert a1=new Alert(Alert.AlertType.WARNING);
                	a1.setTitle("WARNING");
                    a1.setHeaderText("Age is empty or contains characters. Enter only numbers!");
                    a1.showAndWait();
	    		}else if(wardString.isEmpty()) {
	    			Alert a1=new Alert(Alert.AlertType.WARNING);
                	a1.setTitle("WARNING");
                    a1.setHeaderText("Ward is empty!");
                    a1.showAndWait();
	    		}else if(addressString.isEmpty()) {
	    			Alert a1=new Alert(Alert.AlertType.WARNING);
                	a1.setTitle("WARNING");
                    a1.setHeaderText("Address is empty!");
                    a1.showAndWait();
	    		}else if(phonenumberString.isEmpty() || checkforstr(phonenumberString) || phonenumberString.length()!=10) {
	    			Alert a1=new Alert(Alert.AlertType.WARNING);
                	a1.setTitle("WARNING");
                    a1.setHeaderText("Phone Number is empty or contains characters. Enter only numbers! 10 digits expected");
                    a1.showAndWait();
	    		}else if(emergencyString.isEmpty() || checkforstr(emergencyString) || phonenumberString.length()!=10) {
	    			Alert a1=new Alert(Alert.AlertType.WARNING);
                	a1.setTitle("WARNING");
                    a1.setHeaderText("Emergency Number is empty or contains characters. Enter only numbers!");
                    a1.showAndWait();
	    		}else if(otherString.isEmpty()) {
	    			Alert a1=new Alert(Alert.AlertType.WARNING);
                	a1.setTitle("WARNING");
                    a1.setHeaderText("Type of Quarantine is empty!");
                    a1.showAndWait();
	    		}else if(temperString.isEmpty()) {
	    			Alert a1=new Alert(Alert.AlertType.WARNING);
                	a1.setTitle("WARNING");
                    a1.setHeaderText("Temperature is empty!");
                    a1.showAndWait();
	    		}else if(oxyString.isEmpty()) {
	    			Alert a1=new Alert(Alert.AlertType.WARNING);
                	a1.setTitle("WARNING");
                    a1.setHeaderText("Oxygen level is empty!");
                    a1.showAndWait();
	    		}else if(adminString.isEmpty()) {
	    			Alert a1=new Alert(Alert.AlertType.WARNING);
                	a1.setTitle("WARNING");
                    a1.setHeaderText("Date of admission is empty!");
                    a1.showAndWait();
	    		}else if(dischargeString.isEmpty()) {
	    			dischargeString="not discharged";
	    		}else if(billamountString.isEmpty()) {
	    			billamountString="--";
	    		}else {
	    			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
	    			LocalDateTime now = LocalDateTime.now();  
	    			id=now.getDayOfWeek()+fullnameString;
	    			System.out.println("yes");
	    			
	    			try 
	    			{	    	
	    				Class.forName("com.mysql.jdbc.Driver");  
	    				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/covid","root","password");  
	    	            
	    	            Statement stmt = null;
	    	            if(con!=null) {
	    	            	String query = "insert into patientdata values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	                        PreparedStatement st = con.prepareStatement(query);
	                        st.setString(1, id);
	                        st.setString(2, fullnameString);
	                        st.setString(3, ageString);
	                        st.setString(4, emailString);
	                        st.setString(5, addressString);
	                        st.setString(6, wardString);
	                        st.setString(7, phonenumberString);
	                        st.setString(8, emergencyString);
	                        st.setString(9, otherString);
	                        st.setString(10, temperString);
	                        st.setString(11, oxyString);
	                        st.setString(12, adminString);
	                        st.setString(13, dischargeString);
	                        st.setString(14, billamountString);
	                        int result= st.executeUpdate();
	    	            	if(result<=0) {
	    	            		Alert a1=new Alert(Alert.AlertType.ERROR);
	    	                	a1.setTitle("ERROR");
	    	                    a1.setContentText("Could not update try again!");
	    	                    a1.setHeaderText("Try again..");
	    	                    a1.showAndWait();
	    	            	}else {
	    	                		Alert a1=new Alert(Alert.AlertType.INFORMATION);
	    	                    	a1.setTitle("Done");
	    	                    	a1.setHeaderText("New Entry Added! PID:"+id);
	    	                        a1.showAndWait();
	    	                }
	    	            	st.close();
	    	            }	           
	    	            con.close();              
	    	            }catch(Exception e)
	    	            {
	    	         		Alert a1=new Alert(Alert.AlertType.ERROR);
	    	            	a1.setTitle("ERROR");
	    	            	a1.setContentText(e.getMessage());
	    	                a1.setTitle("DATABASE NOT CONNECTED!");
	    	                a1.setHeaderText(null);
	    	                a1.showAndWait();
	    	                System.out.println(e.getMessage());
	    	           	}		
	    		}
	    				
	    }
	    private boolean checkfornum(String str) {
	    	 char currentCharacter;
             boolean numberPresent = false;
             boolean upperCasePresent = false;
             boolean lowerCasePresent = false; 
             
	    	for (int i = 0; i < str.length(); i++) {
                 currentCharacter = str.charAt(i);
                 if (Character.isDigit(currentCharacter)) {
                     numberPresent = true;
                 } 
             }
	    	if(numberPresent) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }
	    private boolean checkforstr(String str) {
	    	 char currentCharacter;
            boolean numberPresent = false;
            boolean upperCasePresent = false;
            boolean lowerCasePresent = false; 
            
	    	for (int i = 0; i < str.length(); i++) {
                currentCharacter = str.charAt(i);
                if (Character.isUpperCase(currentCharacter)) {
                    upperCasePresent = true;
                } else if (Character.isLowerCase(currentCharacter)) {
                    lowerCasePresent = true;
                }
            }
	    	if(upperCasePresent || lowerCasePresent) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }
	    
	    @FXML
	    void goBack(ActionEvent event) {
	    	VBox pane;
			try {
				pane = (VBox)FXMLLoader.load(getClass().getResource("HomePage.fxml"));
				formpage.getChildren().setAll(pane);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
	    }
}
