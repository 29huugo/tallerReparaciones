package entities;

public class Usuario {
private int id;
private String dni;
private String nombre;
private String password;
private String  rol;



public Usuario() {
	
}
public Usuario (int id ,String dni ,String nombre , String password , String rol) {
super();
this.id = id;
this.dni=dni;
this.nombre = nombre;
this.password = password;
this.rol = rol;


}

public String getDni() {
	return dni;
}

public void setDni(String dni) {
	this.dni = dni;
}

public int getId() {
	return id;
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

public String getPassword() {
	return password;
}

public void setPassword(String password ) {
	this.password = password ;
}

public String getRol() {
	return rol;
}

public void setRol(String rol) {
	this.rol = rol;
} 
	

}
