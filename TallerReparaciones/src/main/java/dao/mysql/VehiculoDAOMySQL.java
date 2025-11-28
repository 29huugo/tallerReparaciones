package dao.mysql;

import java.util.ArrayList;

import entities.Usuario;

interface VehiculoDAO {
	boolean login(String dni, String password);
	int insert(Usuario u);
	ArrayList<Usuario> findall();
	Usuario findByNombre(String nombre);
}










public class VehiculoDAOMySQL {

}
