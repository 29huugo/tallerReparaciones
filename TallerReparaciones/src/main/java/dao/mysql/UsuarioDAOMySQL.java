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
    public boolean login(String dni, String password) {
		return false;
	}
    public int insert(Usuario u) {
		return 0;
	}
    public ArrayList<Usuario> findall() {
		return null;
	}
    public Usuario findByNombre (String nombre) {
		return null;
	}
    

}
