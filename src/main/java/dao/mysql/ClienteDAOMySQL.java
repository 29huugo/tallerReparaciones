package dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBConnection;
import dao.interfaces.ClienteDAO;
import entities.Cliente;
public class ClienteDAOMySQL implements ClienteDAO {
	private Connection conn;
	
	public ClienteDAOMySQL() throws SQLException {
		 conn = DBConnection.getInstance().getConnection(); 
	 
	
	}
	  
	@Override
	public int insert(Cliente c) {
		int resul =0;
		String sql = "INSERT INTO Cliente (id, nombre, email  , dni,  telefono) VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
		    pst.setInt(1, c.getId());
		    pst.setString(2, c.getNombre() );
		    pst.setString(3, c.getEmail() );
		    pst.setString(4,c.getDni());
		    pst.setString(5, c.getTelefono());

	        
	        resul = pst.executeUpdate();

			System.out.println("resultado de inserccion:" + resul);
		
		} catch (SQLException e) {
		     System.out.println(">NOK:" + e.getMessage());
		}
		
		return resul;
		
		
	}

	

	@Override
	public int update(Cliente c) {
		int resul =0;
		String sql =  "UPDATE Cliente SET nombre = ?, email = ?, telefono = ? WHERE id = ?"; ;
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
		    pst.setString(1, c.getNombre());
		    pst.setString(2, c.getEmail());
            pst.setString(3, c.getTelefono());
            pst.setInt(4, c.getId());
            
            resul =pst.executeUpdate();
            
           
		
		} catch (SQLException e) {
		     System.out.println(">No se pudo actualizar el cliente con el id: " + c.getId()+ e.getMessage());
		} 
		
		return resul;
		
		
}

	@Override
	public int delete(Cliente c) {
         int filas = 0;
				String sqlDelete = " DELETE FROM CLIENTE  WHERE dni = ?;";;
				try {
					PreparedStatement pst = conn.prepareStatement(sqlDelete);
					pst.setString(1, c.getDni()); // 
				    filas = pst.executeUpdate();
					
					
					
				} catch (SQLException e) {
				
					e.printStackTrace();
		}
		
		return filas;
	}

	@Override
	public ArrayList<Cliente> findall() {
		
		ArrayList<Cliente> lista = new ArrayList<>();
	    String sql = "SELECT * FROM cliente"; 

	    try (
	        PreparedStatement ps = conn.prepareStatement(sql); 
	        ResultSet resul = ps.executeQuery();
	    ) {
	        while (resul.next()) {
	            
	            Cliente c = new Cliente();  
	            
	            c.setId(resul.getInt("id"));             
	            c.setDni(resul.getString("dni"));         
	            c.setNombre(resul.getString("nombre"));  
	            c.setEmail(resul.getString("email")); 
                c.setTelefono(resul.getString("telefono"));
	            lista.add(c); 
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener todos los clientes: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return lista;
		
		
		
		
	
		
	}

	@Override
	public Cliente findByDni(String dni) {
	    Cliente clienteEncontrado = null; 
	    
	  
	    String sql = "SELECT * FROM cliente WHERE dni = ?"; 
	    
	    try (
	       
	        PreparedStatement ps = conn.prepareStatement(sql);
	    ) {
	       
	        ps.setString(1, dni); 
	        
	    
	        try (ResultSet resul = ps.executeQuery()) {
	            
	          
	            if (resul.next()) {
	                clienteEncontrado = new Cliente();
	                
	               
	                clienteEncontrado.setId(resul.getInt("id"));
	                clienteEncontrado.setDni(resul.getString("dni"));
	                clienteEncontrado.setNombre(resul.getString("nombre"));
	                clienteEncontrado.setEmail(resul.getString("email")); 
                    clienteEncontrado.setTelefono(resul.getString("telefono"));
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println(" Error al buscar cliente por DNI: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    
	    return clienteEncontrado;
	}

	



}
