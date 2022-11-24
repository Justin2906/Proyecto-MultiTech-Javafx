package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {

	String bd = "proyecto_javafx";
	String url = "jdbc:mysql://localhost:3306/";
	String user = "root";
	String password = "";
	String driver = "com.mysql.cj.jdbc.Driver";
	Connection cx;

	public ConexionBD() {
	}

	// metodo para establecer conexion a la base de datos
	public Connection conectar() {

		try {
			Class.forName(driver);
			cx = DriverManager.getConnection(url + bd, user, password);
			System.out.println("SE A CONECTADO A BD: " + bd);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("NO SE A CONECTADO A BD: " + bd);
			// Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, e);
		}
		return cx;

	}

	// metodo para insertar registros en la base de datoss
	public void desconectar() {
		try {
			cx.close();
		} catch (SQLException e) {
			Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void insertarRegistro(String nombre, String email, String contrasena) {
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		int resultado = 0;

		String sql = "";

		try {
			Class.forName(driver);

			conexion = DriverManager.getConnection(url + bd, user, password);

			sentenciaSQL = conexion.createStatement();

			sql = "insert into cliente (id,Nombre,Email,Contraseña) values (0,'" + nombre + "','" + email + "','"
					+ contrasena + "')";

			resultado = sentenciaSQL.executeUpdate(sql);

			if (resultado >= 1) {
				System.out.println("Se ha insertado bien.");
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			// System.out.println("Error");
		} finally {
			try {
				if (sentenciaSQL != null) {
					sentenciaSQL.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private boolean consultarDatosBd(String correo, String contrasena) {

		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs = null;
		String sql = "";
		int numero = 0;
		
		boolean encontrado = false;

		try {

			Class.forName(driver);
			conexion = DriverManager.getConnection(url + bd, user, password);

			sentenciaSQL = conexion.createStatement();

			sql = "SELECT email FROM cliente where email = '" + correo + "'";
			// System.out.println(sql);

			rs = sentenciaSQL.executeQuery(sql);

			// chequeo que el result set no sea vac�o, moviendo el cursor a la
			// primer fila. (El cursor inicia antes de la primer fila)
			while (rs.next()) {
				// Si hay resultados obtengo el valor.

				if (rs.getString("email").equals("")) {
					encontrado = true;
				} else {
					encontrado = false;
				}

			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			// System.out.println("Error");
		} finally {
			try {
				if (sentenciaSQL != null) {
					sentenciaSQL.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
		if (encontrado) {
			return false;
		}else {
			return true;
		}

	}

}
