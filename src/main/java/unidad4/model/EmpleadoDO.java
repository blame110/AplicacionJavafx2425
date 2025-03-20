package unidad4.model;

public class EmpleadoDO {

	//(`idempleados`,`nombre`,`apellidos`,`edad`,`sueldo`,`puesto`
	private int idEmpleado;
	private String nombre;
	private String apellidos;
	private int edad;
	private double sueldo;
	private int puesto;

	public EmpleadoDO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param idEmpleado
	 * @param nombre
	 * @param apellidos
	 * @param edad
	 * @param sueldo
	 * @param puesto
	 */
	public EmpleadoDO(int idEmpleado, String nombre, String apellidos, int edad, double sueldo, int puesto) {
		super();
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.sueldo = sueldo;
		this.puesto = puesto;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public int getPuesto() {
		return puesto;
	}

	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}

	@Override
	public String toString() {
		return "EmpleadoDO [idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad="
				+ edad + ", sueldo=" + sueldo + ", puesto=" + puesto + "]";
	}

}
