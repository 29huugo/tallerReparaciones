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
	            v.setMatricula(resul.getString("matricula"));
	            v.setCliente_id(resul.getInt("clienteid"));
	            lista.add(v); 
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener todos los usuarios: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return lista;
	}



	@Override
    public Vehiculo findByMatricula(String matricula) { 
        Vehiculo  vehiculoEncontrado = null;
        String sql = "SELECT * FROM Vehiculo WHERE matricula = ?";
        
        try (
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, matricula); 
            
            try (ResultSet resul = ps.executeQuery()) {
                if (resul.next()) {
                	vehiculoEncontrado = new Vehiculo();
                	vehiculoEncontrado.setId(resul.getInt("id"));
                	vehiculoEncontrado.setMatricula(resul.getString("matricula"));
                	vehiculoEncontrado.setMarca(resul.getString("marca"));
                	vehiculoEncontrado.setModelo(resul.getString("modelo"));
                	vehiculoEncontrado.setCliente_id(resul.getInt("clienteid"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar vehículo por matrícula: " + e.getMessage());
            e.printStackTrace();
        }
        
        return vehiculoEncontrado;
    }




}
