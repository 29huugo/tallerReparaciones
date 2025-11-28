package dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    pst.setInt(1, c.getId());
		    pst.setString(2, c.getNombre() );
		    pst.setString(3, c.getEmail() );
		    pst.setString(4,c.getDni());
		    pst.setString(5, c.getTelefono());

	        if (resul > 0) {
	            try (int resul = pst.getGeneratedKeys()) {
	                if (resul.next()) {
	                    resul = resul.getId(1);
	                   
	                }
	            }
	        }
		
	        int resul = pst.executeUpdate();

			System.out.println("resultado de inserccion:" + resul);
		
		} catch (SQLException e) {
		     System.out.println(">NOK:" + e.getMessage());
		}
		return resul;
		
		
	}

	

	@Override
	public int update(Cliente c) {
		int resul =0;
		String sql = "UPDATE  Cliente ( nombre, email  ,  telefono) VALUES (?, ?, ?,)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
		    pst.setString(1, c.getNombre());
		    pst.setString(2, c.getEmail());
            pst.setString(3, c.getTelefono());
           
            resul =pst.executeUpdate();
            
            System.out.println(" Resultado de la actualización " + resul);
		
		} catch (SQLException e) {
		     System.out.println(">NOK:" + e.getMessage());
		}
		return resul;
		
		
}

	@Override
	public int delete(Cliente c) {

				String sqlDelete = " DELETE Cliente FROM WHERE dni = ?;";;
				try {
					PreparedStatement pst = conn.prepareStatement(sqlDelete);
					pst.setInt(1, c.getId()); // 
					int filas = pst.executeUpdate();
					
					if (filas > 0) {
						System.out.println("> OK. Persona con dni 1 eliminada correctamente.");
					} else {
						System.out.println("> NOK. Persona con dni 1 no se encuentra en la base de datos.");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public ArrayList<Cliente> findall() {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente  findByDni(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	



}
