package entities;

public class Reparacion {
private int id;
private String descripcion;
private int fecha_entrada;
private int coste;

public Reparacion (int id , String descripcion , int fecha_entrada, int coste) {
 super();
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

public int getCoste() {
	return coste;
}

public void setCoste(int coste) {
	this.coste = coste;
}



}
