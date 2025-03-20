package javafx_archetype_simple.javafx;

import java.sql.Connection;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import unidad4.model.EmpleadoDAO;
import unidad4.utils.UtilsBD;

public class AplicacionEjemploFx extends Application {

	//Variables miembro de la clase
	private ChoiceBox chbEmpleado;
	private TextField txtNombre;
	private TextField txtApellidos;
	private ChoiceBox chbEdad;
	private TextField txtSueldo;
	private ChoiceBox chbPuesto;
	private Connection con;

	@Override
	public void start(Stage stage) {

		//Nos conectamos a la bd
		con = UtilsBD.ConectarBD();

		/*******************************************
		 * DECLARACION DE CONTROLES Y PANELES
		 *****************************************/

		VBox panelPrincipal = new VBox();
		GridPane gPane = new GridPane();

		chbEmpleado = EmpleadoDAO.cargarNombresEmpleados(con);
		Label lblEmpleado = new Label("Empleado");

		txtNombre = new TextField();
		Label lblNombre = new Label("Nombre");

		txtApellidos = new TextField();
		Label lblApellidos = new Label("Apellidos");

		chbEdad = new ChoiceBox();
		Label lblEdad = new Label("Edad");

		txtSueldo = new TextField();
		Label lblSueldo = new Label("Sueldo");

		chbPuesto = new ChoiceBox();
		Label lblPuesto = new Label("Puesto");

		Button btnModificar = new Button("Modificar");
		btnModificar.setPrefSize(200, 30);

		//Ponemos todos los números de 1 a 120 en las opciones del choicebox de edad
		for (int i = 1; i <= 120; i++)
			chbEdad.getItems().add(i - 1, Integer.valueOf(i));

		//Añadimos las opciones de seleccion del puesto
		chbPuesto.getItems().add(0, "PROGRAMADOR");
		chbPuesto.getItems().add(1, "JEFE");
		chbPuesto.getItems().add(2, "RRHH");
		chbPuesto.getItems().add(3, "DIRECTOR");

		gPane.setHgap(10);
		gPane.setVgap(5);
		gPane.setPadding(new Insets(20));

		//Añadimos al grid los elementos
		gPane.add(lblEmpleado, 0, 0);
		gPane.add(chbEmpleado, 1, 0);

		gPane.add(lblNombre, 0, 1);
		gPane.add(txtNombre, 1, 1);

		gPane.add(lblApellidos, 0, 2);
		gPane.add(txtApellidos, 1, 2);

		gPane.add(lblEdad, 0, 3);
		gPane.add(chbEdad, 1, 3);

		gPane.add(lblSueldo, 0, 4);
		gPane.add(txtSueldo, 1, 4);

		gPane.add(lblPuesto, 0, 5);
		gPane.add(chbPuesto, 1, 5);

		//Para hacer que un elemento ocupe varias columnas o filas, utilizamos dos parametros mas, que son 
		//respectivamente la cantidad de columnas que ocupa y la cantidad de filas que ocupa
		gPane.add(btnModificar, 0, 6, 2, 1);
		//Posicionar en el centro de forma horizontal
		gPane.setHalignment(btnModificar, HPos.CENTER);

		/**	private String nombre;
		private String apellidos;
		private int edad;
		private double sueldo;
		private int puesto;**/

		//Ponemos el gridpane dentro del VBox
		panelPrincipal.getChildren().add(gPane);

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

	private void cargarEmpleado() {

		//Primero cogemos el nombre del empleado que queremos cargar
		String nombre = (String) chbEmpleado.getValue();

	}

}
