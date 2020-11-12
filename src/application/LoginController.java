package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    void login(ActionEvent event) {
    	String name = username.getText().toString();
    	String pass = password.getText().toString();
    	System.out.println(name+" "+pass);
    }

}
