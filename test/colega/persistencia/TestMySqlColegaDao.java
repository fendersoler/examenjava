package colega.persistencia;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import colega.modelo.entidades.Colega;

public class TestMySqlColegaDao {		
	
	@Test
	public void test()  {		
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/examenjava");
		ds.setUsername("curso");
		ds.setPassword("curso");
		ds.setDriverClassName("com.mysql.jdbc.Driver");
				
		//Datos de entrada
		Colega colega1 = new Colega(1, "Pepe", "Mota", new Date(0));
		Colega colega2 = new Colega(2, "Maria", "Cuervo", new Date(0));
		Colega colega3 = new Colega(3, "Jose", "Hellin", new Date(0));
		
		int idDelColegaConIdUno = 1;
		int idDelColegaConIdDos = 2;
		int idDelColegaConIdTres = 3;
		
		int tamanoEsperadoDeLaTablaDeColegasDespuesDeTresInserciones=3;
		
		int tamanoEsperadoDeLaTablaDeColegasDespuesDeBorrarUno=2;
	
		
		//sut		
		MySqlColegaDao sut = new MySqlColegaDao(ds);		
		
		//Ejecucion:
		try {
			sut.alta(colega1);
			sut.alta(colega2);
			sut.alta(colega3);
			
			Colega idObtenidoDelColegaConIdUno = sut.consultaPorId(colega1.getId());
			Colega idObtenidoDelColegaConIdDos = sut.consultaPorId(colega2.getId());
			Colega idObtenidoDelColegaConIdTres = sut.consultaPorId(colega3.getId());	
			
			Collection<Colega>todosLosColegas = sut.consultaTodos();
		
			//Asertos:			
			assertEquals (idDelColegaConIdUno, idObtenidoDelColegaConIdUno);
			assertEquals (idDelColegaConIdDos, idObtenidoDelColegaConIdDos);
			assertEquals (idDelColegaConIdTres, idObtenidoDelColegaConIdTres);		
			
			assertEquals (tamanoEsperadoDeLaTablaDeColegasDespuesDeTresInserciones, 
					todosLosColegas.size());
			
			//Baja
			sut.bajaPorId(idDelColegaConIdTres);
			Collection<Colega>todosMenosElBorrado = sut.consultaTodos();
			assertEquals (tamanoEsperadoDeLaTablaDeColegasDespuesDeBorrarUno,todosMenosElBorrado.size());
			
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Se produce un SQLException y no deberia: " + e.getMessage());
		}		
		
		//fail("Not yet implemented");
	}
}