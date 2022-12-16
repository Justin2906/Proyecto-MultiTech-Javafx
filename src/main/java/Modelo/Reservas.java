package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Proyecto.Nebrija.LoginController;

import java.sql.ResultSet;
import javafx.collections.ObservableList;

public class Reservas {

	private int id;
	private String fechaReserva;
	private String numProfesionistas;
	private String habilidad;

	public Reservas(int id, String fechaReserva, String numProfesionistas, String habilidad) {
		super();
		this.id = id;
		this.fechaReserva = fechaReserva;
		this.numProfesionistas = numProfesionistas;
		this.habilidad = habilidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(String fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public String getNumProfesionistas() {
		return numProfesionistas;
	}

	public void setNumProfesionistas(String numProfesionistas) {
		this.numProfesionistas = numProfesionistas;
	}

	public String getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(String habilidad) {
		this.habilidad = habilidad;
	}

	public static void llenarInformacionReservas(ObservableList<Reservas> lista) {
		String sql = "";
		ConexionBD conexionBD = new ConexionBD();
		
		try {
			Statement instruccion = conexionBD.conectar().createStatement();
			sql = "select id, Fecha_Reserva, Num_profesionistas, Habilidad_Requerida from reservas where ref_cliente = " + LoginController.usuarioLogueado.getId();
			ResultSet resultado = instruccion.executeQuery(sql);

			while (resultado.next()) {
				lista.add(new Reservas(resultado.getInt("id"), resultado.getString("Fecha_Reserva"),
						resultado.getString("Num_profesionistas"), resultado.getString("Habilidad_Requerida")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int actualizarRegistro(Connection connection) {
		try {
			PreparedStatement instruccion = connection.prepareStatement(
					"update reservas set id = ?, Fecha_Reserva = ?, Num_profesionistas = ?, Habilidad_Requerida = ? where id = ?");
			instruccion.setInt(1, getId());
			instruccion.setString(2, getFechaReserva());
			instruccion.setString(3, getNumProfesionistas());
			instruccion.setString(4, getHabilidad());
			instruccion.setInt(5, getId());

			return instruccion.executeUpdate();
		} catch (SQLException e) {
			return 0;
		}
	}

	public int eliminarRegistro(Connection connection) {
		try {
			PreparedStatement instruccion = connection.prepareStatement("DELETE FROM reservas WHERE id = ?");
			instruccion.setInt(1, getId());
			return instruccion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
