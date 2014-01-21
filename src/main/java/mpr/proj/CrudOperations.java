package mpr.proj;

import java.sql.*;
import java.util.*;

import mpr.proj.pedigree.*;

public abstract class CrudOperations {
	
	static Connection con = null;
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
	
	public static void addBreeder()	{
		try	{
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
			int matka, ojciec, bufor=0;
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
			do	{
				if(bufor>0)	{
					System.out.println("Podany kon nie jest klacza!");
				}
				System.out.print("Podaj id matki: ");
				matka = EasyIn.getInt();
				bufor++;
			} while(Collections.horseID(matka).getSex()!=Sex.MARE);
			bufor = 0;
			do	{	
				if(bufor>0)	{
					System.out.println("Podany kon nie jest klacza!");
				}
				System.out.print("Podaj id ojca: ");
				ojciec = EasyIn.getInt();
				bufor++;
			} while(Collections.horseID(ojciec).getSex()!=Sex.STALLION);
			bufor = 0;
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
	
	public static void updateBreeder()	{
		try	{
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
			int matka,ojciec,bufor=0;
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
			do	{
				if(bufor>0)	{
					System.out.println("Podany kon nie jest klacza!");
				}
				System.out.print("Podaj id matki: ");
				matka = EasyIn.getInt();
				bufor++;
			} while(Collections.horseID(matka).getSex()!=Sex.MARE);
			bufor = 0;
			do	{	
				if(bufor>0)	{
					System.out.println("Podany kon nie jest klacza!");
				}
				System.out.print("Podaj id ojca: ");
				ojciec = EasyIn.getInt();
				bufor++;
			} while(Collections.horseID(ojciec).getSex()!=Sex.STALLION);
			bufor = 0;
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
