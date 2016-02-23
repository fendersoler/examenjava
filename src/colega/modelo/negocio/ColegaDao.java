package colega.modelo.negocio;

import java.sql.SQLException;
import java.util.Collection;

import colega.modelo.entidades.Colega;

public interface ColegaDao {
	void alta(Colega colega) throws SQLException;
	void bajaPorId(int id) throws SQLException;
	void modificar(Colega colega) throws SQLException;
	Colega consultaPorId(int id) throws SQLException;
	Collection<Colega> consultaTodos() throws SQLException;
}