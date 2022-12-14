package Proyecto.Nebrija;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import Modelo.ConexionBD;
import Modelo.Reservas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ReservasController {

	@FXML
	private Label mensajeDelete;

	@FXML
	private TableView<Reservas> listaReservas;

	@FXML
	private Button btnModificarRegistro;

	@FXML
	private Button btnSearch;

	@FXML
	private Button btnDelete;

	@FXML
	private DatePicker txtFechaReservaUp;

	@FXML
	private Button btnUpdate;

	@FXML
	private ComboBox<String> boxHabilidadesUp;

	@FXML
	private TextField txtNumProfesUp;

	private ObservableList<Reservas> lista;
	
	private ObservableList<Reservas> listaAux;

	@FXML
	private TableColumn<Reservas, String> clmHabilidad;

	@FXML
	private TableColumn<Reservas, String> clmFecha;

	@FXML
	private TableColumn<Reservas, String> clmNum;

	@FXML
	private TableColumn<Reservas, String> clmId;

	@FXML
	private Button gb;

	@FXML
	private TextField txtIdUp;

	@FXML
	private DatePicker buscasReserva;

	private ConexionBD conexionBD = new ConexionBD();

	@FXML
	void getItem() {
		listaReservas.onMouseClickedProperty().set(event -> {
			Reservas selectReservas = listaReservas.getSelectionModel().getSelectedItem();
			if (selectReservas != null) {
				txtIdUp.setText(String.valueOf(selectReservas.getId()));
				txtFechaReservaUp.setValue(LocalDate.parse(selectReservas.getFechaReserva()));
				txtNumProfesUp.setText(selectReservas.getNumProfesionistas().toString());
				boxHabilidadesUp.setValue(selectReservas.getHabilidad().toString());
			}
		});
	}

	@FXML
	void update(ActionEvent event) {
		ConexionBD conexionBD = new ConexionBD();

		Reservas r = new Reservas(Integer.valueOf(txtIdUp.getText()), txtFechaReservaUp.getValue().toString(),
				txtNumProfesUp.getText(), boxHabilidadesUp.getValue().toString());

		int resultado = r.actualizarRegistro(conexionBD.conectar());

		if (resultado == 1) {
			lista.set(listaReservas.getSelectionModel().getSelectedIndex(), r);
			System.out.println("Actualizado correctamente");
		}

	}

	@FXML
	void delete(ActionEvent event) {
		ConexionBD conexionBD = new ConexionBD();

		int resultado = listaReservas.getSelectionModel().getSelectedItem().eliminarRegistro(conexionBD.conectar());

		if (resultado == 1) {
			lista.remove(listaReservas.getSelectionModel().getSelectedIndex());
			System.out.println("Elimando correctamente");
		}
	}

	@FXML
	void search(ActionEvent event) {
		
		
	}

	@FXML
	void goback(ActionEvent event) throws IOException {
		App.setRoot("inicio");
	}

	public void initialize() {
			listaReservas.setEditable(true);

			ConexionBD conexionBD = new ConexionBD();

			lista = FXCollections.observableArrayList();
			Reservas.llenarInformacionReservas(conexionBD.conectar(), lista);
			listaReservas.setItems(lista);

			clmId.setCellValueFactory(new PropertyValueFactory<Reservas, String>("id"));
			clmFecha.setCellValueFactory(new PropertyValueFactory<Reservas, String>("fechaReserva"));
			clmNum.setCellValueFactory(new PropertyValueFactory<Reservas, String>("numProfesionistas"));
			clmHabilidad.setCellValueFactory(new PropertyValueFactory<Reservas, String>("habilidad"));

			rellenarComboBox();
		
	}

	private void rellenarComboBox() {
		boxHabilidadesUp.getItems().addAll("Pintor y Decorador", "Tapicero", "Diseñador de Joyas",
				"Diseñador de Vestuario", "Ebanista");

	}

}
