package mpr.proj;

import java.sql.*;
import java.util.*;

import mpr.proj.pedigree.*;

public abstract class DbOperations {
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
	}	
	
	public static void showHorses()	{
		Set<Horse> zbior = Collections.getHorses();
		for(Horse a: zbior)	{
			System.out.println(a.toString());
		}
	}
	
	public static void addBreeder()	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			String queryStr = "INSERT INTO BREEDER (NAME, COUNTRY) VALUES (?,?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			System.out.print("Podaj godnosc: ");
			String godnosc = EasyIn.getString();
			System.out.print("Podaj id kraju: ");
			int kraj = EasyIn.getInt();
			stmt.setString(1, godnosc);
			stmt.setInt(2, kraj);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Dodano wpis "+godnosc+", "+kraj+" do tabeli Breeder.");
			} 
			else	{
				System.out.println("Nie dodano nowego wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}	
	public static void addColor()	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			String queryStr = "INSERT INTO COLOR (LNAME, SNAME) VALUES (?,?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			System.out.print("Podaj dluga nazwe: ");
			String lnam = EasyIn.getString();
			System.out.print("Podaj krotka nazwe: ");
			String snam = EasyIn.getString();
			stmt.setString(1, lnam);
			stmt.setString(2, snam);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Dodano wpis "+lnam+", "+snam+" do tabeli Color.");
			} 
			else	{
				System.out.println("Nie dodano nowego wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}  
	}
	public static void addCountry()	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			String queryStr = "INSERT INTO COUNTRY (NAME, CODE) VALUES (?,?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			System.out.print("Podaj nazwe: ");
			String nazwa = EasyIn.getString();
			System.out.print("Podaj kod kraju: ");
			String kod = EasyIn.getString();
			stmt.setString(1, nazwa);
			stmt.setString(2, kod);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Dodano wpis "+nazwa+", "+kod+" do tabeli Country.");
			} 
			else	{
				System.out.println("Nie dodano nowego wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}	
	public static void addHorse()	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			String queryStr = "INSERT INTO HORSE (NAME, SEX, COLOR, DOB, YEARONLY, DAM, SIRE, BREEDER) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			System.out.print("Podaj imie: ");
			String godnosc = EasyIn.getString();
			System.out.print("Podaj id gatunku: ");
			int plec = EasyIn.getInt();
			System.out.print("Podaj id koloru: ");
			int kolor = EasyIn.getInt();
			System.out.print("Podaj date urodzenia (YYYY-MM-DD): ");
			String data = EasyIn.getString();
			System.out.print("Podaj czy tylko rok: ");
			int rok = EasyIn.getInt();
			System.out.print("Podaj id matki: ");
			int matka = EasyIn.getInt();
			System.out.print("Podaj id ojca: ");
			int ojciec = EasyIn.getInt();
			System.out.print("Podaj id wlasciciela: ");
			int wlasciciel = EasyIn.getInt();
			stmt.setString(1, godnosc);
			stmt.setInt(2, plec);
			stmt.setInt(3, kolor);
			stmt.setString(4, data);
			stmt.setInt(5, rok);
			stmt.setInt(6, matka);
			stmt.setInt(7, ojciec);
			stmt.setInt(8, wlasciciel);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Dodano wpis "+godnosc+", "+plec+", "+kolor+", "+data+", "+rok+", "+matka+", "+ojciec+", "+wlasciciel+" do tabeli Horse.");
			} 
			else	{
				System.out.println("Nie dodano nowego wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	public static void addSex()	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			String queryStr = "INSERT INTO SEX (NAME) VALUES (?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			System.out.print("Podaj nazwe: ");
			String godnosc = EasyIn.getString();
			stmt.setString(1, godnosc);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Dodano wpis "+godnosc+" do tabeli Breeder.");
			} 
			else	{
				System.out.println("Nie dodano nowego wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void updateBreeder()	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			String queryStr = "UPDATE BREEDER SET NAME=(?), COUNTRY=(?) WHERE ID=(?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			System.out.print("Podaj ID wpisu do edytowania: ");
			int id = EasyIn.getInt();
			System.out.print("Podaj godnosc: ");
			String godnosc = EasyIn.getString();
			System.out.print("Podaj id kraju: ");
			int kraj = EasyIn.getInt();
			stmt.setString(1, godnosc);
			stmt.setInt(2, kraj);
			stmt.setInt(3, id);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Zmieniono wpis nr "+id+" na: "+godnosc+", "+kraj+" w tabeli Breeder.");
			} 
			else	{
				System.out.println("Nie zmieniono wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	public static void updateColor()	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			String queryStr = "UPDATE COLOR SET LNAME=(?), SNAME=(?) WHERE ID=(?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			System.out.print("Podaj ID wpisu do edytowania: ");
			int id = EasyIn.getInt();
			System.out.print("Podaj dluga nazwe: ");
			String lnam = EasyIn.getString();
			System.out.print("Podaj krotka nazwe: ");
			String snam = EasyIn.getString();
			stmt.setString(1, lnam);
			stmt.setString(2, snam);
			stmt.setInt(3, id);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Zmieniono wpis nr "+id+" na: "+lnam+", "+snam+" w tabeli Color.");
			} 
			else	{
				System.out.println("Nie dodano nowego wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}   	
	}
	public static void updateCountry()	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			String queryStr = "UPDATE COUNTRY SET NAME=(?), CODE=(?) WHERE ID=(?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			System.out.print("Podaj ID wpisu do edytowania: ");
			int id = EasyIn.getInt();
			System.out.print("Podaj nazwe: ");
			String nazwa = EasyIn.getString();
			System.out.print("Podaj kod kraju: ");
			String kod = EasyIn.getString();
			stmt.setString(1, nazwa);
			stmt.setString(2, kod);
			stmt.setInt(3, id);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Zmieniono wpis nr "+id+" na: "+nazwa+", "+kod+" w tabeli Country.");
			} 
			else	{
				System.out.println("Nie dodano nowego wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	public static void updateHorse()	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			String queryStr = "UPDATE HORSE SET NAME=(?), SEX=(?), COLOR=(?), DOB=(?), YEARONLY=(?), DAM=(?), SIRE=(?), BREEDER=(?) WHERE ID=(?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			System.out.print("Podaj ID wpisu do edytowania: ");
			int id = EasyIn.getInt();
			System.out.print("Podaj imie: ");
			String godnosc = EasyIn.getString();
			System.out.print("Podaj id gatunku: ");
			int plec = EasyIn.getInt();
			System.out.print("Podaj id koloru: ");
			int kolor = EasyIn.getInt();
			System.out.print("Podaj date urodzenia (YYYY-MM-DD): ");
			String data = EasyIn.getString();
			System.out.print("Podaj czy tylko rok: ");
			int rok = EasyIn.getInt();
			System.out.print("Podaj id matki: ");
			int matka = EasyIn.getInt();
			System.out.print("Podaj id ojca: ");
			int ojciec = EasyIn.getInt();
			System.out.print("Podaj id wlasciciela: ");
			int wlasciciel = EasyIn.getInt();
			stmt.setString(1, godnosc);
			stmt.setInt(2, plec);
			stmt.setInt(3, kolor);
			stmt.setString(4, data);
			stmt.setInt(5, rok);
			stmt.setInt(6, matka);
			stmt.setInt(7, ojciec);
			stmt.setInt(8, wlasciciel);
			stmt.setInt(9, id);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Zmieniono wpis nr "+id+" na: "+godnosc+", "+plec+", "+kolor+", "+data+", "+rok+", "+matka+", "+ojciec+", "+wlasciciel+" w tabeli Breeder.");
			} 
			else	{
				System.out.println("Nie dodano nowego wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	public static void updateSex()	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			String queryStr = "INSERT INTO SEX (NAME) VALUES (?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			System.out.print("Podaj nazwe: ");
			String godnosc = EasyIn.getString();
			stmt.setString(1, godnosc);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Dodano wpis "+godnosc+" do tabeli Breeder.");
			} 
			else	{
				System.out.println("Nie dodano nowego wpisu");
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void deleteBreeder()	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
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
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
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
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
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
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
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
	public static void deleteSex()	{
		try	{
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");;
			String queryStr = "DELETE FROM SEX WHERE ID=(?)";
			PreparedStatement stmt = con.prepareStatement(queryStr);
			System.out.print("Podaj id wpisu ktory chcesz usunac: ");
			int id = EasyIn.getInt();
			stmt.setInt(1, id);
			if(stmt.executeUpdate()>=1)	{
				System.out.println("Usunieto wpis o id="+id+" z tabeli Sex.");
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
