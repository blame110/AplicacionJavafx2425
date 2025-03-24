package model;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPService {

	private FTPClient ftpClient;

	public FTPService() {
		super();
		// TODO Auto-generated constructor stub
		ftpClient = new FTPClient();
	}

	public int connectarFTP(String host, int port, String username, String password) {

		try {

			//Nos conectamos al servidor
			ftpClient.connect(host, port);
			//Nos logamos con el usuario y el password
			boolean conectado = ftpClient.login(username, password);

			if (conectado) {
				//Ponemos la conexion en modo pasivo 
				ftpClient.enterLocalPassiveMode();
				return 0;
			}

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -2;
		}

		return -1;
	}

	/**
	 * Carga los ficheros del servidor ftp y los devuelve en un array
	 * @return
	 */
	public FTPFile[] mostrarFicheros() {

		FTPFile[] files = null;

		//Si estamos conectados
		if (ftpClient != null && ftpClient.isConnected()) {

			try {
				//Cargamos los ficheros del directorio actual
				files = ftpClient.listFiles();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}

		//Devolvemos los ficheros
		return files;
	}

	/**
	 * Funcion para desconectar del ftp
	 */
	public void desconectar() {
		if (ftpClient != null && ftpClient.isConnected()) {

			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
