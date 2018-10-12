package model.dao;

import java.io.File;
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

import model.vo.MemberVO;
import model.vo.ReviewVO;
import query.review.ReviewStringQuery;
import query.user.UserStringQuery;

public class TourDao {
	private static TourDao reviewDao = new TourDao();

	private TourDao() {
	}

	public static TourDao getInstance() {
		return reviewDao;
	}

	static {
		try {
			Class.forName(OracleInfo.DRIVER_NAME);
			System.out.println("드라이버 로딩");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public void writeReviewImage(int reviewNum,ArrayList<String> imgList) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.INSERT_REVIEWIMAGE);
			for(String img : imgList) {
				ps.setInt(1, reviewNum);
				ps.setString(2, img);
				int row = ps.executeUpdate();
				System.out.println(row+" row insert review_image posting ok....");
			}
		}finally {
			closeAll(ps, conn);
		}
	}

	public int writeReview(ReviewVO rvo) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int num = 0;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.INSERT_REVIEW);

			ps.setString(1, rvo.getLocation());
			ps.setString(2, rvo.getCity());
			ps.setString(3, rvo.getTitle());
			ps.setString(4, rvo.getContent());
			ps.setString(5, rvo.getId());

			int row = ps.executeUpdate();
			System.out.println(row + " row insert posting ok....");
			System.out.println("dao CURRENT_NO...before...." + rvo.getReviewNum());// x
			// 쿼리문이 하나더 들어가야 한다...시퀀스가 PK로 지정된상황에서 INSERT문이 수행될때는...
			// 현재 시퀀스를 하나 받아와서 그걸 VO에 꽂아버려야 한다.
			ps = conn.prepareStatement(ReviewStringQuery.CURRENT_NO);
			rs = ps.executeQuery();
			if (rs.next())
				rvo.setReviewNum(rs.getInt(1));
			System.out.println("dao CURRENT_NO...after...." + rvo.getReviewNum());// o
			num = rvo.getReviewNum();
		} finally {
			closeAll(rs, ps, conn);
		}
		return num;
	}

	public void writeTag(int reviewNum, ArrayList<String> tagList) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.INSERT_TAG);
			for(String tag : tagList) {
				ps.setInt(1, reviewNum);
				ps.setString(2, tag);
				int row = ps.executeUpdate();
				System.out.println(row+" row insert tag posting ok....");
			}
		}finally {
			closeAll(ps, conn);
		}
	}
	
	public ArrayList<String> getTagsByContent(String content){
	      ArrayList<String> tlist = new ArrayList<String>();
	      String content1 = content.replace("<p>", " ");
	      String content2 = content1.replace("</p>", "");
	      String content3 = content2.replace("&nbsp;", " ");
	      String[] arr = content3.split(" ");
	      for(int i=0;i<arr.length;i++) {
	         if(arr[i].startsWith("#")) {
	            tlist.add(arr[i].substring(1));
	         }
	      }
	      return tlist;
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
			while (rs.next()) {
				cities.add(rs.getString("city"));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return cities;
	}

	public int getTotalReview() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int num = 0;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_TOTAL_REVIEW);
			rs = ps.executeQuery();
			if (rs.next())
				num = rs.getInt(1);
		} finally {
			closeAll(rs, ps, conn);
		}
		return num;
	}
	
	public ArrayList<ReviewVO> getRecentReviews(int pn) throws SQLException{		//index review list
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> rlist = new ArrayList<ReviewVO>();
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_RECENT_REVIEWS);
			ps.setInt(1, pn);
			rs = ps.executeQuery();
			while (rs.next()) {
				rlist.add(new ReviewVO(rs.getInt("review_num"), rs.getString("location"), rs.getString("city"),
						rs.getString("title"), rs.getString("id")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return rlist;
	}

	/*
	 * public ArrayList<ReviewVO> getBestReviews(String tag) throws SQLException {
	 * //index�뿉 移댄뀒怨좊━(tag)蹂� review Connection conn = null; PreparedStatement ps =
	 * null; ResultSet rs = null; ArrayList<ReviewVO> rlist = new
	 * ArrayList<ReviewVO>(); try { conn = getConnect(); ps =
	 * conn.prepareStatement(ReviewStringQuery.GET_RECENT_REVIEWS_BY_TAG);
	 * ps.setString(1, tag); rs = ps.executeQuery();
	 * 
	 * ReviewVO rvo = null; while (rs.next()) { rvo = new ReviewVO();
	 * rvo.setLocation(rs.getString("location"));
	 * rvo.setTitle(rs.getString("title"));
	 * rvo.setReviewNum(rs.getInt("review_num")); rvo.setLike(rs.getInt("likes"));
	 * rvo.setCity(rs.getString("city")); rlist.add(rvo); } } finally { closeAll(rs,
	 * ps, conn); } return rlist; }
	 */

	public void addLike(int reviewNum) {
		
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
				System.out.println("reviewNum�쓽 like媛� 1 利앷�! :: " + rs.getInt("likes"));
			}
		} catch (Exception e) {
		}
	}// addLike �����
	
	public void downLike(int reviewNum) {
	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// + review_num
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.LIKE_REMOVE);
			ps.setInt(1, reviewNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("reviewNum�쓽 like媛� 1 利앷�! :: " + rs.getInt("likes"));
			}
		} catch (Exception e) {	
		}
	}//

	
	
	public void insertLike(String id,int reviewNum) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn= getConnect();
			ps =conn.prepareStatement(ReviewStringQuery.INSERT_CHECK);
			ps.setString(1, id);
			ps.setInt(2, reviewNum);
			ps.executeUpdate();
			
			int row = ps.executeUpdate();
			System.out.println(row + " row insertCheck ok..");
		}finally {
			closeAll(ps, conn);
		}
		
		
	}
	
	public void deleteLike(String id,int reviewNum) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn= getConnect();
			ps =conn.prepareStatement(ReviewStringQuery.DELETE_CHECK);
			ps.setString(1, id);
			ps.setInt(2, reviewNum);
			ps.executeUpdate();
			
			int row = ps.executeUpdate();
			System.out.println(row + " row deleteCheck ok..");
		}finally {
			closeAll(ps, conn);
		}
		
	}
	
	public boolean checkLike(String id,int reviewNum) throws SQLException{
		boolean flag = false;
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs= null;
		try {
		conn=getConnect();
		ps=conn.prepareStatement(ReviewStringQuery.CHECK_SELECT);
		ps.setString(1, id);
		ps.setInt(2, reviewNum);
		rs= ps.executeQuery();
		if(rs.next())
			flag=true;
		}finally {
			closeAll(rs, ps, conn);
		}
		System.out.println(flag+"확인");
		return flag;
	}

	public void addScrap(String id, int review_num) throws Exception {					//scrap
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.INSERT_SCRAP);
			ps.setString(1, id);
			ps.setInt(2, review_num);
			ps.executeUpdate();

		} finally {
			closeAll(ps, conn);
		}
	} // scrap 泥좎쭊�벐
	
	public void delScrap(String id, int review_num) throws Exception {					//scrap
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.DELETE_SCRAP);
			ps.setString(1, id);
			ps.setInt(2, review_num);
			ps.executeUpdate();

		} finally {
			closeAll(ps, conn);
		}
	} // scrap 泥좎쭊�벐
	
	public boolean checkScrap(String id,int reviewNum) throws SQLException{
		boolean flag = false;
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs= null;
		try {
		conn=getConnect();
		ps=conn.prepareStatement(ReviewStringQuery.CHECK_SCRAP);
		ps.setString(1, id);
		ps.setInt(2, reviewNum);
		rs= ps.executeQuery();
		if(rs.next())
			flag=true;
		}finally {
			closeAll(rs, ps, conn);
		}
		System.out.println(flag+"확인");
		return flag;
	}
/*	public ArrayList<ReviewVO> getBestReviewByLocation(String location) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();

		ReviewVO vo = null;
		String sql = "select review_num, title, likes from review where location=?";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setString(1, location);
			rs = ps.executeQuery();

			while (rs.next()) {
				vo = new ReviewVO();
				vo.setReviewNum(rs.getInt("review_num"));
				vo.setTitle(rs.getString("title"));
				vo.setLike(rs.getInt("likes"));
				list.add(vo);
			}
		} finally {
			closeAll(rs, ps, conn);
		}

		return list;
	}*/

							
	public ArrayList<ReviewVO> getBestReviewByTag(String location, String tag, int pageNO) throws SQLException { // v1
																													// review
																													// list
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();

		ReviewVO vo = null;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_BESTREVIEW_BY_TAG_LOCA);//

			ps.setString(1, tag);
			ps.setString(2, location);
			ps.setInt(3, pageNO);

			rs = ps.executeQuery();
			while (rs.next()) {
				vo = new ReviewVO();
				vo.setReviewNum(rs.getInt("review_num"));
				vo.setTitle(rs.getString("title"));
				vo.setCity(rs.getString("city"));
				list.add(vo);
			}
			for (int i = 0; i < list.size(); i++) {
				ArrayList<String> tags = getTags(list.get(i).getReviewNum());
				list.get(i).setTags(tags);
				ArrayList<String> img = getImages(list.get(i).getReviewNum());
				list.get(i).setImages(img);
				if (img.size() != 0)
					list.get(i).setMainImage(img.get(0));
			} // for
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}// getBestReview

	public ArrayList<ReviewVO> getBestReviewByTagCity(String location, String city, String tag, int pageNO) throws SQLException { // v1
		// review
		// list
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();

		ReviewVO vo = null;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_BESTREVIEW_BY_TAG_CITY);//

			ps.setString(1, tag);
			ps.setString(2, city);
			ps.setString(3, location);
			ps.setInt(4, pageNO);

			rs = ps.executeQuery();
			while (rs.next()) {
				vo = new ReviewVO();
				vo.setReviewNum(rs.getInt("review_num"));
				vo.setTitle(rs.getString("title"));
				list.add(vo);
			}
			for (int i = 0; i < list.size(); i++) {
				ArrayList<String> tags = getTags(list.get(i).getReviewNum(), conn);
				list.get(i).setTags(tags);
				ArrayList<String> img = getImages(list.get(i).getReviewNum(), conn);
				list.get(i).setImages(img);
				if(img.size()!=0)
					list.get(i).setMainImage(img.get(0));
			} // for
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}// getBestReview

	public ArrayList<FestivalVO> getFestivalInfo(String location) throws SQLException { /// v1 festival list

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
						rs.getString("END_DATE"), rs.getString("agency"), rs.getString("img")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}// getFestivalInfo 泥좎쭊�벐

	public ArrayList<AttractionVO> getAttraction(String city) throws SQLException { // v2 tourspot list
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
			for (AttractionVO vo : list) {
				ps = conn.prepareStatement("SELECT spot_image FROM spot_image WHERE spot_name=?");
				ps.setString(1, vo.getSpotName());
				rs = ps.executeQuery();
				if (rs.next())
					vo.setMainImage(rs.getString("spot_image"));
			}

		} finally {
			closeAll(rs, ps, conn);
		}

		return list;
	}// getAttraction 泥좎쭊�벐 �씠誘몄�!!!!

	public void scrap(String id, int review_num) throws Exception { // scrap
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
	} // scrap 泥좎쭊�벐

	public ReviewVO checkReview(int reviewNum) throws SQLException { // review detail info

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
			rvo.setImages(getImages(rvo.getReviewNum()));			//image list
			rvo.setComments(getComments(rvo.getReviewNum(), conn));		//comment list
		} finally {
			closeAll(rs, ps, conn);
		}
		return rvo;
	} // checkReview �쑄二쇱벐

	public int totalScrapNumber(String id) throws SQLException {

		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.TOTAL_SCRAP_COUNT);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
		} finally {
			closeAll(rs, ps, conn);
		}
		return count;
	}

	public int totalReviewNumber() throws SQLException {

		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.TOTAL_REVIEW_COUNT);
			rs = ps.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
		} finally {
			closeAll(rs, ps, conn);
		}
		return count;
	}

	public int totalMyReviewNumber(String id) throws SQLException {
		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.TOTAL_MY_REVIEW_COUNT);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
		} finally {
			closeAll(rs, ps, conn);
		}
		return count;
	}

	public int totalRelatedReviewNumber(String id) throws SQLException {

		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.TOTAL_RELATED_REVIEW_COUNT);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
		} finally {
			closeAll(rs, ps, conn);
		}
		return count;
	}

	/*
	 * public ArrayList<ReviewVO> searchByTag(String tag) throws SQLException {
	 * //�샇異쒗븯�뒗怨녹뿉�꽌 �뼱�뼡寃� �븘�슂�븷源� Connection conn = null; PreparedStatement ps =
	 * null; ResultSet rs = null; ArrayList<ReviewVO> list = new
	 * ArrayList<ReviewVO>();
	 * 
	 * try { conn=getConnect(); ps=
	 * conn.prepareStatement(ReviewStringQuery.SEARCH_BY_TAG); ps.setString(1, tag);
	 * rs= ps.executeQuery(); while(rs.next()) { list.add(new
	 * ReviewVO(rs.getInt("review_num"), rs.getString("title"), rs.getString("id"),
	 * rs.getString("location"), rs.getString("city"), rs.getString("content"),
	 * rs.getString("date_writing"), rs.getInt("likes")));
	 * 
	 * } }finally { closeAll(rs, ps, conn); } return list;
	 * 
	 * }
	 */

	public ArrayList<ReviewVO> getScrapList(String id, int pageNo) throws SQLException { // scrap list

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_SCRAP_LIST);
			ps.setString(1, id);
			ps.setInt(2, pageNo);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new ReviewVO(rs.getInt("review_num"), rs.getString("title"), rs.getString("id"),
						rs.getString("location"), rs.getString("city"), rs.getString("content"),
						rs.getString("date_writing"), rs.getInt("likes")));
			} // while
			for (ReviewVO vo : list) {
				if (vo != null) {
					ps = conn.prepareStatement(ReviewStringQuery.REVIEW_IMG);
					ps.setInt(1, vo.getReviewNum());
					rs = ps.executeQuery();
					if (rs.next()) {
						vo.setMainImage(rs.getString("review_image"));
					}
				}
			} // for
		} finally {
			closeAll(ps, conn);
		}

		return list;
	}

	public ArrayList<ReviewVO> getMyReview(String id, int pageNo) throws SQLException { // my review list

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_MY_REVIEW);
			ps.setString(1, id);
			ps.setInt(2, pageNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ReviewVO(rs.getInt("review_num"), rs.getString("title"), rs.getString("id"),
						rs.getString("date_writing")));
			} // while
			for (ReviewVO vo : list) {
				if (vo != null) {

					ps = conn.prepareStatement(ReviewStringQuery.REVIEW_IMG);
					ps.setInt(1, vo.getReviewNum());
					rs = ps.executeQuery();
					if (rs.next()) {
						vo.setMainImage(rs.getString("review_image"));
					}
				}
			} // for
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}

	public void deleteReview(int reviewNum) throws SQLException { // delete review

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

	public void deleteScrap(int reviewNum) throws SQLException { // delete scrap
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.DELETE_SCRAP);
			ps.setInt(1, reviewNum);
			ps.executeUpdate();

			int row = ps.executeUpdate();
			System.out.println(row + " row delete scrap ok..");

		} finally {
			closeAll(ps, conn);
		}
	}

	// ArrayList<String> tags, ArrayList<String> images
	public void updateReview(ReviewVO rvo) throws SQLException { // update

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.UPDATE_REVIEW);
			ps.setString(1, rvo.getLocation());
			ps.setString(2, rvo.getCity());
			ps.setString(3, rvo.getTitle());
			ps.setString(4, rvo.getContent());
			ps.setInt(5, rvo.getReviewNum());
			
			int row = ps.executeUpdate();
			System.out.println(row + " row update posting ok..");
			
			ps = conn.prepareStatement(ReviewStringQuery.DELETE_TAG);
			ps.setInt(1, rvo.getReviewNum());
			ps.executeUpdate();

		} finally {
			closeAll(ps, conn);
		}
	}

	public ArrayList<AttractionVO> getData(String tag) throws SQLException {
		ArrayList<AttractionVO> list = new ArrayList<AttractionVO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_DATA);
			ps.setString(1, tag);
			rs = ps.executeQuery();

			while (rs.next())
				list.add(new AttractionVO(rs.getString("spot_name"), rs.getString("address"), rs.getString("location"),
						rs.getString("city"), rs.getString("info"), rs.getString("img")));

		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}

	public ArrayList<ReviewVO> relatedReviews(String tag, int pageNo) throws SQLException {
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.RELATED_REVIEWS);
			ps.setString(1, tag);
			ps.setInt(2, pageNo);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new ReviewVO(rs.getInt("review_num"), rs.getString("title"), rs.getString("date_writing")));
			}

			for (ReviewVO vo : list) {
				if (vo != null) {

					ps = conn.prepareStatement(ReviewStringQuery.REVIEW_IMG);
					ps.setInt(1, vo.getReviewNum());
					rs = ps.executeQuery();
					if (rs.next()) {
						vo.setMainImage(rs.getString("review_image"));
					}
				}
			} // for

		} finally {
			closeAll(rs, ps, conn);
		}

		return list;
	}

	public String checkTag(String tag) throws SQLException {
		String flag = " ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.CHECK_TAG_BY_LOCATION);
			ps.setString(1, tag);
			rs = ps.executeQuery();

			if (rs.next())
				flag = "location";

			else {
				ps = conn.prepareStatement(ReviewStringQuery.CHECK_TAG_BY_CITY);
				ps.setString(1, tag);
				rs = ps.executeQuery();

				if (rs.next())
					flag = "city";

			}

		} finally {
			closeAll(rs, ps, conn);
		}
		return flag;
	}

	public boolean tagExist(String tag) throws SQLException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.TAG_EXIST);
			ps.setString(1, tag);
			rs = ps.executeQuery();

			if (rs.next())
				flag = true;

		} finally {
			closeAll(rs, ps, conn);
		}

		return flag;
	}

	public ArrayList<AttractionVO> checkSpot(String tag) throws SQLException {
		ArrayList<AttractionVO> list = new ArrayList<AttractionVO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.CHECK_SPOT);
			ps.setString(1, tag);
			rs = ps.executeQuery();

			while (rs.next())
				list.add(new AttractionVO(rs.getString("spot_name"), rs.getString("address"), rs.getString("location"),
						rs.getString("city"), rs.getString("info"), rs.getString("img")));

		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}

	public ArrayList<ReviewVO> getReviewBySearch(String tag, int pageNo) throws SQLException {
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_REVIEW_BY_SEARCH);
			ps.setString(1, tag);
			ps.setInt(2, pageNo);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new ReviewVO(rs.getInt("review_num"), rs.getString("title"), rs.getString("date_writing")));
			}

			for (ReviewVO vo : list) {
				if (vo != null) {

					ps = conn.prepareStatement(ReviewStringQuery.REVIEW_IMG);
					ps.setInt(1, vo.getReviewNum());
					rs = ps.executeQuery();
					if (rs.next()) {
						vo.setMainImage(rs.getString("review_image"));
					}
				}
			} // for

		} finally {
			closeAll(rs, ps, conn);
		}

		return list;
	}
	
	public ArrayList<String> getImages(int reviewNum) throws SQLException{			//get review images
		Connection conn = getConnect();
		ArrayList<String> ilist = new ArrayList<String>();
		PreparedStatement ps = conn.prepareStatement(ReviewStringQuery.GET_REVIEW_IMAGES);
		ps.setInt(1, reviewNum);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ilist.add(rs.getString("review_image"));
		}
		closeAll(rs, ps, conn);
		return ilist;
	}
	
	public ArrayList<String> getTags(int reviewNum) throws SQLException{			//get review images
		Connection conn = getConnect();

		ArrayList<String> ilist = new ArrayList<String>();
		PreparedStatement ps = conn.prepareStatement(ReviewStringQuery.GET_REVIEW_TAGS);
		ps.setInt(1, reviewNum);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ilist.add(rs.getString("word"));
		}
		closeAll(rs, ps, conn);

		return ilist;
	}

	public ArrayList<CommentVO> getComments(int review_num, Connection conn) throws SQLException { // get review
																									// comments
		ArrayList<CommentVO> clist = new ArrayList<CommentVO>();
		PreparedStatement ps = conn.prepareStatement(ReviewStringQuery.GET_REVIEW_COMMENTS);
		ps.setInt(1, review_num);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			clist.add(new CommentVO(rs.getString("id"), rs.getString("content")));
		}
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();
		return clist;
	}

	public void writeReviewImage(int reviewNum, ArrayList<String> imgList) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.INSERT_REVIEWIMAGE);
			for (String img : imgList) {
				ps.setInt(1, reviewNum);
				ps.setString(2, img);
				int row = ps.executeUpdate();
				System.out.println(row + " row insert review_image posting ok....");
			}
		} finally {
			closeAll(ps, conn);
		}
	}

	public void writeTag(int reviewNum, ArrayList<String> tagList) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.INSERT_TAG);
			for (String tag : tagList) {
				ps.setInt(1, reviewNum);
				ps.setString(2, tag);
				int row = ps.executeUpdate();
				System.out.println(row + " row insert tag posting ok....");
			}
		} finally {
			closeAll(ps, conn);
		}
	}

	public ArrayList<String> getTagsByContent(String content) {
		ArrayList<String> tlist = new ArrayList<String>();
		String content1 = content.replace("<p>", " ");
		String content2 = content1.replace("</p>", "");
		String content3 = content2.replace("&nbsp;", " ");
		String[] arr = content3.split(" ");
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].startsWith("#")) {
				tlist.add(arr[i].substring(1));
			}
		}
		return tlist;
	}

	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(OracleInfo.URL, OracleInfo.USER, OracleInfo.PASS);

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

	public void register(MemberVO vo) throws SQLException { // member regist
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnect();
			pstmt = conn.prepareStatement(UserStringQuery.REGISTER_USER);
			pstmt.setString(1, vo.getUserName());
			pstmt.setInt(2, vo.getSsn());
			pstmt.setString(3, vo.getId());
			pstmt.setString(4, vo.getPassword());
			pstmt.setString(5, vo.getTel());
			pstmt.setString(6, vo.getMail());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, conn);
		}
	}

	public void updateInfo(MemberVO vo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnect();
			pstmt = conn.prepareStatement(UserStringQuery.UPDATE_USER);
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getMail());
			pstmt.setString(3, vo.getTel());
			pstmt.setString(4, vo.getId());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, conn);
		}
	}

	public boolean idCheck(String id) throws SQLException {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnect();
			pstmt = conn.prepareStatement(UserStringQuery.IDCHECK_USER);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0)
					result = true;
			}
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return result;
	}

	public MemberVO login(String id, String password) throws SQLException {
		MemberVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnect();
			pstmt = conn.prepareStatement(UserStringQuery.LOGIN_USER);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new MemberVO(rs.getString("id"), rs.getString("member_name"));
			}

		} finally {
			closeAll(pstmt, conn);
		}
		return vo;
	}
	
	public MemberVO findIdPass(String userName, int ssn, String tel) throws SQLException{
		MemberVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnect();
			pstmt = conn.prepareStatement(UserStringQuery.FINDIDPASS_USER);
			pstmt.setString(1, userName);
			pstmt.setInt(2, ssn);
			pstmt.setString(3, tel);
			rs = pstmt.executeQuery();
				if(rs.next()) {
				vo = new MemberVO(rs.getString(1),rs.getString(2));
				}
		}finally {
			closeAll(pstmt,conn);
		}
		return vo;
	}
	
	public void deleteImage(int reviewNum, String img) throws SQLException {
		System.out.println("img url : " + img);
		File file = new File(
				"C:\\yjk\\webPro2\\eclipse\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps"
						+ img);
		System.out.println(file.delete());

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnect();
			pstmt = conn.prepareStatement(ReviewStringQuery.DELETE_REVIEW_IMG);
			pstmt.setInt(1, reviewNum);
			pstmt.setString(2, img);
			pstmt.executeUpdate();
			
		} finally {
			closeAll(pstmt, conn);
		}	
	}
	

	public static void main(String[] args) throws SQLException { // 단위테스트
		
		/*ReviewVO vo = TourDao.getInstance().checkReview(36);
		System.out.println(vo.getImages());
		TourDao.getInstance().deleteImage(vo.getImages().get(0));*/
		/*
		 * ArrayList<ReviewVO> vo = new ArrayList<ReviewVO>(); vo =
		 * TourDao.getInstance().getScrapList("yun"); for(ReviewVO r : vo) {
		 * System.out.println(r.toString()); }
		 */
		// TourDao.getInstance().getBestReviewByTag("경기도", "맛집");

	}
}