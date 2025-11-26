package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		int rc = 0;
		String sql = "INSERT INTO Vehiculo (matricula, marca, modelo , clienteid) VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    pst.setString(1, v.getMatricula());
		    pst.setString(2, v.getMarca());
		    pst.setString(3, v.getModelo());
		    pst.setInt(4,v.getCliente_id());
		    
		    int resul = pst.executeUpdate();

	        if (resul > 0) {
	            try (ResultSet rs = pst.getGeneratedKeys()) {
	                if (rs.next()) {
	                    rc = rs.getInt(1);
	                    v.setId(rc);
	                }
	            }
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rc;
	       
		}

	@Override
	public int update(Vehiculo v) {
		int resul =0;
		String sql = "UPDATE  Vehiculo ( matricula , Marca  , Modelo) VALUES (?, ?, ?,)";
		
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
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Vehiculo> findByMatricula() {
		// TODO Auto-generated method stub
		return null;
	}

}
