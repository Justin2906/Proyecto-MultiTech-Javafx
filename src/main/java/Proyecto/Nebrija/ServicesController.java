package Proyecto.Nebrija;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ServicesController {

	@FXML
	private Button gb;

	@FXML
	void goback(ActionEvent event) throws IOException {
		App.setRoot("inicio");
	}

}
