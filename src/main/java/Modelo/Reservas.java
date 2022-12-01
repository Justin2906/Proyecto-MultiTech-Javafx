package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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

	public static int llenarInformacionReservas(Connection connection,ObservableList<Reservas> lista) {
		String  sql = "";
		int id=0;
		try {
			Statement instruccion = connection.createStatement();
			sql = "select id, Fecha_Reserva, Num_profesionistas, Habilidad_Requerida from reservas";
			ResultSet resultado = instruccion.executeQuery(sql);
			
			while (resultado.next()) {
				lista.add(new Reservas(resultado.getInt("id"),resultado.getString("Fecha_Reserva"), resultado.getString("Num_profesionistas"), resultado.getString("Habilidad_Requerida")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return id;
	}
	
	
	
	
	
	
}
