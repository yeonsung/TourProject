package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.OracleInfo;
import model.vo.AttractionVO;
import model.vo.CommentVO;
import model.vo.FestivalVO;
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
	
	public ArrayList<String> getCities(String location) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> cities = new ArrayList<String>();
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GETCITIES);
			ps.setString(1, location);
			rs = ps.executeQuery();
			while(rs.next()) {
				cities.add(rs.getString("city"));
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return cities;
	}
	
	public ArrayList<ReviewVO> getBestReviews(String tag) throws SQLException {				//index에 카테고리(tag)별 review
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> rlist = new ArrayList<ReviewVO>();
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_BEST_REVIEWS);
			ps.setString(1, tag);
			rs = ps.executeQuery();
			
			ReviewVO rvo = null;
			while(rs.next()) {
				rvo = new ReviewVO();
				rvo.setLocation(rs.getString("location"));
				rvo.setTitle(rs.getString("title"));
				rvo.setReviewNum(rs.getInt("review_num"));
				rvo.setLike(rs.getInt("likes"));
				rvo.setCity(rs.getString("city"));
				rlist.add(rvo);
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return rlist;
	}
	
	public void addLike(int reviewNum) {
		/*
		 * 이 메서드가 호출되면 글번호 post_num인 리뷰의 like 수가 1 증가한다.
		 */
		// post_num 인 애 찾기
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// + review_num
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.LIKE_ADD);
			ps.setInt(1, reviewNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("reviewNum의 like가 1 증가! :: " + rs.getInt("likes"));
			}
		} catch (Exception e) {
		}
	}// addLike 희정쓰

	public ArrayList<ReviewVO> getBestReviewByTag(String location , String tag) throws SQLException {		//location : 시/군     tag: review 카테고리
		
		 //* 이 메서드가 호출되면.. DB에 저장된 모든 Review의 좋아요 수를 비교해서 일단은 상위 3개까지만 리스트로 리턴하기! 그리고 이
		// * 리스트에 review_num이 tag 테이블에 있는 review_num이랑 같은 지 확인해서
		 
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();

		ReviewVO vo = null;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.BEST_REVIEW_LOCATION_TAG);//				
			ps.setString(1, tag);
			ps.setString(2, location);
			rs = ps.executeQuery();
			while (rs.next()) {
				vo = new ReviewVO();
				vo.setReviewNum(rs.getInt("review_num"));
				vo.setTitle(rs.getString("title"));
				vo.setLike(rs.getInt("likes"));
				list.add(vo);
			}
			ps = conn.prepareStatement(ReviewStringQuery.GET_TAG_LIST);

			for (int i = 0; i < list.size(); i++) {
				ps.setInt(1, list.get(i).getReviewNum());
				rs = ps.executeQuery();
				ArrayList<String> tags = new ArrayList<String>();
				if (rs.next()) {
					tags.add(rs.getString(1));
					list.get(i).setTags(tags);
				} // if
			} // for

			ps = conn.prepareStatement(ReviewStringQuery.GET_IMAGE_LIST);
			for (int i = 0; i < list.size(); i++) {
				ps.setInt(1, list.get(i).getReviewNum());
				rs = ps.executeQuery();
				ArrayList<String> img = new ArrayList<String>();
				if (rs.next()) {
					img.add(rs.getString(1));
					list.get(i).setImages(img);
				} // if
			} // for
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}// getBestReview 희정쓰

	public ArrayList<FestivalVO> getFestivalInfo(String location) throws SQLException {			///생각해보기
		ArrayList<FestivalVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_FESTIVAL_INFO);
			ps.setString(1, location);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new FestivalVO(rs.getString("festival_Name"), rs.getString("festival_Location"),
						rs.getString("location"), rs.getString("city"), rs.getString("start_Date"),
						rs.getString("END_DATE"), rs.getString("agency")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}// getFestivalInfo 철진쓰

	public ArrayList<AttractionVO> getAttraction(String city) throws SQLException {
		ArrayList<AttractionVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_ATTRACTION);
			ps.setString(1, city);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new AttractionVO(rs.getString("spot_name"), rs.getString("address"), rs.getString("location"),
						rs.getString("city"), rs.getString("info")));
			}
			for(AttractionVO vo : list) {
				ps = conn.prepareStatement("SELECT spot_image FROM spot_image WHERE spot_name = ?");
				ps.setString(1, vo.getSpotName());
				rs = ps.executeQuery();
				if(rs.next())
					vo.setMainImage(rs.getString("spot_image"));
			}
			
		} finally {
			closeAll(rs, ps, conn);
		}

		return list;
	}// getAttraction 철진쓰			이미지!!!!

	public void scrap(String id, int review_num) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.SCRAP);
			ps.setString(1, id);
			ps.setInt(2, review_num);
			ps.executeUpdate();

		} finally {
			closeAll(ps, conn);
		}
	} // scrap 철진쓰

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
			if (rs.next()) {
				rvo = new ReviewVO(rs.getInt("review_num"), rs.getString("title"), rs.getString("id"),
						rs.getString("location"), rs.getString("city"), rs.getString("content"),
						rs.getString("date_writing"), rs.getInt("likes"));
			}
			rvo.setImages(getImages(rvo.getReviewNum(), conn));			//image list
			rvo.setComments(getComments(rvo.getReviewNum(), conn));		//comment list
			//코멘트. 이미지. 등등 가져와야함..
		} finally {
			closeAll(rs, ps, conn);
		}
		return rvo;
	} // checkReview 윤주쓰
	
	public int totalScrapNumber() throws SQLException{
		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.TOTAL_SCRAP_COUNT);
			rs = ps.executeQuery();
			if(rs.next()) count = rs.getInt(1);
		} finally {
			closeAll(rs, ps, conn);
		}
		return count;
	}
	
	public ArrayList<ReviewVO> searchByTag(String tag) throws SQLException {			//호출하는곳에서 어떤게 필요할까
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
			
			while(rs.next()) { 
				list.add(new ReviewVO(rs.getInt("review_num"), rs.getString("title"), rs.getString("id"), rs.getString("location")
						, rs.getString("city"), rs.getString("content"), rs.getString("date_writing"), rs.getInt("likes")));
			}//while
			for(ReviewVO vo : list) {
				if(vo!=null) {
					ps = conn.prepareStatement("SELECT review_image FROM review_image WHERE review_num=?");
					ps.setInt(1,vo.getReviewNum());
					rs = ps.executeQuery();
					if(rs.next()) {
						vo.setMainImage(rs.getString("review_image"));
					}
				}
			}//for
		} finally {
			closeAll(ps, conn);
		}
		
		return list;
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
			while(rs.next()) {
				list.add(new ReviewVO(rs.getInt("review_num"), rs.getString("title"), rs.getString("id"), rs.getString("location")
						, rs.getString("city"), rs.getString("content"), rs.getString("date_writing"), rs.getInt("likes")));
			}//while
			for(ReviewVO vo : list) {
				if(vo!=null) {
					ps = conn.prepareStatement("SELECT review_image FROM review_image WHERE review_num=?");
					ps.setInt(1,vo.getReviewNum());
					rs = ps.executeQuery();
					if(rs.next()) {
						vo.setMainImage(rs.getString("review_image"));
					}
				}
			}//for
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
	
	public ArrayList<String> getImages(int reviewNum,Connection conn) throws SQLException{			//리뷰# 가지고 이미지들 리턴
		ArrayList<String> ilist = new ArrayList<String>();
		PreparedStatement ps = conn.prepareStatement(ReviewStringQuery.GET_REVIEW_IMAGES);
		ps.setInt(1, reviewNum);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			ilist.add(rs.getString("review_image"));
		}
		if(rs!=null) rs.close();
		if(ps!=null) ps.close();
		return ilist;
	}
	
	public ArrayList<CommentVO> getComments(int review_num,Connection conn) throws SQLException{
		ArrayList<CommentVO> clist = new ArrayList<CommentVO>();
		PreparedStatement ps = conn.prepareStatement(ReviewStringQuery.GET_REVIEW_COMMENTS);
		ps.setInt(1, review_num);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			clist.add(new CommentVO(rs.getString("id"),rs.getString("comment")));
		}
		if(rs!=null) rs.close();
		if(ps!=null) ps.close();
		return clist;
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
	
	
	
	public static void main(String[] args) throws SQLException {		//단위테스트
		/*ArrayList<ReviewVO> vo = new ArrayList<ReviewVO>();
		vo = TourDao.getInstance().getScrapList("yun");
		for(ReviewVO r : vo) {
			System.out.println(r.toString());
		}*/
	}
}
