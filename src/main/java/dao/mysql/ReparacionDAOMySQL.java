package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
				int resul = 0;
				String sql = "INSERT INTO Reparacion (descripcion, fecha_entrada, coste, vehiculo_id, usuario_id) VALUES (?, ?, ?, ?, ?)";
				
				try {
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.setString(1, r.getDescripcion());
					pst.setInt(2, r.getFecha_entrada());
					pst.setDouble(3,r.getCoste());
				    pst.setInt(4,r.getVehiculo_id());
				    pst.setInt(5, r.getUsuario_id());
				    
				    
				    

			         resul = pst.executeUpdate();

					System.out.println("resultado de inserccion:" + resul);
				
				} catch (SQLException e) {
				     System.out.println(">NOK:" + e.getMessage());
				}
				return resul;
				
			       
				}

	@Override
	public int update(Reparacion r) {
		int resul =0;
		String sql =" UPDATE Reparacion SET descripcion = ?, fecha_entrada = ?, coste = ?, vehiculo_id = ?, usuario_id = ? WHERE id = ?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
		    pst.setString(1, r.getDescripcion());
		    pst.setInt(2, r.getFecha_entrada());
            pst.setDouble(3, r.getCoste());
            pst.setInt(4, r.getVehiculo_id());
            pst.setInt(5, r.getUsuario_id());
            resul =pst.executeUpdate();
            
           
		
		} catch (SQLException e) {
		     System.out.println(">Error en actualizar datos de reparación:" + e.getMessage());
		}
		return resul;
		
	}

	@Override
	public int delete(Reparacion r) {
	    int filas = 0;
	    
	    String sqlDelete = " DELETE FROM Reparacion WHERE id = ?";
	    
	    try (PreparedStatement pst = conn.prepareStatement(sqlDelete)) {
	        
	        pst.setInt(1, r.getId()); 
	        
	        filas = pst.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return filas;
	}
	@Override
	public ArrayList<Reparacion> findall() {
	    
	    ArrayList<Reparacion> lista = new ArrayList<>();
	    String sql = "SELECT id, id_vehiculo, id_usuario, descripcion, fecha_entrada, coste FROM reparacion"; 

	    try (
	        PreparedStatement ps = conn.prepareStatement(sql); 
	        ResultSet resul = ps.executeQuery();
	    ) {
	        while (resul.next()) {
	            
	            Reparacion r = new Reparacion();  
	            
	            r.setId(resul.getInt("id"));             
	            r.setVehiculo_id(resul.getInt("id_vehiculo")); 
	            r.setUsuario_id(resul.getInt("id_usuario"));
	            
	            r.setDescripcion(resul.getString("descripcion")); 
	            r.setFecha_entrada(resul.getInt("fecha_entrada"));
	            r.setCoste(resul.getDouble("coste"));
	            
	            lista.add(r);
	        }
	    } catch (SQLException e) {
	    }
	    
	    return lista;
	}

	@Override
	public ArrayList<Reparacion> findByUsuario_id(int id) {
	    ArrayList<Reparacion> lista = new ArrayList<>();
	    String sql = "SELECT id, id_vehiculo, id_usuario, descripcion, fecha_entrada, coste FROM reparacion WHERE id_usuario = ?";
	    
	    try (
	        PreparedStatement ps = conn.prepareStatement(sql); 
	    ) {
	        ps.setInt(1, id);
	        
	        try (ResultSet resul = ps.executeQuery()) {
	            while (resul.next()) {
	                
	                Reparacion r = new Reparacion();  
	                
	                r.setId(resul.getInt("id"));             
	                r.setVehiculo_id(resul.getInt("id_vehiculo")); 
	                r.setUsuario_id(resul.getInt("id_usuario"));
	                
	                r.setDescripcion(resul.getString("descripcion")); 
	                r.setFecha_entrada(resul.getInt("fecha_entrada"));
	                r.setCoste(resul.getDouble("coste"));
	                
	                lista.add(r);
	            }
	        }
	    } catch (SQLException e) {
	    }
	    
	    return lista;
	}

	@Override
	public Reparacion findById(int idReparacion) {
	    Reparacion reparacion = null;
	    String sql = "SELECT id, id_vehiculo, id_usuario, descripcion, fecha_entrada, coste FROM reparacion WHERE id = ?";
	    
	    try (
	        PreparedStatement ps = conn.prepareStatement(sql); 
	    ) {
	        ps.setInt(1, idReparacion);
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                reparacion = new Reparacion();
	                
	                reparacion.setId(rs.getInt("id"));             
	                reparacion.setVehiculo_id(rs.getInt("id_vehiculo")); 
	                reparacion.setUsuario_id(rs.getInt("id_usuario"));
	                
	                reparacion.setDescripcion(rs.getString("descripcion")); 
	                reparacion.setFecha_entrada(rs.getInt("fecha_entrada"));
	                reparacion.setCoste(rs.getDouble("coste"));
	            }
	        }
	    } catch (SQLException e) {
	    }
	    
	    return reparacion;
	}





}
