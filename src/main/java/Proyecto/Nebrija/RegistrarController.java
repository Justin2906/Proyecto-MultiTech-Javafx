package Proyecto.Nebrija;


import java.io.IOException;

import Modelo.ConexionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrarController {

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private PasswordField txtContrasena2;

    @FXML
    private TextField txtNombre;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private TextField txtCorreo2;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private TextField txtCorreo;

    @FXML
    void iniciarSesion(ActionEvent event) throws IOException {
    	App.setRoot("login");
    }


    @FXML
    void registrarse(ActionEvent event) throws IOException {	
    	ConexionBD conexionBD = new ConexionBD();
    	
    
		conexionBD.insertarRegistro(txtNombre.getText(), txtCorreo.getText(), txtContrasena.getText());
		
		App.setRoot("login");
		
    }

}


