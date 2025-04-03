package javafx_archetype_simple.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AplicacionEjemploFx extends Application {

	@Override
	public void start(Stage stage) {

		/*******************************************
		 * DECLARACION DE CONTROLES Y PANELES
		 *****************************************/
		//Panel VBoxPrincipal
		VBox panelPrincipal = new VBox();

		//Creamos una Barra de menu
		MenuBar barraM = new MenuBar();

		//Creamos menus principales para añadir a nuestra barra de menu
		Menu menuFile = new Menu("File");
		Menu menuDB = new Menu("Database");
		Menu menuHelp = new Menu("Help");
		Menu menuEmployee = new Menu("Employee");

		//Creamos los items
		MenuItem mChat = new MenuItem("Chat IA");
		MenuItem mExit = new MenuItem("Exit");
		MenuItem mUpdate = new MenuItem("Update Employee");
		MenuItem mList = new MenuItem("List Employees");
		MenuItem mAbout = new MenuItem("About Us");

		//Añadimos los items a los menus principales
		menuFile.getItems().addAll(mChat, mExit);
		menuDB.getItems().add(menuEmployee);
		menuEmployee.getItems().addAll(mUpdate, mList);
		menuHelp.getItems().addAll(mAbout);

		//Añadimos los menus a la barra de menu
		barraM.getMenus().addAll(menuFile, menuDB, menuHelp);

		//Creamos los paneles principales
		PanelEmpleado pEmpleado = new PanelEmpleado();
		PanelFTP pFtp = new PanelFTP();
		PanelChatBot pChat = new PanelChatBot();

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
		chatTab.setContent(pChat);

		//Añadimos las pestañas al panel de pestañas
		tabPes.getTabs().addAll(empleadoTab, ftpTab, chatTab);

		//Ejemplo por defecto que se selecciona la pestaña ftp
		tabPes.getSelectionModel().select(ftpTab);

		/***************************************************
		 * EVENTOS
		 ***************************************************/
		//Si damos a exit se cierra
		mExit.setOnAction(e -> {
			stage.close();
		});
		//Cuando pulsamos sobre el menuitem chat nos vamos a la pestaña del chatbot
		mChat.setOnAction(e -> {
			tabPes.getSelectionModel().select(2);
		});

		mAbout.setOnAction(e -> {
			//Creo un stage de tipo aboutus
			WindowAboutUs wAbout = new WindowAboutUs();
			//Pongo madalidad windows para que la ventana padre se quede bloqueada
			wAbout.initModality(Modality.WINDOW_MODAL);
			//Defino la ventana padre
			wAbout.initOwner(stage);
			//Mostramos la ventana
			wAbout.showAndWait();
		});

		//Ponemos el gridpane dentro del VBox
		panelPrincipal.getChildren().addAll(barraM, tabPes);

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
