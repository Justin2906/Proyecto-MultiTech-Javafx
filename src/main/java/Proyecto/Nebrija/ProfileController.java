package Proyecto.Nebrija;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ProfileController {

	@FXML
	private Button gb;

	@FXML
	void goback(ActionEvent event) throws IOException {
		Stage nuevaStage = new Stage();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("inicio.fxml"));
			// Establecemos el t�tulo de la ventana
			nuevaStage.setTitle("Pagina Principal - ShowSkills");
			// Establecemos el ancho y el alto
			nuevaStage.setScene(new Scene(root));
			// Mostramos la ventana
			nuevaStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		cerrarVentana(gb);
	}
	
	private void cerrarVentana(Button boton) {
		Stage stage = (Stage) boton.getScene().getWindow();
		stage.close();
	}

}
