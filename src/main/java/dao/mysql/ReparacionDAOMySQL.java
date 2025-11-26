package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.DBConnection;
import dao.interfaces.ReparacionDAO;
import entities.Reparacion;

public class ReparacionDAOMySQL implements ReparacionDAO {
	private Connection conn;
	
	 public ReparacionDAOMySQL() throws SQLException {
		 conn = DBConnection.getInstance().getConnection(); 
	  }
	@Override
	public int insert(Reparacion r) {
		int rc = 0;
		String sql = "INSERT INTO  Reparacion (id, descripcion, fecha_entrada , coste ) VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    pst.setInt(1, r.getId());
		    pst.setString(2, r.getDescripcion());
		    pst.setInt(3, r.getFecha_entrada());
		    pst.setInt(4,r.getCoste());
		    
		    int resul = pst.executeUpdate();

	        if (resul > 0) {
	            try (ResultSet rs = pst.getGeneratedKeys()) {
	                if (rs.next()) {
	                    rc = rs.getInt(1);
	                    r.setId(rc);
	                }
	            }
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rc;
	}

	@Override
	public int update(Reparacion r) {
		int resul =0;
		String sql = "UPDATE  Vehiculo ( matricula , Marca  , Modelo) VALUES (?, ?, ?,)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
		    pst.setString(1, r.getDescripcion());
		    pst.setInt(2, r.getFecha_entrada());
            pst.setInt(3, r.getCoste());
           
            resul =pst.executeUpdate();
            
            System.out.println(" Resultado de actualización " + resul);
		
		} catch (SQLException e) {
		     System.out.println(">NOK:" + e.getMessage());
		}
		return resul;
		
	}

	@Override
	public int delete(Reparacion r) {

		String sqlDelete = " BORRAR Reparacion FROM WHERE dni = ?;";;
		try {
			PreparedStatement pst = conn.prepareStatement(sqlDelete);
			pst.setInt(1, 1); 
			int filas = pst.executeUpdate();
			
			if (filas > 0) {
				System.out.println("> OK. Reparacion con id 1 eliminada correctamente.");
			} else {
				System.out.println("> NOK. Reparacion con id 1 no se encuentra en la base de datos.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
}

        return 0;
		
	}

}
