package dao.mysql;

import java.util.ArrayList;

import entities.Usuario;

interface UsuarioDAO {
	boolean login(String dni, String password);
	int insert(Usuario u);
	ArrayList<Usuario> findall();
	Usuario findByNombre(String nombre);
}



public class UsuarioDAOMySQL implements UsuarioDAO {
    boolean login(String dni, String password);
    int insert(Usuario u);
    ArrayList<Usuario> findall();
    Usuario findByNombre (String nombre);
    

}
