package com.hcl.usf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.hcl.usf.common.STOSException;
/**
 * 
 * @author intakhabalam.s@hcl.com
 *
 */
@Service
public class DBConnection {
	private final static String DB_URL = "jdbc:oracle:thin:@hpvm31.usfood.com:1521:ECOMQA";
	private final static String USER = "ecomuser";
	private final static String PASS = "oer0vw92n";
	private static Connection conn = null;
    @Autowired
    Environment env;
	public static void main(String[] args) {
		String query2/*="UPDATE ECOM.PROD_CONV_DTL set DECLINED='N', ACCEPTED_DTE=null, DECLINE_REASON_CODE=null where "
				+ " cust_nbr = 33497140 and "
				+ " OLD_PROD_NBR='64774' and "
				+ " NEW_PROD_NBR='2326411'";*/
		="select * from prod_conv_dtl where cust_nbr=10044329 and old_prod_nbr in(60335,8382806)";
		doSelect(query2);
	}

	private static void registerDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Connecting to database...");
		} catch (Exception se) {
			se.printStackTrace();
		}
	}
     /***
      * This is for update query
      * @param query
      */
	public  String doUpdate(String query) {
		registerDriver();
		System.out.println("Update Query is running...");
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(env.getProperty("db.url"),
					env.getProperty("db.user"), env.getProperty("db.pass"));
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			int x = stmt.executeUpdate(query);
			System.out.println("Updated::" + x);
             return ""+x;
		} catch (Exception se) {
			throw new STOSException(se.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception se) {
				throw new STOSException(se.getMessage());
			}
		}
	} 
	/***
     * This is for update query
     * @param query
     */
	public static  void doUpdateTesting(String query) {
		registerDriver();
		System.out.println("Update Query is running...");
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			int x = stmt.executeUpdate(query);
			System.out.println("Updated::" + x);

		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			try {
				//
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception se) {
				se.printStackTrace();
			}
		}
		System.out.println("All done ");
	}

	/***
	 * This is for select query
	 * @param query
	 * @param columnName
	 */
	public static void doSelect(String query) {
		System.out.println("Select Query is running...");
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs.getString(5) +"--"+rs.getString(6));
			}

		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			try {
				//
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception se) {
				se.printStackTrace();
			}
		}
		System.out.println("All done ");
	}
}
