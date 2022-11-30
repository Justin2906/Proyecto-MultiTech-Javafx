package Proyecto.Nebrija;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ReservasController {

	@FXML
	private TableView<String> listaReservas;

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

	@FXML
	void update(ActionEvent event) {
		txtFechaReservaUp.setVisible(true);
		txtNumProfesUp.setVisible(true);
		boxHabilidadesUp.setVisible(true);
		btnModificarRegistro.setVisible(true);
	}

	@FXML
	void delete(ActionEvent event) {

	}

	@FXML
	void search(ActionEvent event) {

	}

	public void initialize() {
//		tablaLista.setEditable(true);
//		TableColumn nombreCol = new TableColumn("Nombre");
//		TableColumn tipoCol = new TableColumn("Tipo");
//		TableColumn companiaCol = new TableColumn("Compañía");
//		TableColumn plataformaCol = new TableColumn("Plataforma");
//		TableColumn fechaCol = new TableColumn("Fecha");
//		TableColumn requisitosCol = new TableColumn("Requisitos");
//		TableColumn comentarioCol = new TableColumn("Comentario");
//		nombreCol.setCellValueFactory(new PropertyValueFactory<VideoJuego, String>("nombre"));
//		tipoCol.setCellValueFactory(new PropertyValueFactory<VideoJuego, String>("tipo"));
//		companiaCol.setCellValueFactory(new PropertyValueFactory<VideoJuego, String>("compania"));
//		plataformaCol.setCellValueFactory(new PropertyValueFactory<VideoJuego, String>("plataforma"));
//		fechaCol.setCellValueFactory(new PropertyValueFactory<VideoJuego, String>("fecha"));
//		requisitosCol.setCellValueFactory(new PropertyValueFactory<VideoJuego, String>("requisitos"));
//		comentarioCol.setCellValueFactory(new PropertyValueFactory<VideoJuego, String>("comentario"));
//		ObservableList<VideoJuego> pepe = FXCollections.observableArrayList(listaJuegos.getListadoJuegos());
//		tablaLista.setItems((ObservableList<VideoJuego>) pepe);
//		tablaLista.getColumns().addAll(nombreCol);
//		tablaLista.getColumns().addAll(tipoCol);
//		tablaLista.getColumns().addAll(companiaCol);
//		tablaLista.getColumns().addAll(plataformaCol);
//		tablaLista.getColumns().addAll(fechaCol);
//		tablaLista.getColumns().addAll(requisitosCol);
//		tablaLista.getColumns().addAll(comentarioCol);
	}

}
