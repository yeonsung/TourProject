package model;
/*
 * DataSource를 하나 리턴받는 로직은 Dao클래스의 생성자 안에서 했었는데, (naming Service : Context lookup())
 * 
 * 이 부분을 DAO 생성자가 아니라 DataSource MAnager라는 별도의 class를 만들고 해당 로직 진행
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
		} catch (Exception e) {
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
