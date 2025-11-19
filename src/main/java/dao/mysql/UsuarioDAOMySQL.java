package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBConnection;
import dao.interfaces.UsuarioDAO;
import entities.Usuario;

public class UsuarioDAOMySQL implements UsuarioDAO {
	private Connection conn;
	
	 public UsuarioDAOMySQL() throws SQLException {
		 conn = DBConnection.getInstance().getConnection(); 
	  }
	  
	
	
	@Override
	public boolean login(String dni, String password) {
		Usuario usuario = null;
		ResultSet res = null;
		
		
		String sql = "SELECT id,nombre, rol FROM Usuario WHERE dni = ? AND password = ?";
		PreparedStatement pst;
		
		try {
			pst = conn.prepareStatement(sql);
		    pst.setString(1, dni);
		    pst.setString(2, PasswordUtils.hashPassword(password));
		    res=pst.executeQuery();
		    
		    if (res.next()) {
		    	usuario = new Usuario();
		    	usuario.setId(res.getInt("id"));
		    	usuario.setDni(dni);
		    	usuario.setPassword(PasswordUtils.hashPasword(password));
		    	usuario.setNombre(res.getString("nombre"));
		    	usuario.setRol(res.getString("rol"));
		    }
		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	return usuario;
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
