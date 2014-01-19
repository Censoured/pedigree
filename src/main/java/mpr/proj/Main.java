package mpr.proj;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
    	Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");
    	if(!con.isClosed()){
    		System.out.println("Nawiazano polaczenie z baza danych.");
    	}
    	Collections.setConnection(con);
    	Menus.mainMenu();
    	con.close();
    }
}
