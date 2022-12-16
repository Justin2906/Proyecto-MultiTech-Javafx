package Proyecto.Nebrija;

import java.io.IOException;
import Modelo.ConexionBD;
import Modelo.DatosUsuario;
import Modelo.Reservas;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
		String fecha = txtFechaReserva.getValue().toString();
		// LoginController login = new LoginController();

		if (txtFechaReserva.getValue() == null || txtNumProfes.getText().isEmpty()
				|| boxHabilidades.getValue() == null) {
			camposIncompletos.setVisible(true);
		} else if (conexionBD.insertarReserva(fecha, txtNumProfes.getText(), boxHabilidades.getValue(),
				LoginController.usuarioLogueado.getId()) == true) {
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
		App.setRoot("login");
	}

	// relleno el combobox a pelo para comprobar funcionalidad IMPORTANTE: borrar
	// luegop y rellenar con datos de la base de datos
	public void initialize() {
		rellenarComboBox();
	}

	private void rellenarComboBox() {
		boxHabilidades.getItems().addAll("Pintor y Decorador", "Tapicero", "Diseñador de Joyas",
				"Diseñador de Vestuario", "Ebanista");
	}

}
