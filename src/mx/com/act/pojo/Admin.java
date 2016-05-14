package mx.com.act.pojo;

import java.sql.Timestamp;

public class Admin {

	private int idAdmin;
	private String nombre;
	private String cargo;
	private Timestamp fechaCreacion;

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		return "Admin [idAdmin=" + idAdmin + ", nombre=" + nombre + ", cargo="
				+ cargo + ", fechaCreacion=" + fechaCreacion + "]";
	}
}
