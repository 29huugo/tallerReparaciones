package dao.mysql;

import java.util.ArrayList;

import dao.interfaces.UsuarioDAO;
import entities.Usuario;

public class UsuarioDAOMySQL implements UsuarioDAO {

	@Override
	public boolean login(String dni, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int insert(Usuario u) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Usuario> findall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}
    
    

}
