package mpr.proj;

import java.sql.*;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {
    	Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb","sa","");
    	if(!con.isClosed()){
    		System.out.println("Nawiazano polaczenie z baza danych.");
    	}
    	Collections.setConnection(con);
    	CrudOperations.setConnection(con);
    	Menus.mainMenu();
    	con.close();
    }
}