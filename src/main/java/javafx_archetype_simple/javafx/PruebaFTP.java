package javafx_archetype_simple.javafx;

import org.apache.commons.net.ftp.FTPFile;

import model.FTPService;

public class PruebaFTP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FTPService servicioFtp = new FTPService();

		servicioFtp.connectarFTP("ftp.ujaen.es", 21, "anonymous", "laquenossalede..");

		FTPFile[] ficheros = servicioFtp.mostrarFicheros();

		//Para cada fichero mostramos el nombre
		for (FTPFile fichero : ficheros) {
			System.out.println("Fichero: " + fichero.getName());
		}

		servicioFtp.desconectar();
	}

}
