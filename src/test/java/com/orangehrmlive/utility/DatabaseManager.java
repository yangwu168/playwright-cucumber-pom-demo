package com.orangehrmlive.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseManager {

	public static Logger log = LogManager.getLogger(DatabaseManager.class);
	
	private Connection con;
	
	public Connection getCon() {
		return con;
	}

	public DatabaseManager(String url, String username, String password) {
		try {
			// load the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// create the connection object
			this.con = DriverManager.getConnection(url, username, password);
			log.info("Connection established successfully");
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}
	}
	
	public String[][] getDatabaseData(Connection con, String query) {
		String[][] databaseData = null;
		try {
			// create the statement object
			PreparedStatement ps = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// execute query
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			if(rs.first()) {
				int totalCols = md.getColumnCount();
				int totalRows = 0;
				if(rs.last()) {
					totalRows = rs.getRow();
					rs.beforeFirst();
				}
				databaseData = new String[totalRows][totalCols];
				
				int iRowCount = 0;
				while(rs.next()) {
					int iColCount = 1;
					while(iColCount <= totalCols) {
						String data = rs.getString(iColCount);
						databaseData[iRowCount][iColCount-1] = data;
						log.info("Row: " + iRowCount + ", Col: " + iColCount + ", Data: " + data);
						iColCount++;
					}
					iRowCount++;
				}
				rs.close();
				ps.close();
			} else {
				log.info("There are no results returned from query");
			}
		} catch (Exception e) {
			log.error("Error: ", e);
			assertTrue(false);
		} finally {
			try {
				con.close();
				log.info("Connection closed");
			} catch (SQLException e) {
				log.error("Error: ", e);
				assertTrue(false);
			}
		}
		return databaseData;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		DatabaseManager db = new DatabaseManager("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		String query = "select * from employees";
		String[][] arrayData = db.getDatabaseData(db.con, query);
		for(String[] rowOfStrings : arrayData) {
			int iColCount = 0;
			String rowRecord = "";
			for(String cell : rowOfStrings) {
				if(iColCount == 0) {
					rowRecord = rowRecord + cell;
				} else {
					rowRecord = rowRecord + " " + cell;
				}
				iColCount++;
			}
			System.out.println(rowRecord);
		}
//		// load the driver class
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		// create the connection object
//		Connection con = DriverManager.getConnection(db.url, db.username, db.password);
//		log.info("Connection established successfully");
//		// create the statement object
//		Statement st = con.createStatement();
//		// execute query
//		ResultSet rs = st.executeQuery(db.query);
//		
//		while(rs.next()) {
//			int employeeID = rs.getInt(1);
//			String firstName = rs.getString(2);
//			String lastName = rs.getString(3);
//			log.info("Employee ID: " + employeeID + "; " + firstName + " " + lastName);
//		}
//		
//		st.close();
//		con.close();
//		log.info("Connection closed");
	}
	
}