package dao.interfaces;

import java.util.ArrayList;

import entities.Cliente;

public interface ClienteDAO {
	int insert (entities.Cliente c);
	int update (entities.Cliente c);
	int delete (String dni);
	ArrayList<Cliente> findall();
    Cliente findByDni(String dni);
	



}
