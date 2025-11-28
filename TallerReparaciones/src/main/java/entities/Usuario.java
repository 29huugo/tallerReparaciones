package entities;

public class Usuario {
private int id;
private String nombre;
private boolean password;
private String  rol;

public Usuario (int id ,String nombre , boolean password , String rol) {
super();
this.id = id;
this.nombre = nombre;
this.password = password;
this.rol = rol;


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

public boolean isPassword() {
	return password;
}

public void setPassword(boolean password) {
	this.password = password;
}

public String getRol() {
	return rol;
}

public void setRol(String rol) {
	this.rol = rol;
} 
	

}
