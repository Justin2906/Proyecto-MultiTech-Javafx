package Modelo;

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
		return "loginUsuario [id=" + id + ", correo=" + correo + ", contraseña=" + contraseña
				+ "]";
	}

}
