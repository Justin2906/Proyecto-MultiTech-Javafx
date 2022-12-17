package Proyecto.Nebrija;

import java.io.IOException;
import java.time.LocalDate;

import Modelo.ConexionBD;
import Modelo.DatosUsuario;
import Modelo.Reservas;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class InicioController {

	@FXML
	private ComboBox<String> boxHabilidades;

	@FXML
	private ImageView imgViewCalendar;

	@FXML
	private Button btnReservas;

	@FXML
	private ImageView imgViewDatos;

	@FXML
	private Label insertBien;

	@FXML
	private Label camposIncompletos;

	@FXML
	private ImageView imgViewLogo;

	@FXML
	private Label insertMal;

	@FXML
	private Button btnDatosPersonales;

	@FXML
	private Button btnServicios;

	@FXML
	private DatePicker txtFechaReserva;

	@FXML
	private Button btnResevar;

	@FXML
	private ImageView imgViewServices;

	@FXML
	private TextField txtNumProfes;

	@FXML
	private Button btnCerrarSesion;

	private ObservableList<Reservas> lista;

	@FXML
	void reservar(ActionEvent event) throws IOException {
		ConexionBD conexionBD = new ConexionBD();
		LocalDate fecha = txtFechaReserva.getValue();
		
		if (fecha == null || txtNumProfes.getText().isEmpty() || boxHabilidades.getSelectionModel().isEmpty()) {
			camposIncompletos.setVisible(true);
		} else if (conexionBD.insertarReserva(txtFechaReserva.getValue().toString(), txtNumProfes.getText(),
				boxHabilidades.getValue(), LoginController.usuarioLogueado.getId()) == true) {
			insertBien.setVisible(true);
		} else {
			insertMal.setVisible(true);
		}

	}

	@FXML
	void misDatos(ActionEvent event) throws IOException {
		App.setRoot("profile");
	}

	@FXML
	void serviciosOfrecidos(ActionEvent event) throws IOException {
		App.setRoot("services");
	}

	@FXML
	void misReservas(ActionEvent event) throws IOException {
		App.setRoot("misReservas");
	}

	@FXML
	void cerrarSesion(ActionEvent event) throws IOException {
		App.setRoot("Login");
	}

	public void initialize() {
		rellenarComboBox();
	}

	private void rellenarComboBox() {
		boxHabilidades.getItems().addAll("Pintor y Decorador", "Tapicero", "Diseñador de Joyas",
				"Diseñador de Vestuario", "Ebanista");
	}
	
	private void cerrarVentana(Button boton) {
		Stage stage = (Stage) boton.getScene().getWindow();
		stage.close();
	}

}
