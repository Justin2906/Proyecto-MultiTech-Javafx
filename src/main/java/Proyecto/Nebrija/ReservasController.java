package Proyecto.Nebrija;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Modelo.ConexionBD;
import Modelo.Reservas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

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
	private TextField busqueda;

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
	void goback(ActionEvent event) throws IOException {
		App.setRoot("inicio");
	}

	public void initialize() {
		rellenarComboBox();

		// declaro que la lista puede ser editada
		listaReservas.setEditable(true);

		ConexionBD conexionBD = new ConexionBD();

		// añado los elementos de 'lista' al tableView
		lista = FXCollections.observableArrayList();
		Reservas.llenarInformacionReservas(lista);
		listaReservas.setItems(lista);

		// añado los elementos de cada registro en sus respectivas columnas
		clmId.setCellValueFactory(new PropertyValueFactory<Reservas, String>("id"));
		clmFecha.setCellValueFactory(new PropertyValueFactory<Reservas, String>("fechaReserva"));
		clmNum.setCellValueFactory(new PropertyValueFactory<Reservas, String>("numProfesionistas"));
		clmHabilidad.setCellValueFactory(new PropertyValueFactory<Reservas, String>("habilidad"));

		// envuelvo el obLista en una fList para al principio, si no busca nada, mostrar
		// todos los datos en la tabla
		FilteredList<Reservas> filteredList = new FilteredList<>(lista, b -> true);

		// añado un predicado al filtro para que reciba cambios
		busqueda.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredList.setPredicate(reserva -> {

				// si el campo de busqueda esta vacio, muestro todos los registros
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// lo paso a lowerCase para mejor busqueda
				String lowerCaseFilter = newValue.toLowerCase();

				// filtro la lista dependiendo de lo que introduzca en el campo busqueda (recibe
				// los datos de los registros)
				if (reserva.getNumProfesionistas().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;// si es diferente de -1 devuelvo un true a la lambda
				} else if (reserva.getHabilidad().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (reserva.getFechaReserva().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (reserva.getFechaReserva().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}else {
					return false;
				}

			});
		});

		// envuelvo el filtro una lista ordenada, dependiendo de la busqueda realizada
		SortedList<Reservas> sortedList = new SortedList<>(filteredList);

		// uno la lista ordenada con el tableView para poder filtrar la busqueda
		sortedList.comparatorProperty().bind(listaReservas.comparatorProperty());

		// paso la lista ordenada y filtrada al tableview para que se actualice
		// dependiendo de los buscado
		listaReservas.setItems(sortedList);
	}

	private void rellenarComboBox() {
		boxHabilidadesUp.getItems().addAll("Pintor y Decorador", "Tapicero", "Diseñador de Joyas",
				"Diseñador de Vestuario", "Ebanista");

	}

}
