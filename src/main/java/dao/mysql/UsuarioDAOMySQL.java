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
		    String sql = "SELECT id, dni, password, nombre, rol FROM Usuario WHERE dni = ?";
		    
		    try (PreparedStatement pst = conn.prepareStatement(sql)) {
		        
		        pst.setString(1, dni);
		        
		        try (ResultSet resul = pst.executeQuery()) {
			        
			        if (resul.next()) {
			            String storedHash = resul.getString("password");
			            
			            
			            if (PasswordUtils.verifyPassword(password, storedHash)) {
	                        Usuario usuario = new Usuario();
	                        usuario.setId(resul.getInt("id"));
	                        usuario.setDni(dni);
	                        usuario.setPassword(storedHash); 
	                        usuario.setNombre(resul.getString("nombre"));
	                        usuario.setRol(resul.getString("rol"));
	                    
			                return true;
			            }
			        }
		        }
		    } catch (SQLException e) {
	            System.err.println("Error  durante el inicio de sesión: " + e.getMessage());
		        e.printStackTrace();
		    }
		   
		    return false;
		}
	
	

	@Override
	public int insert(Usuario u) {
		int resul =0;
		String sql = "INSERT INTO USUARIO (id, dni, password , nombre, rol ) VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
		    pst.setInt(1, 1);
		    pst.setString(2,"123456789");
		    pst.setString(3,u.getPassword() );
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
	    int resul = 0;
	    String sql = "UPDATE Usuario SET password = ?, nombre = ?, rol = ? WHERE id = ?";
	    
	    try (
	        PreparedStatement pst = conn.prepareStatement(sql);
	    ) {
	        pst.setString(1, u.getPassword());
	        pst.setString(2, u.getNombre());
	        pst.setString(3, u.getRol());
	        pst.setInt(4, u.getId());
	       
	        resul = pst.executeUpdate();
	        
	    } catch (SQLException e) {
	    }
	    return resul;
	}	
	

	
	
	
	
	@Override
	public int delete(Usuario u) {
		int filas = 0;
		String sqlDelete = " DELETE FROM USUARIO  WHERE dni = ?;";;
		try {
			PreparedStatement pst = conn.prepareStatement(sqlDelete);
			pst.setString(1, u.getDni()); // 
		 filas = pst.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("Error al eliminar usuario con DNI " + u.getDni() + ": " + e.getMessage());
			e.printStackTrace();
}

return filas;
		
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
	            u.setPassword(resul.getString("password")); 
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
		Usuario usuarioEncontrado = null;
        String sql = "SELECT * FROM Usuario WHERE nombre = ?";
        
        try (
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, nombre);
            
            try (ResultSet resul = ps.executeQuery()) {
                if (resul.next()) {
                    usuarioEncontrado = new Usuario();
                    usuarioEncontrado.setId(resul.getInt("id"));
                    usuarioEncontrado.setDni(resul.getString("dni"));
                    usuarioEncontrado.setNombre(resul.getString("nombre"));
                    usuarioEncontrado.setPassword(resul.getString("password"));
                    usuarioEncontrado.setRol(resul.getString("rol"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario por nombre: " + e.getMessage());
            e.printStackTrace();
        }
        return usuarioEncontrado;
	}


	@Override
	public Usuario findByDni(String Dni) {
		Usuario usuarioEncontrado = null;
        String sql = "SELECT * FROM Usuario WHERE nombre = ?";
        
        try (
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, Dni);
            
            try (ResultSet resul = ps.executeQuery()) {
                if (resul.next()) {
                    usuarioEncontrado = new Usuario();
                    usuarioEncontrado.setId(resul.getInt("id"));
                    usuarioEncontrado.setDni(resul.getString("dni"));
                    usuarioEncontrado.setNombre(resul.getString("nombre"));
                    usuarioEncontrado.setPassword(resul.getString("password"));
                    usuarioEncontrado.setRol(resul.getString("rol"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario por nombre: " + e.getMessage());
            e.printStackTrace();
        }
        return usuarioEncontrado;
	}




	
    
    

}
