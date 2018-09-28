package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.OracleInfo;
import jdk.management.resource.ResourceAccuracy;
import query.review.ReviewStringQuery;

public class TourDao {
	private static TourDao reviewDao = new TourDao();
	private TourDao() {}
	public static TourDao getInstance() {
		return reviewDao;
	}
	
	
	
	
	
	
	static {
		try {
			Class.forName(OracleInfo.DRIVER_NAME);
			System.out.println("드라이버 로딩 성공");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<String> getCities(String location) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> cities = new ArrayList<String>();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(ReviewStringQuery.GETCITIES);
			ps.setString(1, location);
			
			while(rs.next()) {
				cities.add(rs.getString("city"));
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return cities;
	}
	
	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(OracleInfo.URL, OracleInfo.USER, OracleInfo.PASS);
		System.out.println("디비 연결 성공!");
		return conn;
	}// getConnect
	
	private void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();
	}// closeAll

	private void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(ps, conn);
	}// closeAll
	
	public static void main(String[] args) {		//단위테스트
		
	}
}
