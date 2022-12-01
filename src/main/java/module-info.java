module Proyecto.Nebrija {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.sql;
	requires java.desktop;

    opens Proyecto.Nebrija to javafx.fxml;
    exports Proyecto.Nebrija;
    exports Modelo;
}
