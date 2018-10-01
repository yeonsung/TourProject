package model.review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.OracleInfo;
import model.attraction.AttractionVO;
import model.vo.CommentVO;
import model.vo.FestivalVO;
import query.review.ReviewStringQuery;

public class ReviewDao {
	private static ReviewDao reviewDao = new ReviewDao();
	private ReviewDao() {}
	public static ReviewDao getInstance() {
		return reviewDao;
	}
	
	public ArrayList<ReviewVO> searchByTag(String tag) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		ArrayList<String> tList = new ArrayList<String>();
		ArrayList<CommentVO> cList = new ArrayList<CommentVO>();
		ArrayList<String> imgList = new ArrayList<String>();
		try {
		conn=getConnect();
		ps= conn.prepareStatement(ReviewStringQuery.SEARCH_BY_TAG);
		ps.setString(1,tag);
		rs= ps.executeQuery();
		ReviewVO vo = null;
		while(rs.next()) {
			list.add(vo = new ReviewVO(rs.getInt("review_num"),
								  rs.getString("title"),
								  rs.getString("id"),					
								  rs.getString("location"),					
								  rs.getString("city"),
								  rs.getString("content"),							  
								  rs.getString("date_writing"),							  
								  rs.getInt("likes")));
		}
		ps= conn.prepareStatement(ReviewStringQuery.GET_WORD);
		ps.setString(1,tag);
		rs= ps.executeQuery();
		while(rs.next()){
			tList.add(rs.getString("word"));
		}
		vo.setTags(tList);
		
		ps= conn.prepareStatement(ReviewStringQuery.GET_COMMENTS);
		ps.setString(1,tag);
		rs= ps.executeQuery();
		while(rs.next()){
			cList.add(new CommentVO(rs.getString("id"),
					  rs.getString("content")
					));
		}
		vo.setComments(cList);
		
		ps= conn.prepareStatement(ReviewStringQuery.GET_REVIEW_IMAGE);
		ps.setString(1,tag);
		rs= ps.executeQuery();
		while(rs.next()){
			imgList.add(rs.getString("review_image"));
		}
		vo.setImages(imgList);
		
		}finally {
			closeAll(rs, ps, conn);
		}
		return list;
		
	}
	
	public void scrap(String id, int review_num) throws Exception {
		Connection conn = null;
		PreparedStatement ps= null;
		
		try {
		conn=getConnect();
		ps=conn.prepareStatement(ReviewStringQuery.SCRAP);
		ps.setString(1,id);
		ps.setInt(2,review_num);
		ps.executeUpdate();
		
		}finally{
			closeAll(ps, conn);
		}
		
	}
	
	public ArrayList<AttractionVO> getAttraction (String city) throws SQLException{
		ArrayList<AttractionVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs= null;
	
		try {
			conn=getConnect();
			ps=conn.prepareStatement(ReviewStringQuery.GET_ATTRACTION);
			ps.setString(1, city);
			rs=ps.executeQuery();
			while(rs.next()) {
				list.add(new AttractionVO(rs.getString("spot_name"),
										  rs.getString("address"),
										  rs.getString("location"),
										  rs.getString("city"),
										  rs.getString("info")/*,
										  (ArrayList<String>)rs.getObject("images")*/
										));
				
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		
		return list;
	}
	
	
	
	public ArrayList<FestivalVO> getFestivalInfo (String city) throws SQLException{
		ArrayList<FestivalVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs= null;
	
		try {
			conn=getConnect();
			ps=conn.prepareStatement(ReviewStringQuery.GET_FESTIVAL_INFO);
			ps.setString(1, city);
			rs=ps.executeQuery();
			while(rs.next()) {
				list.add(new FestivalVO(rs.getString("festivalName"),
										  rs.getString("festivalLocation"),
										  rs.getString("location"),
										  rs.getString("city"),
										  rs.getString("startDate"),
										  rs.getString("endDate"),
										  rs.getString("agency")
										  
										));
				
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		
		return list;
		
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
	
	public static void main(String[] args)  {		//단위테스트
	
			try {
			System.out.println(ReviewDao.getInstance().searchByTag("보성"));
			/*	ReviewDao.getInstance().scrap("lcj", 3);*/
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
