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

	public void insertarRegistro(String nombre, String email, String contrasena, String tipoCliente, String habilidad) {
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		int resultado = 0;

		String sql = "";

		try {
			Class.forName(driver);

			conexion = DriverManager.getConnection(url + bd, user, password);

			sentenciaSQL = conexion.createStatement();

			if (tipoCliente.equals("Profesionista")) {
				sql = "insert into profesionista (id,Nombre,Email,Contrasena,Habilidad) values (0,'" + nombre + "','" + email + "','"
						+ contrasena  + "','" + habilidad + "')";
			}else{
				sql = "insert into cliente (id,Nombre,Email,Contraseña) values (0,'" + nombre + "','" + email + "','"
					+ contrasena + "')";
			}
			

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

	public boolean consultarEmailBd(String correo) {

		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs = null;
		String query = "";

		boolean encontrado = false;

		try {

			Class.forName(driver);
			conexion = DriverManager.getConnection(url + bd, user, password);

			sentenciaSQL = conexion.createStatement();

			query = "SELECT * FROM cliente WHERE Email = '" + correo + "';";
			// query = "SELECT Email FROM `cliente` WHERE Email = '" + correo + "';";
			// System.out.println(sql);

			rs = sentenciaSQL.executeQuery(query);

			// chequeo que el result set no sea vac�o, moviendo el cursor a la
			// primer fila. (El cursor inicia antes de la primer fila)

			while (rs.next()) {
				// Si hay resultados obtengo el valor.
				System.out.println(rs.getString("Email"));
				if (rs.getString("Email").equals(correo)) {
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
			return true;
		} else {
			return false;
		}

	}

	public boolean consultarContrasenaBd(String correo, String contrasena) {
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs = null;
		String query = "";

		boolean encontrado = false;

		try {

			Class.forName(driver);
			conexion = DriverManager.getConnection(url + bd, user, password);

			sentenciaSQL = conexion.createStatement();

			query = "SELECT * FROM cliente WHERE Email = '" + correo + "';";
			// query = "SELECT Email FROM `cliente` WHERE Email = '" + correo + "';";
			// System.out.println(sql);

			rs = sentenciaSQL.executeQuery(query);

			// chequeo que el result set no sea vac�o, moviendo el cursor a la
			// primer fila. (El cursor inicia antes de la primer fila)

			while (rs.next()) {
				// Si hay resultados obtengo el valor.
				System.out.println(rs.getString("Contraseña"));
				if (rs.getString("Contraseña").equals(contrasena)) {
					encontrado = true;
					System.out.println("las contraseñas coinciden");
					
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
			return true;
		} else {
			return false;
		}

	}

	public boolean insertarReserva(String fecha_Reserva, String num_Profes, String habilidad, int ref_cliente) {
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		int resultado = 0;

		String sql = "";
		boolean encontrado = true;

		try {
			Class.forName(driver);

			conexion = DriverManager.getConnection(url + bd, user, password);

			sentenciaSQL = conexion.createStatement();

			sql = "insert into reservas (id,Fecha_Reserva,Num_profesionistas,Habilidad_Requerida, ref_cliente) values (0,'"
					+ fecha_Reserva + "','" + num_Profes + "','" + habilidad + "','" + ref_cliente + "')";

			resultado = sentenciaSQL.executeUpdate(sql);

			if (resultado >= 1) {
				System.out.println("Se ha insertado bien.");
				encontrado = true;
			} else {
				encontrado = false;
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
			return true;
		} else {
			return false;
		}
	}
	
	public int consultarIdUsuario(String correo) {

		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs = null;
		String query = "";
		int id = 0;

		boolean encontrado = false;

		try {

			Class.forName(driver);
			conexion = DriverManager.getConnection(url + bd, user, password);

			sentenciaSQL = conexion.createStatement();

			query = "SELECT id FROM cliente WHERE Email = '" + correo + "';";

			rs = sentenciaSQL.executeQuery(query);

			// chequeo que el result set no sea vac�o, moviendo el cursor a la
			// primer fila. (El cursor inicia antes de la primer fila)

			while (rs.next()) {
				id = rs.getInt("id");
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

		return id;

	}

}
