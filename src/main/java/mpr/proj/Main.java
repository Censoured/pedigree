package mpr.proj;

import java.sql.*;
import java.util.*;

import mpr.proj.pedigree.*;

public class Main {
    public static void main(String[] args) throws SQLException {
    	Connection con = null;
    	try {
			Class.forName("org.hsqldb.jdbcDriver");
			con=DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");
			if(!con.isClosed())	{
				System.out.println("Nawiazano polaczenie z baza danych.");
			}
    	}
    	catch (Exception ex) {
    		System.out.println(ex.getMessage());
    	}
    	finally	{
    		System.out.println("Menu glowne:");
    		System.out.println("1. Podejrzyj tabele");
    		System.out.println("2. Dodaj dane");
    		System.out.println("3. Edytuj dane");
    		System.out.println("4. Usun dane");
    		System.out.print("Podaj operacje do wykonania: ");
			int opis = EasyIn.getInt();
			if(opis==1) {
				System.out.println("Podglad tabeli.");
	    		System.out.println("1. Breeder");
	    		System.out.println("2. Color");
	    		System.out.println("3. Country");
	    		System.out.println("4. Horse");
	    		System.out.println("5. Sex");
	    		System.out.print("Wybierz tabele do podejrzenia: ");
	    		int podglad = EasyIn.getInt();
	    		if(podglad==1)	{
	    			try	{
	    				Set<Breeder> zbior = new HashSet<Breeder>();
	    				String queryStr = "SELECT * FROM BREEDER";
	    				Country id=Collections.countryID(1);
	    				Statement stmt = con.createStatement();
	    				ResultSet rs = stmt.executeQuery(queryStr);
	    				/*while(rs.next())	{
	    					zbior.add(new Breeder(rs.getLong("ID"), rs.getString("NAME"), Collections.countryID(rs.getLong("COUNTRY"))));
	    				}
	    				for(Breeder a: zbior)	{
	    					System.out.println(a.toString());
	    				}*/
	    				System.out.println("Nazwa:");
	    			}
	    			catch (Exception ex)	{
	    				System.out.println(ex.getMessage());
	    			}
	    		}
				if(podglad==2)	{
					try	{
	    				Set<Color> zbior = new HashSet<Color>();
	    				String queryStr = "SELECT * FROM COLOR";
	    				Statement stmt = con.createStatement();
	    				ResultSet rs = stmt.executeQuery(queryStr);
	    				while(rs.next())	{
	    					zbior.add(new Color(rs.getLong("ID"),rs.getString("LNAME"),rs.getString("SNAME")));
	    				}	
	    				for(Color a: zbior)	{
	    					System.out.println(a.toString());
	    				}
	    			}
	    			catch (Exception ex)	{
	    				System.out.println(ex.getMessage());
	    			}
				}
				if(podglad==3)	{
					
				}
				if(podglad==4)	{
					
				}
				if(podglad==5)	{
					
				}
			}
			if(opis==2) {
				System.out.println("Dodawanie danych.");
	    		System.out.println("1. Breeder");
	    		System.out.println("2. Color");
	    		System.out.println("3. Country");
	    		System.out.println("4. Horse");
	    		System.out.println("5. Sex");
	    		System.out.print("Wybierz tabele do powiekszenia: ");
	    		int podglad = EasyIn.getInt();
	    		if(podglad==1)	{
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
				if(podglad==2)	{
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
				if(podglad==3)	{
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
		    				System.out.println("Dodano wpis "+nazwa+", "+kod+" do tabeli Breeder.");
		    			} 
		    			else	{
		    				System.out.println("Nie dodano nowego wpisu");
		    			}
	    			}
	    			catch (Exception ex) {
	    				System.out.println(ex.getMessage());
	    			}
				}
				if(podglad==4)	{
					try	{
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
		    				System.out.println("Dodano wpis "+godnosc+", "+plec+", "+kolor+", "+data+", "+rok+", "+matka+", "+ojciec+", "+wlasciciel+" do tabeli Breeder.");
		    			} 
		    			else	{
		    				System.out.println("Nie dodano nowego wpisu");
		    			}
	    			}
	    			catch (Exception ex) {
	    				System.out.println(ex.getMessage());
	    			}
				}
				if(podglad==5)	{
					try	{
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
			}
			if(opis==3)	{
				System.out.println("Edycja danych.");
	    		System.out.println("1. Breeder");
	    		System.out.println("2. Color");
	    		System.out.println("3. Country");
	    		System.out.println("4. Horse");
	    		System.out.println("5. Sex");
	    		System.out.print("Wybierz tabele do edycji: ");
	    		int podglad = EasyIn.getInt();
	    		if(podglad==1)	{
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
				if(podglad==2)	{
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
				if(podglad==3)	{
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
		    				System.out.println("Zmieniono wpis nr "+id+" na: "+nazwa+", "+kod+" e tabeli Breeder.");
		    			} 
		    			else	{
		    				System.out.println("Nie dodano nowego wpisu");
		    			}
	    			}
	    			catch (Exception ex) {
	    				System.out.println(ex.getMessage());
	    			}
				}
				if(podglad==4)	{
					try	{
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
				if(podglad==5)	{
					try	{
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
			}
			if(opis==4)	{
				System.out.println("Usuwanie danych");
				System.out.println("1. Breeder");
	    		System.out.println("2. Color");
	    		System.out.println("3. Country");
	    		System.out.println("4. Horse");
	    		System.out.println("5. Sex");
	    		System.out.print("Wybierz tabele do pomniejszenia: ");
	    		int podglad = EasyIn.getInt();
	    		if(podglad==1)	{
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
	    		if(podglad==2)	{
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
	    		if(podglad==3)	{
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
	    		if(podglad==4)	{
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
	    		if(podglad==5)	{
	    			try	{
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
    	}
    }
}
