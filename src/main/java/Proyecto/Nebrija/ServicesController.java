package Proyecto.Nebrija;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ServicesController {

	@FXML
	private Button gb;

	@FXML
	void goback(ActionEvent event) throws IOException {
		App.setRoot("inicio");
	}
 
}
