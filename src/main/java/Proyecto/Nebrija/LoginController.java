package Proyecto.Nebrija;

import java.io.IOException;

import Modelo.ConexionBD;
import Modelo.DatosUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

	public static DatosUsuario usuarioLogueado;

	@FXML
	void registrarUusuario(ActionEvent event) throws IOException {
		App.setRoot("registrarse");
	}

	@FXML
	void logearse(ActionEvent event) throws IOException {
		ConexionBD conexionBD = new ConexionBD();

		if (txtEmail.getText().isEmpty() || txtContrasena.getText().isEmpty()) {
			mostrarAlertInfo(event, "debe rellenar todos los campos");
		}else if (conexionBD.consultarEmailBd(txtEmail.getText()) == false) {
			emailIncorrecto.setVisible(true);
		} else if (conexionBD.consultarContrasenaBd(txtEmail.getText(), txtContrasena.getText()) == false) {
			pswdIncorrecta.setVisible(true);

		} else {
			usuarioLogueado = new DatosUsuario(conexionBD.consultarIdUsuario(txtEmail.getText()), txtEmail.getText(),
					txtContrasena.getText());
			App.setRoot("inicio");
		}
	}
	
	private void mostrarAlertInfo(ActionEvent event, String texto) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("ALERTA!!");
		alert.setContentText(texto);
		alert.showAndWait();
	}


}
