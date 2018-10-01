package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DataSourceManager {
	
	private DataSource ds;
	
	private DataSourceManager() {
		try{
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/oracleDB");
			System.out.println("DataSource lookup");
		} catch (NamingException e) {
			System.out.println("DataSource Loading fail");
		}
	}
	private static DataSourceManager instance = new DataSourceManager();
	public static DataSourceManager getInstance() {
		return instance;
	}
	
	public DataSource getDataSource() throws SQLException {
		return ds;
	}
}
