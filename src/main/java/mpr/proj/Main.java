package mpr.proj;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
    	PdfExport.exportAncestors(Collections.horseID(7));
    	Menus.mainMenu();
    }
}
