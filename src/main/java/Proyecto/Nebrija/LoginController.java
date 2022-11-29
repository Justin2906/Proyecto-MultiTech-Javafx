package Proyecto.Nebrija;

import java.io.IOException;

import Modelo.ConexionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginController {

	@FXML
	private Label pswdIncorrecta;

	@FXML
	private PasswordField txtContrasena;

	@FXML
	private TextField txtEmail;

	@FXML
	private Label emailIncorrecto;

	@FXML
	private ImageView imgViewFondo;

	@FXML
	private Button btnRegistrarse;

	@FXML
	private Button btnEntrar;

	@FXML
	void registrarUusuario(ActionEvent event) throws IOException {
		App.setRoot("registrarse");
	}

	@FXML
	void logearse(ActionEvent event) throws IOException {
		ConexionBD conexionBD = new ConexionBD();

		if (conexionBD.consultarEmailBd(txtEmail.getText()) == false) {
			emailIncorrecto.setVisible(true);
		} else if (conexionBD.consultarContrasenaBd(txtEmail.getText(), txtContrasena.getText()) == false) {
			pswdIncorrecta.setVisible(true);
		} else {
			App.setRoot("inicio");
		}

	}

}
