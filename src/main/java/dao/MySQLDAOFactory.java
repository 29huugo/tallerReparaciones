package dao;

import dao.interfaces.ClienteDAO;
import dao.interfaces.ReparacionDAO;
import dao.interfaces.UsuarioDAO;
import dao.interfaces.VehiculoDAO;

public class MySQLDAOFactory implements DAOFactory {

	@Override
	public UsuarioDAO getUsuarioDAO() {
		// TODO Auto-generated method stub
		return UsuarioDAOMySQL;
	}

	@Override
	public VehiculoDAO getVehiculoDAO() {
		// TODO Auto-generated method stub
		return VehiculoDAOMySQL;
	}

	@Override
	public ClienteDAO getClienteDAO() {
		// TODO Auto-generated method stub
		return ClienteDAOMySQL;
	}

	@Override
	public ReparacionDAO getReparacionDAO() {
		// TODO Auto-generated method stub
		return ReparacionDAOMySQL;
	}

}
