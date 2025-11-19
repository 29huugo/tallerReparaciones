package entities;

public class Vehiculo {
	private int id;
	private String matricula;
	private String marca;
	private String modelo ;
	private int Cliente_id;
	
	public Vehiculo (int id, String matricula , String  marca , String modelo , int Cliente_id ) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
	    this.Cliente_id = Cliente_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getCliente_id() {
		return Cliente_id;
	}

	public void setCliente_id(int cliente_id) {
		Cliente_id = cliente_id;
	}

}
