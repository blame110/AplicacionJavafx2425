package javafx_archetype_simple.javafx;

import java.sql.Connection;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.EmpleadoDAO;
import model.EmpleadoDO;
import utils.UtilsBD;

public class PanelEmpleado extends GridPane {

	//Variables miembro de la clase
	private ChoiceBox chbEmpleado;
	private TextField txtNombre;
	private TextField txtApellidos;
	private ChoiceBox chbEdad;
	private TextField txtSueldo;
	private ChoiceBox chbPuesto;

	//Conexion compartida por todos los metodos para acceder a BD
	private Connection con;
	//id del empleado seleccionado actualmente
	private int idEmpleado = 0;

	public PanelEmpleado() {
		//Creamos el gridpane
		super();

		//Nos conectamos a la bd
		con = UtilsBD.ConectarBD();

		// TODO Auto-generated constructor stub
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
		chbPuesto.getItems().add(4, "ANALISTA");
		chbPuesto.getItems().add(5, "JEFEEQUIPO");
		chbPuesto.getItems().add(6, "DISENADOR");

		this.setHgap(10);
		this.setVgap(5);
		this.setPadding(new Insets(20));

		//Añadimos al grid los elementos
		this.add(lblEmpleado, 0, 0);
		this.add(chbEmpleado, 1, 0);

		this.add(lblNombre, 0, 1);
		this.add(txtNombre, 1, 1);

		this.add(lblApellidos, 0, 2);
		this.add(txtApellidos, 1, 2);

		this.add(lblEdad, 0, 3);
		this.add(chbEdad, 1, 3);

		this.add(lblSueldo, 0, 4);
		this.add(txtSueldo, 1, 4);

		this.add(lblPuesto, 0, 5);
		this.add(chbPuesto, 1, 5);

		//Para hacer que un elemento ocupe varias columnas o filas, utilizamos dos parametros mas, que son 
		//respectivamente la cantidad de columnas que ocupa y la cantidad de filas que ocupa
		this.add(btnModificar, 0, 6, 2, 1);
		//Posicionar en el centro de forma horizontal
		this.setHalignment(btnModificar, HPos.CENTER);

		/******************************************************
		 * EVENTOS
		 ******************************************************/

		chbEmpleado.setOnAction(e -> this.cargarEmpleado());
		btnModificar.setOnAction(e -> this.guardarEmpleado());

	}

	/**
	 * Esta funcion carga con el nombre del choicebox desde bd los datos de un empleado 
	 * Y los pone en el formulario
	 */
	private void cargarEmpleado() {

		//Primero cogemos el nombre del empleado que queremos cargar
		String nombre = (String) chbEmpleado.getValue();

		//Cargamos los datos del empleado del choicebox
		EmpleadoDO empleado = EmpleadoDAO.getEmpleadoDO(con, nombre);

		//Guardamos el id del empleado seleccionado
		this.idEmpleado = empleado.getIdEmpleado();

		//Cargamos los campos del formulario con los datos del empleado
		txtNombre.setText(empleado.getNombre());
		txtApellidos.setText(empleado.getApellidos());
		chbEdad.getSelectionModel().select(empleado.getEdad() - 1);
		txtSueldo.setText(String.valueOf(empleado.getSueldo()));
		chbPuesto.getSelectionModel().select(empleado.getPuesto());

	}

	private void guardarEmpleado() {

		//Cargamos los datos de un EmpleadoDO con lo que hay en el formulario
		EmpleadoDO empleado = new EmpleadoDO(this.idEmpleado, txtNombre.getText(), txtApellidos.getText(),
				(int) chbEdad.getSelectionModel().getSelectedItem(), Double.parseDouble(txtSueldo.getText()),
				chbPuesto.getSelectionModel().getSelectedIndex());
		//Guardamos en BD
		int resultado = EmpleadoDAO.updateEmpleado(con, empleado);
		String textoResultado = "";

		//dependiendo del resultado del guardado mostraremos uno u otro mensaje
		switch (resultado) {
		case -1:
			textoResultado = "El empleado esta Vacio";
			break;
		case -2:
			textoResultado = "El empleado no existe en BD";
			break;
		case -3:
			textoResultado = "Ha habido un error al guardar el empleado";
			break;
		default:
			textoResultado = "El empleado ha sido modificado correctamente";
		}

		// Crear una alerta de tipo información
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("Guardando Empleado");
		alert.setContentText(textoResultado);

		// Mostrar la alerta y esperar hasta que el usuario la cierre
		alert.showAndWait();

	}

}
