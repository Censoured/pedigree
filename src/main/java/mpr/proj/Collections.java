package mpr.proj;

import mpr.proj.pedigree.*;

import java.sql.*;
import java.util.*;

public abstract class Collections {
	
	public static Set<Breeder> getBreeders()	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			Set<Breeder> dane = new HashSet<Breeder>();
			String queryStr = "SELECT * FROM BREEDER";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryStr);
			while(rs.next())	{
				dane.add(new Breeder(rs.getLong("ID"), rs.getString("NAME"), countryID(rs.getLong("COUNTRY"))));
			}
			return dane;
		}
		catch (Exception ex)	{
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	public static Set<Horse> getHorses()	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			Set<Horse> dane = new HashSet<Horse>();
			String queryStr = "SELECT * FROM HORSE";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryStr);
			while(rs.next())	{
				dane.add(new Horse(rs.getLong("ID"), rs.getString("NAME"), sexID(rs.getLong("SEX")), new DateOfBirth(rs.getDate("DOB"), rs.getBoolean("YEARONLY")), colorID(rs.getLong("COLOR")), horseID(rs.getLong("SIRE")), horseID(rs.getLong("DAM")), breederID(rs.getLong("BREEDER"))));
			}
			return dane;
		}
		catch (Exception ex)	{
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	public static Set<Color> getColors()	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			Set<Color> dane = new HashSet<Color>();
			String queryStr = "SELECT * FROM COLOR";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryStr);
			while(rs.next())	{
				dane.add(new Color(rs.getLong("ID"),rs.getString("LNAME"),rs.getString("SNAME")));
			}
			return dane;
		}
		catch (Exception ex)	{
			System.out.println(ex.getMessage());
			return null;
		}
		
	}
	
	public static Set<Country> getCountries()	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			Set<Country> dane = new HashSet<Country>();
			String queryStr = "SELECT * FROM COUNTRY";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryStr);
			while(rs.next())	{
				dane.add(new Country(rs.getLong("ID"),rs.getString("NAME"),rs.getString("CODE")));
			}
			return dane;
		}
		catch (Exception ex)	{
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	public static Color colorID(long id)	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			String queryStr = "SELECT * FROM COLOR WHERE COLOR.ID="+id;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryStr);
			if(rs.next())	{
				return new Color(rs.getLong("ID"),rs.getString("LNAME"),rs.getString("SNAME"));
			}
		}
		catch (Exception ex)	{
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	public static Country countryID(long id)	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			String queryStr = "SELECT * FROM COUNTRY WHERE ID="+id;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryStr);
			if(rs.next())	{
				return new Country(rs.getLong("ID"),rs.getString("NAME"),rs.getString("CODE"));
			}
		}
		catch (Exception ex)	{
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	public static Sex sexID(long id)	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			String queryStr = "SELECT * FROM SEX WHERE SEX.ID="+id;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryStr);
			if(rs.next())	{
				return Sex.valueOf(rs.getString("NAME"));
			}
		}
		catch (Exception ex)	{
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	public static Breeder breederID(long id)	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			String queryStr = "SELECT * FROM BREEDER WHERE BREEDER.ID="+id;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryStr);
			if(rs.next())	{
				return new Breeder(rs.getLong("ID"), rs.getString("name"), countryID(rs.getLong("COUNTRY")));
			}
		}
		catch (Exception ex)	{
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	public static Horse horseID(long id)	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			String queryStr = "SELECT * FROM HORSE WHERE HORSE.ID="+id;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryStr);
			if(rs.next())	{
				return new Horse(rs.getLong("ID"), rs.getString("NAME"), sexID(rs.getLong("SEX")), new DateOfBirth(rs.getDate("DOB"), rs.getBoolean("YEARONLY")), colorID(rs.getLong("COLOR")), horseID(rs.getLong("SIRE")), horseID(rs.getLong("DAM")), breederID(rs.getLong("BREEDER")));
			}
		}
		catch (Exception ex)	{
			System.out.println(ex.getMessage());
		}
		return null;
	}
}
