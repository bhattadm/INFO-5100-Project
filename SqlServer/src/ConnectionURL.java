
import java.sql.DriverManager;
import java.sql.*;
import java.sql.SQLException;

import javax.swing.JOptionPane;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;

	public class ConnectionURL {
	    public static void main(String[] args) {

	        // Create a variable for the connection string.
	        String connectionUrl = "jdbc:sqlserver://is-swang01.ischool.uw.edu:1433;databaseName= VechileManagementSystem;user=INFO6210;password=NEUHusky!";

	        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
	            String SQL = "SELECT TOP 10 * FROM dbo.CarInventory";
	            ResultSet rs = stmt.executeQuery(SQL);

	            // Iterate through the data in the result set and display it.
	            while (rs.next()) {
	                System.out.println(rs.getString("type")+rs.getString("model"));
	            }
	        }
	        // Handle any errors that may have occurred.
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


