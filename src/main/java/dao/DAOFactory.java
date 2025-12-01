package dao;

import java.sql.SQLException;

import dao.interfaces.ClienteDAO;
import dao.interfaces.ReparacionDAO;
import dao.interfaces.UsuarioDAO;
import dao.interfaces.VehiculoDAO;
public interface DAOFactory {

	public UsuarioDAO getUsuarioDAO() throws SQLException;
	public VehiculoDAO getVehiculoDAO() throws SQLException;
	public ClienteDAO getClienteDAO() throws SQLException;
	public ReparacionDAO getReparacionDAO() throws SQLException;
	
	
	
}
