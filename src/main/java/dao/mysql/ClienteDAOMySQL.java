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
		try {

			ResultSet resultado = null;

			conn.setAutoCommit(false);

			String sql = "SELECT id_cliente, nombre, telefono, email, dni_cliente FROM persona WHERE dni_cliente > ?";

			PreparedStatement pst = conn.prepareStatement(

					sql,

					ResultSet.TYPE_SCROLL_SENSITIVE,

					ResultSet.CONCUR_UPDATABLE); 

			

			pst.setInt(1, 15);

			resultado = pst.executeQuery();

			

			while (resultado.next()) {

				String nombre = resultado.getString("nombre");

				int edadActual = resultado.getInt("edad");

				resultado.updateInt("edad", edadActual + 5);

				resultado.updateRow();

				System.out.println("> La edad de la persona " + nombre + " se modificado a " +  resultado.getInt("edad"));

			}

			

			conn.commit();

			System.out.println("> Cambios confirmados correctamente");

			

		} catch (SQLException e) {

			if (conn != null) {

				try {

					conn.rollback();

					System.out.println("> Cambios confirmados correctamente");

				} catch (SQLException e1) {

					System.out.println("> NOK:" + e.getMessage());

				

				

			}

		 

		finally {

			if (conn != null) {

				try {

		
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
