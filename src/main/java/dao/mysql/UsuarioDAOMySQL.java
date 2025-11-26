package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DBConnection;
import dao.interfaces.UsuarioDAO;
import entities.Usuario;
import entities.Vehiculo;

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
		int resul =0;
		String sql = "INSERT INTO USUARIO (id, dni, password , nombre, rol ) VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    pst.setInt(1, 1);
		    pst.setString(2,"123456789");
		    pst.setBoolean(3,u.isPassword() );
		    pst.setString(4, "Carlos" );
		    pst.setString(5, "mecanico");

	        if (resul > 0) {
	            try (ResultSet rs = pst.getGeneratedKeys()) {
	                if (rs.next()) {
	                    resul = rs.getInt(1);
	                   
	                }
	            }
	        }
		
	        int rs = pst.executeUpdate();

			System.out.println("resultado de inserccion:" + rs);
		
		} catch (SQLException e) {
		     System.out.println(">NOK:" + e.getMessage());
		}
		return resul;
		
	}

	
	
	@Override
	public int update(Usuario u) {
			int resul =0;
			String sql = "UPDATE  Usuario ( password , nombre  ,rol VALUES (?, ?, ?,)";
			
			try {
				PreparedStatement pst = conn.prepareStatement(sql);
			    pst.setBoolean(1, u.isPassword());
			    pst.setString(2, u.getNombre());
	            pst.setString(3, u.getRol());
	           
	            resul =pst.executeUpdate();
	            
	            System.out.println(" Resultado de actualización " + resul);
			
			} catch (SQLException e) {
			     System.out.println(">NOK:" + e.getMessage());
			}
			return resul;
		
	
	}
	
	
	@Override
	public int delete(Usuario u) {
		// TODO Auto-generated method stub
		return 0;
	}
    
	
	
	
	
	@Override
	public ArrayList<Usuario> findall() {
		
		List<Usuario> lista = new ArrayList<>();
	    String sql = "SELECT * FROM usuario";

	    try (Connection conn = DBConnection.getInstance().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Usuario t = new Usuario();
	          
	            lista.add(t);
	        }
	    }
	    return (ArrayList<Usuario>) lista;
	}

	@Override
	public Usuario findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}



	



	
    
    

}
