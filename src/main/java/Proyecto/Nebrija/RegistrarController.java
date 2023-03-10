package Proyecto.Nebrija;

import java.io.IOException;
import Modelo.ConexionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	private ComboBox<String> boxTipoCliente;

	@FXML
	private ComboBox<String> boxCompetencias;

	@FXML
	void registrarse(ActionEvent event){
		ConexionBD conexionBD = new ConexionBD();

		if (txtNombre.getText().isEmpty() &&  txtCorreo.getText().isEmpty()&& txtCorreo2.getText().isEmpty() && txtContrasena.getText().isEmpty() && txtContrasena2.getText().isEmpty() && boxTipoCliente.getSelectionModel().isEmpty()) {
			mostrarAlertInfo(event, "Debe rellenar todos los campos");
		}else if (txtNombre.getText().isEmpty()) {
			nombreBien.setVisible(true);
		} else if (!txtCorreo.getText().equals(txtCorreo2.getText())) {
			emailsBien.setVisible(true);
		} else if (!txtContrasena.getText().equals(txtContrasena2.getText())) {
			contrasenasBien.setVisible(true);
		} else if (conexionBD.consultarEmailBd(txtCorreo.getText()) == true) {
			correoBdExiste.setVisible(true);
		} else if (boxTipoCliente.getValue().equals("")) {
			mostrarAlertInfo(event, "debe rellenar el campo cliente");
		} else if (boxCompetencias.getSelectionModel().isEmpty()) {
			mostrarAlertInfo(event, "debe rellenar el campo de habilidades");
		} else {
			if (boxTipoCliente.getValue().equals("Profesionista")) {
				conexionBD.insertarRegistro(txtNombre.getText().toLowerCase(), txtCorreo.getText().toLowerCase(),
						txtContrasena.getText().toLowerCase(), boxTipoCliente.getValue(), boxCompetencias.getValue());
				try {
					App.setRoot("login");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				conexionBD.insertarRegistro(txtNombre.getText().toLowerCase(), txtCorreo.getText().toLowerCase(),
						txtContrasena.getText().toLowerCase(), boxTipoCliente.getValue(), null);
				try {
					App.setRoot("login");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	@FXML
	void logearse(ActionEvent event) throws IOException {
		App.setRoot("login");
	}

	@FXML
	void dependencia(ActionEvent event) {
		if (boxTipoCliente.getValue().equals("Profesionista")) {
			boxCompetencias.setVisible(true);
		} else {
			boxCompetencias.setVisible(false);
		}
	}

	public void initialize() {
		rellenarComboBox();
	}

	private void rellenarComboBox() {
		boxTipoCliente.getItems().addAll("Profesionista", "Cliente");
		boxCompetencias.getItems().addAll("Pintor y Decorador", "Tapicero", "Dise??ador de Joyas",
				"Dise??ador de Vestuario", "Ebanista");
	}
	
	private void mostrarAlertInfo(ActionEvent event, String texto) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("ALERTA!!");
		alert.setContentText(texto);
		alert.showAndWait();
	}

}
