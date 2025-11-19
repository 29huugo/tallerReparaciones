package entities;

import java.util.Scanner;


public class Cliente {
	private int id;
	private String nombre;
	private String email;
	private String dni;
	private int telefono;
	
	public Cliente (int id, String nombre, String  email , String dni , int telefono ) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.dni = dni;
	    this.telefono = telefono;
	}


	public int getId() {
		return id;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}

	


}
