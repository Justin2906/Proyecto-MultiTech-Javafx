package Proyecto.Nebrija;

import java.io.IOException;
import Modelo.ConexionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrarController {

	@FXML
	private PasswordField txtContrasena2;

	@FXML
	private TextField txtNombre;

	@FXML
	private PasswordField txtContrasena;

	@FXML
	private TextField txtCorreo2;

	@FXML
	private Button btnRegistrar;

	@FXML
	private TextField txtCorreo;

	@FXML
	void iniciarSesion(ActionEvent event) throws IOException {
		App.setRoot("login");
	}

	@FXML
	void registrarse(ActionEvent event) throws IOException {
		ConexionBD conexionBD = new ConexionBD();

		if (conexionBD.consultarDatosBd(txtCorreo.getText()) == true) {
			mostrarAlertInfo(event, "El correo que esta introduciendo ya existe");
		} else if (!txtCorreo.getText().equals(txtCorreo2.getText())) {
			mostrarAlertInfo(event, "Los correos deben coincidir");
		} else if (!txtContrasena.getText().equals(txtContrasena2.getText())) {
			mostrarAlertInfo(event, "Las contraseñas deben coincidir");
		} else {
			conexionBD.insertarRegistro(txtNombre.getText().toLowerCase(), txtCorreo.getText().toLowerCase(),
					txtContrasena.getText().toLowerCase());
			App.setRoot("login");
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
