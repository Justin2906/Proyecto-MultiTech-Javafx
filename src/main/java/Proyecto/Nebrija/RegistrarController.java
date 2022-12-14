package Proyecto.Nebrija;

import java.io.IOException;
import Modelo.ConexionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrarController {

	@FXML
	private PasswordField txtContrasena2;

	@FXML
	private TextField txtNombre;

	@FXML
	private Label correoBdExiste;

	@FXML
	private PasswordField txtContrasena;

	@FXML
	private TextField txtCorreo2;

	@FXML
	private Label contrasenasBien;

	@FXML
	private Label emailsBien;

	@FXML
	private Button btnRegistrar;

	@FXML
	private Label nombreBien;

	@FXML
	private TextField txtCorreo;

	@FXML
	private Button btnEntrar;

	@FXML
	void registrarse(ActionEvent event) throws IOException {
		ConexionBD conexionBD = new ConexionBD();

		if (txtNombre.getText().isEmpty()) {
			nombreBien.setVisible(true);
		} else if (!txtCorreo.getText().equals(txtCorreo2.getText())) {
			emailsBien.setVisible(true);
		} else if (!txtContrasena.getText().equals(txtContrasena2.getText())) {
			contrasenasBien.setVisible(true);
		} else if (conexionBD.consultarEmailBd(txtCorreo.getText()) == true) {
			correoBdExiste.setVisible(true);
		} else {
			conexionBD.insertarRegistro(txtNombre.getText().toLowerCase(), txtCorreo.getText().toLowerCase(),
					txtContrasena.getText().toLowerCase());
			App.setRoot("login");
		}

	}

	@FXML
	void logearse(ActionEvent event) throws IOException {
		App.setRoot("login");
	}

	private void mostrarAlertInfo(ActionEvent event, String texto) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("ALERTA!!");
		alert.setContentText(texto);
		alert.showAndWait();
	}

}
