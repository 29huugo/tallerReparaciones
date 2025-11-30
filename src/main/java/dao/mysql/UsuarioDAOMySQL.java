package dao.mysql;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBConnection;
import dao.interfaces.UsuarioDAO;
import entities.Usuario;
import utils.PasswordUtils;

public class UsuarioDAOMySQL implements UsuarioDAO {
	private Connection conn;
	
	 public UsuarioDAOMySQL() throws SQLException {
		 conn = DBConnection.getInstance().getConnection(); 
	  }
	  
	 public boolean login(String dni, String password) {
		    
		    boolean loginExitoso = false;
		    Usuario usuario = null; 
		    ResultSet resul = null;
		    PreparedStatement pst = null; 
		    String sql = "SELECT id, nombre, rol FROM Usuario WHERE dni = ? AND password = ?";
		    
		    try {
		        pst = conn.prepareStatement(sql);
		        pst.setString(1, dni);
		        pst.setString(2, PasswordUtils.hashPassword(password)); 
		        
		        resul = pst.executeQuery();
		        
		       
		        if (resul.next()) {
		            loginExitoso = true; 
		            usuario = new Usuario();
		            usuario.setId(resul.getInt("id"));
		            usuario.setDni(dni);
		            usuario.setPassword(loginExitoso); 
		            usuario.setNombre(resul.getString("nombre"));
		            usuario.setRol(resul.getString("rol"));
		            
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		        loginExitoso = false; 
		    } finally {
		        
		        if (resul != null) { try { resul.close(); } catch (SQLException e) { e.printStackTrace(); } }
		        if (pst != null) { try { pst.close(); } catch (SQLException e) { e.printStackTrace(); } }
		       
		    }
		    return loginExitoso;
		}
	
	

	@Override
	public int insert(Usuario u) {
		int resul =0;
		String sql = "INSERT INTO USUARIO (id, dni, password , nombre, rol ) VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
		    pst.setInt(1, 1);
		    pst.setString(2,"123456789");
		    pst.setBoolean(3,u.isPassword() );
		    pst.setString(4, "Carlos" );
		    pst.setString(5, "mecanico");


	         resul = pst.executeUpdate();

			System.out.println("resultado de inserccion:" + resul);
		
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
		String sqlDelete = " DELETE FROM CLIENTE  WHERE dni = ?;";;
		try {
			PreparedStatement pst = conn.prepareStatement(sqlDelete);
			pst.setString(1, u.getDni()); // 
			int filas = pst.executeUpdate();
			
			if (filas > 0) {
				System.out.println("> OK. Persona con dni 1 eliminada correctamente.");
			} else {
				System.out.println("> NOK. Persona con dni 1 no se encuentra en la base de datos.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
}

return 0;
		
	}
    
	
	
	
	
	@Override
	public ArrayList<Usuario> findall () {
		
		ArrayList<Usuario> usuarios= new ArrayList<>();
	    String sql = "SELECT * FROM usuario"; 

	    try (
	        PreparedStatement ps = conn.prepareStatement(sql); 
	        ResultSet resul = ps.executeQuery();
	    ) {
	        while (resul.next()) {
	            
	            Usuario u = new Usuario();  
	            
	            u.setId(resul.getInt("id"));             
	            u.setDni(resul.getString("dni"));         
	            u.setNombre(resul.getString("nombre"));   
	            u.setPassword(resul.getBoolean("password")); 
	            u.setRol(resul.getString("rol"));
	            usuarios.add(u); 
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener todos los usuarios: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return usuarios;
	}

	@Override
	public Usuario findByNombre(String nombre) {
		
		return null;
	}


	



	
    
    

}
