package entities;

public class Reparacion {
private int id;
private String descripcion;
private int fecha_entrada;
private double coste;
private int vehiculo_id;
private int usuario_id;


public Reparacion() {
	
}

public Reparacion (int id , String descripcion , int fecha_entrada, double coste , int vehiculo_id ,int usuario_id ) {

 this.id = id;
 this.descripcion = descripcion;
 this.fecha_entrada = fecha_entrada;
 this.coste = coste;

}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

public int getFecha_entrada() {
	return fecha_entrada;
}

public void setFecha_entrada(int fecha_entrada) {
	this.fecha_entrada = fecha_entrada;
}

public double getCoste() {
	return coste;
}

public void setCoste(double coste) {
	this.coste = coste;
}

public int getVehiculo_id() {
	return vehiculo_id;
}

public void setVehiculo_id(int vehiculo_id) {
	this.vehiculo_id = vehiculo_id;
}

public int getUsuario_id() {
	return usuario_id;
}

public void setUsuario_id(int usuario_id) {
	this.usuario_id = usuario_id;
}




}
