package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DBConnection;
import dao.interfaces.VehiculoDAO;
import entities.Vehiculo;


public class VehiculoDAOMySQL implements VehiculoDAO  {
  private Connection conn;
	
  public VehiculoDAOMySQL() throws SQLException {
	 conn = DBConnection.getInstance().getConnection(); 
  }
  
  
  
  @Override
	public int insert(Vehiculo v) {
		int resul = 0;
		String sql = "INSERT INTO Vehiculo (matricula, marca, modelo , clienteid) VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
		    pst.setString(1, v.getMatricula());
		    pst.setString(2, v.getMarca());
		    pst.setString(3, v.getModelo());
		    pst.setInt(4,v.getCliente_id());
		    
		    
		    
		    

	         resul = pst.executeUpdate();

			System.out.println("resultado de inserccion:" + resul);
		
		} catch (SQLException e) {
		     System.out.println(">NOK:" + e.getMessage());
		}
		return resul;
		
	       
		}

	@Override
	public int update(Vehiculo v) {
		int resul =0;
		String sql = "UPDATE Vehiculo SET marca = ?, modelo = ? WHERE matricula = ?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
		    pst.setString(1, v.getMatricula());
		    pst.setString(2, v.getMarca());
            pst.setString(3, v.getModelo());
           
            resul =pst.executeUpdate();
            
            System.out.println(" Resultado de actualización " + resul);
		
		} catch (SQLException e) {
		     System.out.println(">NOK:" + e.getMessage());
		}
		return resul;
	}



	@Override
	public List<Vehiculo> findall() {
		ArrayList<Vehiculo> lista = new ArrayList<>();
	    String sql = "SELECT * FROM Vehiculo"; 

	    try (
	        PreparedStatement ps = conn.prepareStatement(sql); 
	        ResultSet resul = ps.executeQuery();
	    ) {
	        while (resul.next()) {
	            
	            Vehiculo v = new Vehiculo();  
	            
	            v.setId(resul.getInt("id"));             
	            v.setMarca(resul.getString("marca"));         
	            v.setModelo(resul.getString("modelo"));  
	            
	            lista.add(v); 
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener todos los usuarios: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return lista;
	}



	@Override
	public List<Vehiculo> findByMatricula() {
		// TODO Auto-generated method stub
		return null;
	}




}
