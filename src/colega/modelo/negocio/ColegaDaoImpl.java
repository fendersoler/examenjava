package colega.modelo.negocio;

import java.sql.SQLException;
import java.util.Collection;

import colega.modelo.entidades.Colega;

public class ColegaDaoImpl implements ColegaDao {

	private ColegaDao dao;	
	
	public ColegaDaoImpl(ColegaDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public void alta(Colega colega) throws SQLException {
		dao.alta(colega);

	}

	@Override
	public void bajaPorId(int id) throws SQLException {
		dao.bajaPorId(id);

	}

	@Override
	public void modificar(Colega colega) throws SQLException {
		dao.modificar(colega);

	}

	@Override
	public Colega consultaPorId(int id) throws SQLException {
		return dao.consultaPorId(id);
	}

	@Override
	public Collection<Colega> consultaTodos() throws SQLException {		
		return dao.consultaTodos();
	}
}