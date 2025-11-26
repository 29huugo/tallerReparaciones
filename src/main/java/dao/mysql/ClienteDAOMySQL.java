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
		String sql = "INSERT INTO Cliente (id, nombre, email  , dni,  telefono) VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    pst.setInt(1, 1);
		    pst.setString(2,"Hugo" );
		    pst.setInt(3, 19 );
		    pst.setString(4,"123456789");
		    pst.setString(5, "555388791");

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
	public int update(Cliente c) {
		int resul =0;
		String sql = "UPDATE  Cliente ( nombre, email  ,  telefono) VALUES (?, ?, ?,)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
		    pst.setString(1, c.getNombre());
		    pst.setString(2, c.getEmail());
            pst.setString(3, c.getTelefono());
           
            resul =pst.executeUpdate();
            
            System.out.println(" Resultado de actualización " + resul);
		
		} catch (SQLException e) {
		     System.out.println(">NOK:" + e.getMessage());
		}
		return resul;
		
		

			

				

		
	}

	@Override
	public int delete(String dni) {

				String sqlDelete = " BORRAR Cliente FROM WHERE dni = ?;";;
				try {
					PreparedStatement pst = conn.prepareStatement(sqlDelete);
					pst.setInt(1, 1); // borrar id
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
