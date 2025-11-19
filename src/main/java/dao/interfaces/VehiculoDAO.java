package dao.interfaces;


import java.util.List;

import entities.Vehiculo;

public interface VehiculoDAO {
	int insert(Vehiculo vehiculo);
    int update ( Vehiculo vehiculo);
	List <Vehiculo> findall();
	


}