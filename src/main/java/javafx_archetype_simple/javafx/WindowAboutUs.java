package javafx_archetype_simple.javafx;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WindowAboutUs extends Stage {

	public WindowAboutUs() {

		//Creamos una escena con la etiqueta y la añadimos al stage
		Scene scene = new Scene(new Label("SOMOS LA MITAD DE LA CLASE DE 1DAW 2425"), 380, 200);

		//añadimos la scena al stage
		this.setScene(scene);
	}

}
