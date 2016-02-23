package colega.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import javax.sql.DataSource;

import colega.modelo.entidades.Colega;
import colega.modelo.negocio.ColegaDao;

public class MySqlColegaDao implements ColegaDao {
	
	private static final String INSERT_COLEGA = "INSERT INTO Colega (id,nombre,ciudad,fecha) VALUES (?,?,?,?)";
	private static final String DELETE_COLEGA = "DELETE FROM Colega WHERE id=?";
	private static final String UPDATE_COLEGA = "UPDATE Colega SET nombre=?, ciudad=?, fecha=? WHERE id=?";
	private static final String SELECT_COLEGA_BY_ID = "SELECT * FROM Colega WHERE id=?";
	private static final String SELECT_COLEGA = "SELECT * FROM Colega";
	
	private DataSource ds;
	
	public MySqlColegaDao(DataSource ds) {
		super();
		this.ds = ds;
	}

	@Override
	public void alta(Colega colega) throws SQLException {
		Connection connection = null;
		try {			
			connection = ds.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(INSERT_COLEGA);
			ps.setInt(1, colega.getId());
			ps.setString(2, colega.getNombre());
			ps.setString(3, colega.getCiudad());
			ps.setDate(4, colega.getFecha());
			
			ps.executeUpdate();

		}finally {			
			if (connection !=null){
				connection.close();
			}
		}
	}

	@Override
	public void bajaPorId(int id) throws SQLException {
		Connection connection = null;
		try {			
			connection = ds.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(DELETE_COLEGA);
			ps.setInt(1, id);
			
			ps.executeUpdate();

		}finally {			
			if (connection !=null){
				connection.close();
			}
		}
	}

	@Override
	public void modificar(Colega colega) throws SQLException {
		Connection connection = null;
		try {			
			connection = ds.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(UPDATE_COLEGA);			
			ps.setString(1, colega.getNombre());
			ps.setString(2, colega.getCiudad());
			ps.setDate(3, colega.getFecha());
			ps.setInt(4, colega.getId());
			
			ps.executeUpdate();

		}finally {			
			if (connection !=null){
				connection.close();
			}
		}
	}

	@Override
	public Colega consultaPorId(int id) throws SQLException {
		Connection connection = null;		
		try {			
			connection = ds.getConnection();
			PreparedStatement ps = connection.prepareStatement(SELECT_COLEGA_BY_ID);
			ps.setInt(1, id);
		
			ResultSet rs = ps.executeQuery();
		
			if(rs.first()){
				return  new Colega(id, rs.getString("nombre"), rs.getString("ciudad"), 
					rs.getDate("fecha"));		
			}	
			return null;
		
		}finally {
			if (connection !=null){
				connection.close();
			}
		}
	}

	@Override
	public Collection<Colega> consultaTodos() throws SQLException {
		Collection <Colega> resultado = new HashSet<>();
		
		Connection connection = null;
		
		try {			
			connection = ds.getConnection();
			PreparedStatement ps = connection.prepareStatement(SELECT_COLEGA);		
		
			ResultSet rs = ps.executeQuery();
		
			if(rs.first()){
				do{
					Colega colega = new Colega(rs.getInt("id"), rs.getString("nombre"), 
							rs.getString("ciudad"), rs.getDate("fecha"));				
					
					resultado.add(colega);
					
				}while(rs.next());		
			}	
			return null;
		
		}finally {
			if (connection !=null){
				connection.close();
			}
		}
	}
}
