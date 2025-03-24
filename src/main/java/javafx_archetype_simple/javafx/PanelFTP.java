package javafx_archetype_simple.javafx;

import org.apache.commons.net.ftp.FTPFile;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.FTPService;

public class PanelFTP extends GridPane {

	//Variables miembro de la clase

	private TextField txtHost;
	private TextField txtPuerto;
	private TextField txtUsuario;
	private PasswordField txtPassword;
	private ListView tablaFicheros;

	//Servicio FTP Para gestionar la conexion
	FTPService servicioFtp;

	public PanelFTP() {
		//Creamos el gridpane
		super();

		//Creamos el servicio ftp
		servicioFtp = new FTPService();

		txtHost = new TextField();
		Label lblHost = new Label("Host");

		txtPuerto = new TextField();
		Label lblPuerto = new Label("Puerto");

		txtUsuario = new TextField();
		Label lblUsuario = new Label("Usuario");

		txtPassword = new PasswordField();
		Label lblPassword = new Label("Password");

		Button btnMostrarFicheros = new Button("Mostrar Ficheros");
		btnMostrarFicheros.setPrefSize(200, 30);

		tablaFicheros = new ListView();

		this.setHgap(10);
		this.setVgap(5);
		this.setPadding(new Insets(20));

		//Añadimos al grid los elementos
		this.add(lblHost, 0, 0);
		this.add(txtHost, 1, 0);

		this.add(lblPuerto, 0, 1);
		this.add(txtPuerto, 1, 1);

		this.add(lblUsuario, 0, 2);
		this.add(txtUsuario, 1, 2);

		this.add(lblPassword, 0, 3);
		this.add(txtPassword, 1, 3);

		//Para hacer que un elemento ocupe varias columnas o filas, utilizamos dos parametros mas, que son 
		//respectivamente la cantidad de columnas que ocupa y la cantidad de filas que ocupa
		this.add(btnMostrarFicheros, 0, 4, 2, 1);
		//Posicionar en el centro de forma horizontal
		this.setHalignment(btnMostrarFicheros, HPos.CENTER);

		//muestro la listview
		this.add(tablaFicheros, 0, 5, 2, 1);
		tablaFicheros.setVisible(false);

		/******************************************************
		 * EVENTOS
		 ******************************************************/

		btnMostrarFicheros.setOnAction(e -> this.mostrarFicheros());

		//Eventhandler para manejar el evento de doble click
		tablaFicheros.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent click) {

				//Si se ha pulsado dos veces el raton
				if (click.getClickCount() == 2) {
					//Cogemos el fichero selecionado
					String ficheroSeleccionado = (String) tablaFicheros.getSelectionModel().getSelectedItem();

					// Mostramos en una ventana el nombre del fichero
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Información");
					alert.setHeaderText("Has hecho doble click!!");
					alert.setContentText("Sobre el elemento: " + ficheroSeleccionado);
					alert.showAndWait();
				}
			}
		});

	}

	private void mostrarFicheros() {

		//servicioFtp.connectarFTP("ftp.ujaen.es", 21, "anonymous", "laquenossalede..");
		//Nos conectamos al servidor ftp con los datos del formulario
		servicioFtp.connectarFTP(this.txtHost.getText(), Integer.valueOf(this.txtPuerto.getText()),
				this.txtUsuario.getText(), this.txtPassword.getText());

		FTPFile[] ficheros = servicioFtp.mostrarFicheros();

		//Para cada fichero insertamos su nombre en la listView
		for (FTPFile fichero : ficheros) {
			tablaFicheros.getItems().add(fichero.getName());
		}

		tablaFicheros.setVisible(true);

		servicioFtp.desconectar();
	}

}
