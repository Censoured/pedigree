package mpr.proj;

import java.sql.*;
import java.util.*;

import mpr.proj.pedigree.*;

public abstract class CrudOperations {
	
	static Connection con = null;
	
    public static Sex getSex(int id)	{
    	if(id==0)	{
    		return Sex.MARE;
    	}
    	if(id==1)	{
    		return Sex.STALLION;
    	}
    	if(id==2)	{
    		return Sex.GELDING;
    	}
		return null;
    }
    
	public static void setConnection(Connection dcon)	{
		con = dcon;
	}
	
	public static void showBreeders()	{
		Set<Breeder> zbior = Collections.getBreeders();
		for(Breeder a: zbior)	{
			System.out.println(a.toString());
		}
	}	
	public static void showCountries()	{
		Set<Country> zbior = Collections.getCountries();
		for(Country a: zbior)	{
			System.out.println(a.toString());
		}
	}	
	
	public static void showColors()	{
		Set<Color> zbior = Collections.getColors();
		for(Color a: zbior)	{
			System.out.println(a.toString());
		}
	}	
	
	public static void showSex()	{
		Set<String> zbior = Collections.getSex();
		for(String a: zbior)	{
			System.out.println(a.toString());
		} 
	}	
	
	public static void showHorses()	{
		Set<Horse> zbior = Collections.getHorses();
		for(Horse a: zbior)	{
			System.out.println(a.toString());
		} 
	}
	
	public static Breeder addBreeder(Breeder breeder)	{
		try	{
			String queryStr = "INSERT INTO BREEDER (NAME, COUNTRY) VALUES (?,?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			stmt.setString(1, breeder.getName());
			stmt.setInt(2, (int) breeder.getCountry().getId());
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Dodano wpis "+breeder.getName()+", "+breeder.getCountry().getId()+" do tabeli Breeder.");
				return new Breeder(0, breeder.getName(), Collections.countryID(breeder.getCountry().getId()));
			} 
			else	{
				System.out.println("Nie dodano nowego wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}	
	
	public static Color addColor(Color color)	{
		try	{
			String queryStr = "INSERT INTO COLOR (LNAME, SNAME) VALUES (?,?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			stmt.setString(1, color.getLname());
			stmt.setString(2, color.getSname());
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Dodano wpis "+color.getLname()+", "+color.getSname()+" do tabeli Color.");
				return new Color(0, color.getLname(), color.getSname());
			} 
			else	{
				System.out.println("Nie dodano nowego wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}  
		return null;
	}
	
	public static Country addCountry(Country country)	{
		try	{
			String queryStr = "INSERT INTO COUNTRY (NAME, CODE) VALUES (?,?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			stmt.setString(1, country.getName());
			stmt.setString(2, country.getCode());
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Dodano wpis "+country.getName()+", "+country.getCode()+" do tabeli Country.");
				return new Country(0, country.getName(), country.getCode());
			} 
			else	{
				System.out.println("Nie dodano nowego wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}	
	
	public static Horse addHorse(Horse horse)	{
		try	{
			
			String queryStr = "INSERT INTO HORSE (NAME, SEX, COLOR, DOB, YEARONLY, DAM, SIRE, BREEDER) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			
			stmt.setString(1, horse.getName());
			stmt.setInt(2, horse.getSex().ordinal());
			stmt.setInt(3, (int)horse.getColor().getId());
			stmt.setString(4, horse.getDob().toString());
			stmt.setInt(5, horse.getYearOnly());
			stmt.setInt(6, (int)horse.getDam().getID());
			stmt.setInt(7, (int)horse.getSire().getID());
			stmt.setInt(8, (int)horse.getBreeder().getId());
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Dodano wpis "+horse.getName()+", "+horse.getSex().name()+", "+horse.getColor().getLname()+", "+horse.getDob().toString()+", "+horse.getYearOnly()+", "+horse.getDam().getName()+", "+horse.getSire().getName()+", "+horse.getBreeder().getName()+" do tabeli Horse.");
				return new Horse(0, horse.getName(), getSex(horse.getSex().ordinal()), horse.getDob(), horse.getColor(), horse.getDam(), horse.getSire(), horse.getBreeder());
			} 
			else	{
				System.out.println("Nie dodano nowego wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	public static Breeder updateBreeder(Breeder breeder, int id)	{
		try	{
			String queryStr = "UPDATE BREEDER SET NAME=(?), COUNTRY=(?) WHERE ID=(?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			stmt.setString(1, breeder.getName());
			stmt.setInt(2, (int)breeder.getCountry().getId());
			stmt.setInt(3, id);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Zmieniono wpis nr "+id+" na: "+breeder.getName()+", "+breeder.getCountry().getName()+" w tabeli Breeder.");
				return breeder = new Breeder(0, breeder.getName(), Collections.countryID(breeder.getCountry().getId()));
			} 
			else	{
				System.out.println("Nie zmieniono wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	public static Color updateColor(Color color, int id)	{
		try	{
			String queryStr = "UPDATE COLOR SET LNAME=(?), SNAME=(?) WHERE ID=(?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			stmt.setString(1, color.getLname());
			stmt.setString(2, color.getSname());
			stmt.setInt(3, id);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Zmieniono wpis nr "+id+" na: "+color.getLname()+", "+color.getSname()+" w tabeli Color.");
				return color = new Color(0, color.getLname(), color.getSname());
			} 
			else	{
				System.out.println("Nie dodano nowego wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}   
		return null;
	}
	
	public static Country updateCountry(Country country, int id)	{
		try	{
			String queryStr = "UPDATE COUNTRY SET NAME=(?), CODE=(?) WHERE ID=(?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			stmt.setString(1, country.getName());
			stmt.setString(2, country.getCode());
			stmt.setInt(3, id);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Zmieniono wpis nr "+id+" na: "+country.getName()+", "+country.getCode()+" w tabeli Country.");
				return country = new Country(0, country.getName(), country.getCode());
			} 
			else	{
				System.out.println("Nie dodano nowego wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	public static Horse updateHorse(Horse horse, int id)	{
		try	{
			int matka,ojciec,bufor=0;
			String queryStr = "UPDATE HORSE SET NAME=(?), SEX=(?), COLOR=(?), DOB=(?), YEARONLY=(?), DAM=(?), SIRE=(?), BREEDER=(?) WHERE ID=(?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			stmt.setString(1, horse.getName());
			stmt.setInt(2, horse.getSex().ordinal());
			stmt.setInt(3, (int)horse.getColor().getId());
			stmt.setString(4, horse.getDob().toString());
			stmt.setInt(5, horse.getYearOnly());
			stmt.setInt(6, (int)horse.getDam().getID());
			stmt.setInt(7, (int)horse.getSire().getID());
			stmt.setInt(8, (int)horse.getBreeder().getId());
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Dodano wpis "+horse.getName()+", "+horse.getSex().name()+", "+horse.getColor().getLname()+", "+horse.getDob().toString()+", "+horse.getYearOnly()+", "+horse.getDam().getName()+", "+horse.getSire().getName()+", "+horse.getBreeder().getName()+" do tabeli Horse.");
				return new Horse(0, horse.getName(), getSex(horse.getSex().ordinal()), horse.getDob(), horse.getColor(), horse.getDam(), horse.getSire(), horse.getBreeder());
			} 
			else	{
				System.out.println("Nie dodano nowego wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	public static void deleteBreeder()	{
		try	{
			String queryStr = "DELETE FROM BREEDER WHERE ID=(?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			System.out.print("Podaj id wpisu ktory chcesz usunac: ");
			int id = EasyIn.getInt();
			stmt.setInt(1, id);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Usunieto wpis o id="+id+" z tabeli Breeder.");
			} 
			else	{
				System.out.println("Nie usunieto wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}	
	}
	
	public static void deleteColor()	{
		try	{
			String queryStr = "DELETE FROM COLOR WHERE ID=(?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			System.out.print("Podaj id wpisu ktory chcesz usunac: ");
			int id = EasyIn.getInt();
			stmt.setInt(1, id);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Usunieto wpis o id="+id+" z tabeli Color.");
			} 
			else	{
				System.out.println("Nie usunieto wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void deleteCountry()	{
		try	{
			String queryStr = "DELETE FROM COUNTRY WHERE ID=(?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			System.out.print("Podaj id wpisu ktory chcesz usunac: ");
			int id = EasyIn.getInt();
			stmt.setInt(1, id);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Usunieto wpis o id="+id+" z tabeli Country.");
			} 
			else	{
				System.out.println("Nie usunieto wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void deleteHorse()	{
		try	{
			String queryStr = "DELETE FROM HORSE WHERE ID=(?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			System.out.print("Podaj id wpisu ktory chcesz usunac: ");
			int id = EasyIn.getInt();
			stmt.setInt(1, id);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Usunieto wpis o id="+id+" z tabeli Horse.");
			} 
			else	{
				System.out.println("Nie usunieto wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
