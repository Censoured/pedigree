package mpr.proj;

import java.sql.*;

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
    		Menus.mainMenu();
    	}
    }
}
