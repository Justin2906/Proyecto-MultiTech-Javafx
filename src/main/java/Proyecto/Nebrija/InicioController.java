package Proyecto.Nebrija;

import java.awt.MenuItem;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class InicioController {

	@FXML
	private Button btnDatosPersonales;

	@FXML
	private ComboBox<String> boxHabilidades;

	@FXML
	private Button btnServicios;

	@FXML
	private ImageView imgViewCalendar;

	@FXML
	private Button btnReservas;

	@FXML
	private ImageView imgViewDatos;

	@FXML
	private Label camposIncompletos;

	@FXML
	private ImageView imgViewLogo;

	@FXML
	private DatePicker txtFechaReserva;

	@FXML
	private Button btnBuscar;

	@FXML
	private ImageView imgViewServices;

	@FXML
	private TextField txtNumProfes;

	@FXML
	private MenuButton menu;

	@FXML
	void buscar(ActionEvent event) {
		if (txtFechaReserva.getValue() == null || txtNumProfes.getText().isEmpty()
				|| boxHabilidades.getValue() == null) {
			camposIncompletos.setVisible(true);
		}
	}

	@FXML
	void misDatos(ActionEvent event) throws IOException {
		App.setRoot("profile");
	}

	@FXML
	void serviciosOfrecidos(ActionEvent event) {

	}

	@FXML
	void misReservas(ActionEvent event) throws IOException {
		App.setRoot("misReservas");
	}

	// relleno el combobox a pelo para comprobar funcionalidad IMPORTANTE: borrar
	// luegop y rellenar con datos de la base de datos
	public void initialize() {
		rellenarComboBox();
	}

	private void rellenarComboBox() {
		boxHabilidades.getItems().addAll("cariñosa", "fontanero", "sexo");

	}
}
