package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Proyecto.Nebrija.LoginController;
import javafx.collections.ObservableList;

public class DatosUsuario {

	private int id;
	private String correo;
	private String contraseña;

	public DatosUsuario(int id, String correo, String contraseña) {
		super();
		this.id = id;
		this.correo = correo;
		this.contraseña = contraseña;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return "loginUsuario [id=" + id + ", correo=" + correo + ", contraseña=" + contraseña + "]";
	}

	public static void insertarUsuarioProfesionista(String nombre, String email, String contrasena, String habilidad) {
		String sql = "";
		ConexionBD conexionBD = new ConexionBD();
		Statement sentenciaSQL = null;
		int resultado = 0;

		try {
			Statement instruccion = conexionBD.conectar().createStatement();
			sql = "insert into profesionista (id,Nombre,Email,Contrasena,Habilidad) values (0,'" + nombre + "','"
					+ email + "','" + contrasena + "','" + habilidad + "')";
			resultado = sentenciaSQL.executeUpdate(sql);

			if (resultado >= 1) {
				System.out.println("Se ha insertado bien.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertarUsuarioCliente(String nombre, String email, String contrasena) {
		String sql = "";
		ConexionBD conexionBD = new ConexionBD();
		Statement sentenciaSQL = null;
		int resultado = 0;

		try {
			Statement instruccion = conexionBD.conectar().createStatement();
			sql = "insert into profesionista (id,Nombre,Email,Contrasena,Habilidad) values (0,'" + nombre + "','"
					+ email + "','" + contrasena + "')";
			resultado = sentenciaSQL.executeUpdate(sql);

			if (resultado >= 1) {
				System.out.println("Se ha insertado bien.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
