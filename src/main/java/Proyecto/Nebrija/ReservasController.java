package Proyecto.Nebrija;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modelo.ConexionBD;
import Modelo.Reservas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReservasController {

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

	private ConexionBD conexionBD = new ConexionBD();

	@FXML
	void update(ActionEvent event) {
		txtFechaReservaUp.setVisible(true);
		txtNumProfesUp.setVisible(true);
		boxHabilidadesUp.setVisible(true);
		btnModificarRegistro.setVisible(true);
	}

	@FXML
	void modificarRegistro(ActionEvent event) {
		Reservas reservas = new Reservas(Reservas.llenarInformacionReservas(conexionBD.conectar(), lista), txtFechaReservaUp.getValue().toString(), txtNumProfesUp.getText(),
				boxHabilidadesUp.getValue());
		int resultado = actualizarRegistro(conexionBD.conectar());

		if (resultado == 1) {
			lista.set(listaReservas.getSelectionModel().getSelectedIndex(), reservas);
		}

	}

	@FXML
	void delete(ActionEvent event) {
		
	}

	@FXML
	void search(ActionEvent event) {

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
	}

	public int actualizarRegistro(Connection connection) {
		try {
			PreparedStatement instruccion = connection.prepareStatement(
					"update reservas set id = ?, Fecha_Reserva = ?, Num_profesionistas = ?, Habilidad_Requerida = ? where id = ?");
			instruccion.setInt(1, 2);
			instruccion.setString(2, txtFechaReservaUp.getValue().toString());
			instruccion.setString(3, txtNumProfesUp.getText());
			instruccion.setString(4, boxHabilidadesUp.getValue());
			instruccion.setInt(5, 1);

			return instruccion.executeUpdate();
		} catch (SQLException e) {
			return 0;
		}
	}
	
	@FXML
    void goback(ActionEvent event) throws IOException {
		App.setRoot("inicio");
    }

}
