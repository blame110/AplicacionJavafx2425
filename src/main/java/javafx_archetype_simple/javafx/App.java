package javafx_archetype_simple.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	@Override
	public void start(Stage stage) {
		var javaVersion = SystemInfo.javaVersion();
		var javafxVersion = SystemInfo.javafxVersion();

		//Creamos un panel Vertical VBox, en este panel se van añadiendo cosas verticalmente una debajo de la anterior
		VBox panelVertical = new VBox();
		//Creamos un boton de ejemplo
		Button btnExplosion = new Button("Atrevete a pulsarme!!!");
		//Etiqueta de texto
		var label = new Label("Nooooooooooooooooooooooooooooo");
		//Campo de input de texto
		TextField txtnombre = new TextField("Nombre rata");
		//Creamos un checkbox

		CheckBox chbCaracterMalo = new CheckBox("Tiene Mal Caracter");
		CheckBox chbCaracterBueno = new CheckBox("Tiene Buen Caracter");

		panelVertical.getChildren().add(btnExplosion);
		panelVertical.getChildren().add(label);

		//Podemos añadir varios elementos a la vez
		panelVertical.getChildren().addAll(txtnombre, chbCaracterBueno, chbCaracterMalo);

		//Pongo el panel vertical en la escena
		var scene = new Scene(panelVertical, 800, 600);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}