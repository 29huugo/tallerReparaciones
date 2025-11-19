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
	public int insert(Vehiculo vehiculo) {
		int rc = 0;
		String sql = "INSERT INTO Vehiculo (matricula, marca, modelo , clienteid) VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    pst.setString(1, vehiculo.getMatricula());
		    pst.setString(2, vehiculo.getMarca());
		    pst.setString(3, vehiculo.getModelo());
		    pst.setInt(4,vehiculo.getCliente_id());
		    
		    int resul = pst.executeUpdate();

	        if (resul > 0) {
	            try (ResultSet rs = pst.getGeneratedKeys()) {
	                if (rs.next()) {
	                    rc = rs.getInt(1);
	                    vehiculo.setId(rc);
	                }
	            }
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rc;
	       
		}

	@Override
	public int update(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public List<Vehiculo> findall() {
		// TODO Auto-generated method stub
		return null;
	}

}
