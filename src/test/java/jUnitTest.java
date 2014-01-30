import static org.junit.Assert.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import mpr.proj.*;
import mpr.proj.pedigree.*;

public class jUnitTest {
	
	public boolean canBeDam(Horse child, Horse parent)	{
		if(child.getDob().getYear()-parent.getDob().getYear()>3)	{
			return true;
		}
		else return false;
	}
	
	public boolean canBeSire(Horse child, Horse parent)	{
		if(child.getDob().getYear()-parent.getDob().getYear()>2)	{
			return true;
		}
		else return false;
	}
	
	public boolean horseCanBeParent(Horse parent, Horse child)	{
		if(child.getDob().getYear()- parent.getDob().getYear()< 30)	{
			return true;
		}
		else return false;
	}
	
	//potomek mlodszy od rodzicow
	@Test
	public void horseMayBeParent() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");
		Collections.setConnection(con);
		CrudOperations.setConnection(con);
		boolean buffer = horseCanBeParent(Collections.horseID(24), Collections.horseID(26));
		boolean buffer1 = horseCanBeParent(Collections.horseID(25), Collections.horseID(26));
		assertEquals(buffer, true);
		assertEquals(buffer1, true);
	}
	
	//rodzice nie starsi niz 30 lat
	@Test
	public void childIsYouger() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");
		Collections.setConnection(con);
		CrudOperations.setConnection(con);
		boolean buffer = canBeDam(Collections.horseID(26), Collections.horseID(24));
		boolean buffer1 = canBeDam(Collections.horseID(26), Collections.horseID(25));
		assertEquals(buffer, true);
		assertEquals(buffer1, true);
	}
	
	//matka mare
	@Test
	public void horseIsMare() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");
		Collections.setConnection(con);
		CrudOperations.setConnection(con);
		Sex buffer = Collections.horseID(26).getDam().getSex();
		assertEquals(buffer, Sex.MARE);
	}
	
	//ojciec stallion
	@Test
	public void horseIsStallion()	throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");
		Collections.setConnection(con);
		CrudOperations.setConnection(con);
		Sex buffer = Collections.horseID(7).getSire().getSex();
		assertEquals(buffer, Sex.STALLION);
	}
	
	//potomstwo ojciec, matka
	@Test
	public void horseIsParent()	throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");
		Collections.setConnection(con);
		CrudOperations.setConnection(con);
		Horse buffer = Collections.horseID(26);
		Horse buffer1 = Collections.horseID(25);
		Horse buffer2 = Collections.horseID(24);
		assertEquals(buffer.getSire().getID(), buffer1.getID());
		assertEquals(buffer.getDam().getID(), buffer2.getID());
	}
	
	@Test 
	public void crudCheckColor() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");
		Collections.setConnection(con);
		CrudOperations.setConnection(con);
		//create
		Color buffer = CrudOperations.addColor(new Color(30,"zielony", "zie"), 30);
		Color buffer1 = Collections.colorID(30);
		assertEquals(buffer.getLname(), buffer1.getLname());
		assertEquals(buffer.getSname(), buffer1.getSname());
		//update
		buffer = CrudOperations.updateColor(new Color(30, "czerwony", "cze"), 30);
		buffer1 = Collections.colorID(30);
		assertEquals(buffer.getLname(), buffer1.getLname());
		assertEquals(buffer.getSname(), buffer1.getSname());
		//delete
		CrudOperations.deleteColor(30);
		buffer = Collections.colorID(30);
		assertEquals(buffer, (Color)null);
	}
	
	@Test 
	public void crudCheckCountry() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");
		Collections.setConnection(con);
		CrudOperations.setConnection(con);
		//create
		Country buffer = CrudOperations.addCountry(new Country(30, "Jamajka", "JA"), 30);
		Country buffer1 = Collections.countryID(30);
		assertEquals(buffer.getCode(), buffer1.getCode());
		assertEquals(buffer.getName(), buffer1.getName());
		//update
		buffer = CrudOperations.updateCountry(new Country(30, "Argentyna", "AR"), 30);
		buffer1 = Collections.countryID(30);
		assertEquals(buffer.getCode(), buffer1.getCode());
		assertEquals(buffer.getName(), buffer1.getName());
		//delete
		CrudOperations.deleteCountry(30);
		buffer = Collections.countryID(30);
		assertEquals(buffer, (Country)null);
	}
	
	@Test 
	public void crudCheckBreeder() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");
		Collections.setConnection(con);
		CrudOperations.setConnection(con);
		//create
		Breeder buffer = CrudOperations.addBreeder(new Breeder(30, "Dominika", Collections.countryID(1)), 30);
		Breeder buffer1 = Collections.breederID(30);
		assertEquals(buffer.getCountry().getName(), buffer1.getCountry().getName());
		assertEquals(buffer.getName(), buffer1.getName());
		//update
		buffer = CrudOperations.updateBreeder(new Breeder(30, "Aleksandra", Collections.countryID(2)), 30);
		buffer1 = Collections.breederID(30);
		assertEquals(buffer.getCountry().getName(), buffer1.getCountry().getName());
		assertEquals(buffer.getName(), buffer1.getName());
		//delete
		CrudOperations.deleteBreeder(30);
		buffer = Collections.breederID(30);
		assertEquals(buffer, (Breeder)null);
	}
	
	@Test 
	public void crudCheckHorse() throws SQLException, ParseException {
		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");
		Collections.setConnection(con);
		CrudOperations.setConnection(con);
		//create
		Horse horse1 = new Horse(50, 
				"Maicey", 
				Sex.MARE, 
				new DateOfBirth(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1992-09-27").getTime()), false), 
				Collections.colorID(2), 
				Collections.horseID(25), 
				Collections.horseID(24), 
				Collections.breederID(1));
		Horse buffer = CrudOperations.addHorse(horse1, 50);
		Horse buffer1 = Collections.horseID(50);
		assertEquals(buffer.getName(), buffer1.getName());
		assertEquals(buffer.getBreeder().getName(), buffer1.getBreeder().getName());
		assertEquals(buffer.getDob().getYear(), buffer1.getDob().getYear());
		assertEquals(buffer.getSex(), buffer1.getSex());
		assertEquals(buffer.getSire().getName(), buffer1.getDam().getName());
		assertEquals(buffer.getDam().getName(), buffer1.getSire().getName());
		assertEquals(buffer.getColor().getLname(), buffer1.getColor().getLname());
		//update
		horse1 = new Horse(50, "Josh", Sex.STALLION, new DateOfBirth(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1994-03-14").getTime()), false), Collections.colorID(4), Collections.horseID(22), Collections.horseID(21), Collections.breederID(1));
		buffer = CrudOperations.updateHorse(horse1, 50);
		buffer1 = Collections.horseID(50);
		assertEquals(buffer.getName(), buffer1.getName());
		assertEquals(buffer.getBreeder().getName(), buffer1.getBreeder().getName());
		assertEquals(buffer.getDob().getYear(), buffer1.getDob().getYear());
		assertEquals(buffer.getSex(), buffer1.getSex());
		assertEquals(buffer.getSire().getName(), buffer1.getDam().getName());
		assertEquals(buffer.getDam().getName(), buffer1.getSire().getName());
		assertEquals(buffer.getColor().getLname(), buffer1.getColor().getLname());
		//delete
		CrudOperations.deleteHorse(50);
		buffer = Collections.horseID(50);
		assertEquals(buffer, (Horse)null);
	}
}