package javafx_archetype_simple.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AplicacionEjemploFx extends Application {

	@Override
	public void start(Stage stage) {

		/*******************************************
		 * DECLARACION DE CONTROLES Y PANELES
		 *****************************************/
		//Panel VBoxPrincipal
		VBox panelPrincipal = new VBox();

		//Creamos los paneles principales
		PanelEmpleado pEmpleado = new PanelEmpleado();
		PanelFTP pFtp = new PanelFTP();

		//Panel con las pestañas (tabpane)
		TabPane tabPes = new TabPane();

		//Creamos las pestañas
		Tab empleadoTab = new Tab("Empleado");
		empleadoTab.setClosable(false);
		Tab ftpTab = new Tab("Ftp");
		ftpTab.setClosable(false);
		Tab chatTab = new Tab("Chat");
		chatTab.setClosable(false);

		//Ponemos el contenidos a las pestañas
		empleadoTab.setContent(pEmpleado);
		ftpTab.setContent(pFtp);

		//Añadimos las pestañas al panel de pestañas
		tabPes.getTabs().addAll(empleadoTab, ftpTab, chatTab);

		//Ejemplo por defecto que se selecciona la pestaña ftp
		tabPes.getSelectionModel().select(ftpTab);

		//Ponemos el gridpane dentro del VBox
		panelPrincipal.getChildren().add(tabPes);

		//Creamos la escena principal con el panel principal
		Scene scene = new Scene(panelPrincipal, 800, 600);

		//Añadimos la escena al stage
		stage.setScene(scene);

		stage.setTitle("Aplicacion de ejemplo");
		stage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
	}

}
