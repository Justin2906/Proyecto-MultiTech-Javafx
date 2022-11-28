package Proyecto.Nebrija;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
	void buscar(ActionEvent event) {

	}

	@FXML
	void misDatos(ActionEvent event) throws IOException {
		App.setRoot("profile");
	}

	@FXML
	void serviciosOfrecidos(ActionEvent event) {

	}

	@FXML
	void misReservas(ActionEvent event) {

	}
	
	//relleno el combobox a pelo para comprobar funcionalidad IMPORTANTE: borrar luegop y rellenar con datos de la base de datos
	public void initialize() {
		rellenarComboBox();
	}
	
	private void rellenarComboBox() {
		boxHabilidades.getItems().addAll("cariñosa", "fontanero", "sexo");
	}

}
