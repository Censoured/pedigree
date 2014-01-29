
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import mpr.proj.*;
import mpr.proj.pedigree.*;

public class jUnitTest {

	//testy crud
	@Test
	public void updateBreederTest() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");
		Collections.setConnection(con);
		Breeder buffer = Collections.breederID(1);
		CrudOperations.updateBreeder(new Breeder(0, "Maciek" , new Country(1, "Polska", "PL")), 1);
		assertEquals(Collections.breederID(1).getName(), "Maciek");
        CrudOperations.updateBreeder(buffer, 1);
        assertEquals(Collections.breederID(1).getId(),1);
        assertEquals(Collections.breederID(1).getCountry().getId(), 1);
	}
	
	@Test
	public void updateCountryTest() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");
		Collections.setConnection(con);
		Country buffer = Collections.countryID(1);
		CrudOperations.updateCountry(new Country(0, "Meksyk", "ME"), 1);
		assertEquals("Meksyk", Collections.countryID(1).getName());
		assertEquals(Collections.countryID(1).getCode(), "ME");
        CrudOperations.updateCountry(buffer, 1);
        assertEquals(Collections.countryID(1).getId(),1);
	}
	//ojciec tylko ogier
	
	//matka tylko klacz
	
	//potomstwo to konie ktorych jest ojcem
	
	// -||- matka
	
	//drzewo jest poprawnie zbudowane (??)
}