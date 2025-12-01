package dao.interfaces;

import java.util.ArrayList;
import entities.Reparacion;

public interface ReparacionDAO {
	int insert (entities.Reparacion C);
	int update(entities.Reparacion C);
	int delete(entities.Reparacion C);
	ArrayList<Reparacion> findall();
	Reparacion findById(int idReparacion);
	ArrayList<Reparacion> findByUsuario_id(int id);

}
