package dao.interfaces;

import java.util.ArrayList;

import entities.Usuario;

public interface UsuarioDAO  {
	boolean login(String dni, String password);
	int insert(Usuario u);
	ArrayList<Usuario> findall();
	Usuario findByNombre(String nombre);
}


