package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.OracleInfo;
import model.vo.AttractionVO;
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
	
	void addLike(int reviewNum) {
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
			String updateQuery = "update review set like=? where review_num=?";
			ps = conn.prepareStatement(updateQuery);
			ps.setInt(1, reviewNum);
			ps.setInt(2, reviewNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("reviewNum의 like가 1 증가! :: " + rs.getInt("like"));
			}
		} catch (Exception e) {
		}
	}// addLike 희정쓰

	/*public ArrayList<ReviewVO> getBestReview() throws SQLException {
		
		 * 이 메서드가 호출되면.. DB에 저장된 모든 Review의 좋아요 수를 비교해서 일단은 상위 3개까지만 리스트로 리턴하기! 그리고 이
		 * 리스트에 review_num이 tag 테이블에 있는 review_num이랑 같은 지 확인해서
		 
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();

		ReviewVO vo = null;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.BEST_REVIEW);				*****************************고치기********************************
			rs = ps.executeQuery();
			while (rs.next()) {
				vo = new ReviewVO();
				vo.setReviewNum(rs.getInt("review_num"));
				vo.setContent(rs.getString("content"));
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
*/
	public ArrayList<FestivalVO> getFestivalInfo(String location) throws SQLException {
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
		} finally {
			closeAll(rs, ps, conn);
		}

		return list;
	}// getAttraction 철진쓰

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
			//코멘트. 이미지. 등등 가져와야함..
		} finally {
			closeAll(ps, conn);
		}
		return rvo;
	} // checkReview 윤주쓰
	
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
	
	public static void main(String[] args) {		//단위테스트
		
	}
}
