package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.OracleInfo;
import model.vo.ReviewVO;
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
	
	public Connection getConnect() throws SQLException {
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
	
	public ArrayList<ReviewVO> searchByTag(String tag) throws SQLException {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
	      
	    try {
	    conn=getConnect();
	    ps= conn.prepareStatement(ReviewStringQuery.SEARCH_BY_TAG);
	    ps.setString(1, tag);
	    rs= ps.executeQuery();
	    while(rs.next()) {
	       list.add(new ReviewVO(rs.getInt("review_num"),
	                        rs.getString("title"),
	                        rs.getString("id"),               
	                        rs.getString("location"),               
	                        rs.getString("city"),
	                        rs.getString("content"),                       
	                        rs.getString("date_writing"),                       
	                        rs.getInt("likes")));
	                
	    }
	    }finally {
	       closeAll(rs, ps, conn);
	    }
	    return list;
	      
	}

	public ArrayList<ReviewVO> getScrapList(String id) throws SQLException { // 스크랩 목록 가져오기
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_SCRAP_LIST);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) 
				list.add(new ReviewVO(rs.getInt("review_num"), rs.getString("title"), rs.getString("id"), rs.getString("location")
						, rs.getString("city"), rs.getString("content"), rs.getString("date_writing"), rs.getInt("likes")));
			
		} finally {
			closeAll(ps, conn);
		}
		
		return list;
	}
	
	public ReviewVO checkReview(int reviewNum) throws SQLException { // 글 조회하기
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ReviewVO rvo = null;
		
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.CHECK_REVIEW);
			ps.setInt(1, reviewNum);
			rs = ps.executeQuery();
			while(rs.next()) 
				new ReviewVO(rs.getInt("review_num"), rs.getString("title"), rs.getString("id"), rs.getString("location"),
						rs.getString("city"), rs.getString("content"), rs.getString("date_writing"), rs.getInt("likes"));
			
		} finally {
			closeAll(ps, conn);
		}
		
		return null;
	}
	
	public ArrayList<ReviewVO> getMyReview(String id) throws SQLException { // 내가 쓴 글 가져오기
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_MY_REVIEW);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) 
				list.add(new ReviewVO(rs.getInt("review_num"), rs.getString("title"), rs.getString("id"), rs.getString("location")
						, rs.getString("city"), rs.getString("content"), rs.getString("date_writing"), rs.getInt("likes")));
			
		} finally {
			closeAll(ps, conn);
		}
		
		return list;
	}
	
	public void deleteReview(int reviewNum) throws SQLException { // 글 삭제하기
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.DELETE_REVIEW);
			ps.setInt(1, reviewNum);
			ps.executeUpdate();
			
			int row = ps.executeUpdate();
			System.out.println(row + " row delete posting ok..");
			
		} finally {
			closeAll(ps, conn);
		}
	}
	
	// ArrayList<String> tags, ArrayList<String> images
	public void updateReview(ReviewVO rvo) throws SQLException { // 글 수정하기(보류보류 보류보류보류보류뷰......)
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.UPDATE_REVIEW);
			ps.setString(1, rvo.getLocation());
			ps.setString(2, rvo.getCity());
			ps.setString(3, rvo.getTitle());
			ps.setInt(4, rvo.getReviewNum());
			ps.executeUpdate();
			
			int row = ps.executeUpdate();
			System.out.println(row + " row update posting ok..");
			
		} finally {
			closeAll(ps, conn);
		}
	}
	
	public static void main(String[] args) throws SQLException {		//단위테스트
		/*ArrayList<ReviewVO> vo = new ArrayList<ReviewVO>();
		vo = TourDao.getInstance().getScrapList("yun");
		for(ReviewVO r : vo) {
			System.out.println(r.toString());
		}*/
	}
}
