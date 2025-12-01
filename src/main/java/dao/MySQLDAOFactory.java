package dao;

import java.sql.SQLException;

import dao.interfaces.ClienteDAO;
import dao.interfaces.ReparacionDAO;
import dao.interfaces.UsuarioDAO;
import dao.interfaces.VehiculoDAO;
import dao.mysql.ClienteDAOMySQL;
import dao.mysql.ReparacionDAOMySQL;
import dao.mysql.UsuarioDAOMySQL;
import dao.mysql.VehiculoDAOMySQL;

public class MySQLDAOFactory implements DAOFactory {

	@Override
	public UsuarioDAO getUsuarioDAO() throws SQLException {
		// TODO Auto-generated method stub
		return  new UsuarioDAOMySQL();
	}

	@Override
	public VehiculoDAO getVehiculoDAO() throws SQLException {
		// TODO Auto-generated method stub
		return  new VehiculoDAOMySQL();
	}

	@Override
	public ClienteDAO getClienteDAO() throws SQLException {
		// TODO Auto-generated method stub
		return  new ClienteDAOMySQL();
	}

	@Override
	public ReparacionDAO getReparacionDAO() throws SQLException {
		// TODO Auto-generated method stub
		return  new ReparacionDAOMySQL();
	}

}
