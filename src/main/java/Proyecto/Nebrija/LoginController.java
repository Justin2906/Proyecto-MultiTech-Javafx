package Proyecto.Nebrija;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private TextField txtEmail;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private Button btnEntrar;

    

    @FXML
    void registrarUsuario(ActionEvent event) throws IOException {
    	App.setRoot("registrarse");
    }

    @FXML
    void logearse(ActionEvent event) throws IOException {
    	App.setRoot("inicio");
    }

}




