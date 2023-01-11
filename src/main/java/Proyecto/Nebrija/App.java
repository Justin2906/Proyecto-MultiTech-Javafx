package Proyecto.Nebrija;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import Modelo.ConexionBD;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) {
		ConexionBD conexionBD = new ConexionBD();
		conexionBD.conectar();

		try {
			scene = new Scene(loadFXML("login"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.setScene(scene);
		stage.setMaxWidth(1039);
		stage.setMaxHeight(774);
		stage.show();
		stage.setTitle("ShowSkills");
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

}