package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class UtilsBD {

	/**
	 * Conecta con la BD
	 * @return
	 */
	public static Connection ConectarBD() {
		try {

			//Comprueba que tengamos el driver para conectarnos a la bd mysql 
			//Antes de conectarnos en si, si no esta no pasa nada
			Class.forName("com.mysql.cj.jdbc.Driver");

			/*Para conectarnos necesitamos 5 parametros
			 * ip o nombre del host donde esta la bd, en nuestro caso localhost
			 * puerto por el que nos conectamos,en mysql 3306
			 * usuario y contraseña
			 * nombre del esquema de la bd en nuestro caso como dijo mario es frigopie
			 */
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/frigopie", "root", "toor");

			//Una vez realizada la conexion, la devolvemos
			return con;

		} catch (Exception e) {
			//El printstacktrace muesta el errror de la excepcion si ocurre el error
			e.printStackTrace();
		}

		return null;
	}

}
