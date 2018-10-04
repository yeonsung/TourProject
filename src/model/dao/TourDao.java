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
			System.out.println("�뱶�씪�씠踰� 濡쒕뵫 �꽦怨�");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void writeReview(ReviewVO rvo) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.INSERT_REVIEW);
			
			ps.setString(1, rvo.getLocation());
			ps.setString(2, rvo.getCity());
			ps.setString(3, rvo.getTitle());
			ps.setString(4, rvo.getContent());
			ps.setString(5, rvo.getId());
			
			int row = ps.executeUpdate();
			System.out.println(row+" row insert posting ok....");
			System.out.println("dao CURRENT_NO...before...."+rvo.getReviewNum());//x
			//쿼리문이 하나더 들어가야 한다...시퀀스가 PK로 지정된상황에서 INSERT문이 수행될때는...
			//현재 시퀀스를 하나 받아와서 그걸 VO에 꽂아버려야 한다.
			ps = conn.prepareStatement(ReviewStringQuery.CURRENT_NO);
			rs = ps.executeQuery();
			if(rs.next()) 
				rvo.setReviewNum(rs.getInt(1));
			System.out.println("dao CURRENT_NO...after...."+rvo.getReviewNum());//o
		}finally{
			closeAll(rs, ps, conn);
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
			while (rs.next()) {
				cities.add(rs.getString("city"));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return cities;
	}
	
	public ArrayList<ReviewVO> getRecentReviews(String tag) throws SQLException{		//index review list
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> rlist = new ArrayList<ReviewVO>();
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_RECENT_REVIEWS_BY_TAG);
			rs = ps.executeQuery();
			while(rs.next()) {
				rlist.add(new ReviewVO(rs.getInt("review_num"),
									   rs.getString("location"),
									   rs.getString("city"),
									   rs.getString("title"),
									   rs.getString("id")));
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return rlist;
	}
	/*public ArrayList<ReviewVO> getBestReviews(String tag) throws SQLException {				//index�뿉 移댄뀒怨좊━(tag)蹂� review
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> rlist = new ArrayList<ReviewVO>();
		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_RECENT_REVIEWS_BY_TAG);
			ps.setString(1, tag);
			rs = ps.executeQuery();

			ReviewVO rvo = null;
			while (rs.next()) {
				rvo = new ReviewVO();
				rvo.setLocation(rs.getString("location"));
				rvo.setTitle(rs.getString("title"));
				rvo.setReviewNum(rs.getInt("review_num"));
				rvo.setLike(rs.getInt("likes"));
				rvo.setCity(rs.getString("city"));
				rlist.add(rvo);
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return rlist;
	}*/

	public void addLike(int reviewNum) {
		/*
		 * �씠 硫붿꽌�뱶媛� �샇異쒕릺硫� 湲�踰덊샇 post_num�씤 由щ럭�쓽 like �닔媛� 1 利앷��븳�떎.
		 */
		// post_num �씤 �븷 李얘린
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
	}// addLike �씗�젙�벐

	public ArrayList<ReviewVO> getBestReviewByTag(String location , String tag) throws SQLException {		//v1 review list
		 
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
			for (int i = 0; i < list.size(); i++) {
				ArrayList<String> tags = getTags(list.get(i).getReviewNum(), conn);
				list.get(i).setTags(tags);
				ArrayList<String> img = getImages(list.get(i).getReviewNum(), conn);
				list.get(i).setImages(img);
			} // for
		} finally {
			closeAll(rs, ps, conn);
		}
		System.out.println("���� �׽�Ʈ!!!!!!!!!!! " + list);
		return list;
	}// getBestReview �씗�젙�벐

	public ArrayList<FestivalVO> getFestivalInfo(String location) throws SQLException {			///v1 festival list

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
	}// getFestivalInfo 泥좎쭊�벐


	public ArrayList<AttractionVO> getAttraction(String city) throws SQLException {				//v2 tourspot list
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
	}// getAttraction 泥좎쭊�벐			�씠誘몄�!!!!


	public void scrap(String id, int review_num) throws Exception {					//scrap
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
			rvo.setImages(getImages(rvo.getReviewNum(), conn));			//image list
			rvo.setComments(getComments(rvo.getReviewNum(), conn));		//comment list
			//肄붾찘�듃. �씠誘몄�. �벑�벑 媛��졇���빞�븿..
		} finally {
			closeAll(rs, ps, conn);
		}
		return rvo;
	} // checkReview �쑄二쇱벐
	
	public int totalScrapNumber(String id) throws SQLException{ 

		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.TOTAL_SCRAP_COUNT);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) count = rs.getInt(1);
		} finally {
			closeAll(rs, ps, conn);
		}
		return count;
	}
	
	public int totalMyReviewNumber(String id) throws SQLException{ 
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
	
/*	public ArrayList<ReviewVO> searchByTag(String tag) throws SQLException {			//�샇異쒗븯�뒗怨녹뿉�꽌 �뼱�뼡寃� �븘�슂�븷源�
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
	      
	}*/
	
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
					ps = conn.prepareStatement("SELECT review_image FROM review_image WHERE review_num=?");
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
			while(rs.next()) {
				list.add(new ReviewVO(rs.getInt("review_num"), rs.getString("title"), rs.getString("id"), rs.getString("date_writing")));
			}//while
			for(ReviewVO vo : list) {
				if(vo!=null) {

					ps = conn.prepareStatement("SELECT review_image FROM review_image WHERE review_num=?");
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
			ps.setInt(4, rvo.getReviewNum());
			ps.executeUpdate();
							///// DELETE FROM x,x,x WHERE review_num=x test
			int row = ps.executeUpdate();
			System.out.println(row + " row update posting ok..");

		} finally {
			closeAll(ps, conn);
		}
	}
	
	public ArrayList<String> getTags(int reviewNum,Connection conn) throws SQLException{			//get reivew tags
		ArrayList<String> tlist = new ArrayList<String>();
		PreparedStatement ps = conn.prepareStatement(ReviewStringQuery.GET_REVIEW_TAGS);
		ps.setInt(1, reviewNum);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			tlist.add(rs.getString("word"));
		}
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();
		return tlist;
	}
	
	public ArrayList<String> getImages(int reviewNum,Connection conn) throws SQLException{			//get review images

		ArrayList<String> ilist = new ArrayList<String>();
		PreparedStatement ps = conn.prepareStatement(ReviewStringQuery.GET_REVIEW_IMAGES);
		ps.setInt(1, reviewNum);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ilist.add(rs.getString("review_image"));
		}
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();
		return ilist;
	}

	public ArrayList<CommentVO> getComments(int review_num, Connection conn) throws SQLException {	//get review comments
		ArrayList<CommentVO> clist = new ArrayList<CommentVO>();
		PreparedStatement ps = conn.prepareStatement(ReviewStringQuery.GET_REVIEW_COMMENTS);
		ps.setInt(1, review_num);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			clist.add(new CommentVO(rs.getString("id"), rs.getString("comment")));
		}
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();
		return clist;
	}

	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(OracleInfo.URL, OracleInfo.USER, OracleInfo.PASS);
		System.out.println("�뵒鍮� �뿰寃� �꽦怨�!");
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

	public void register(MemberVO vo) throws SQLException{			//member regist
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = getConnect();
			pstmt = conn.prepareStatement(UserStringQuery.REGISTER_USER);
			pstmt.setString(1,vo.getUserName());
			pstmt.setInt(2,vo.getSsn());
			pstmt.setString(3,vo.getId());
			pstmt.setString(4,vo.getPassword());
			pstmt.setString(5,vo.getTel());
			pstmt.setString(6,vo.getMail());
			pstmt.executeUpdate();
			System.out.println(vo.getUserName()+"占쏙옙 회占쏙옙占쏙옙 占쏙옙占�...");
		}finally{
			closeAll(pstmt,conn);
		}
	}
	
	public void updateInfo(MemberVO vo) throws SQLException{		//member update
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = getConnect();
			pstmt=conn.prepareStatement(UserStringQuery.UPDATE_USER);			
			pstmt.setString(1,vo.getPassword());
			pstmt.setString(2,vo.getMail());
			pstmt.setString(3,vo.getTel());
			pstmt.setString(4,vo.getId());
			pstmt.executeUpdate();
			System.out.println("회占쏙옙 占쏙옙占� 占쏙옙占�..");
		}finally{
			closeAll(pstmt,conn);
		}
	}
	
	public boolean idCheck(String id) throws SQLException{			//idcheck
		boolean result=false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = getConnect();
			pstmt = conn.prepareStatement(UserStringQuery.IDCHECK_USER);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()){				
				if(rs.getInt(1)>0)
					result=true;
			}
		}finally{
			closeAll(rs,pstmt,conn);
		}
		return result;
	}
	
	public MemberVO login(String id, String password) throws SQLException{
		MemberVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn=getConnect();
			pstmt=conn.prepareStatement(UserStringQuery.LOGIN_USER);
			pstmt.setString(1,id);
			pstmt.setString(2,password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				//占쌨아야되는곤옙 id 
				vo = new MemberVO(rs.getString("id"));
			}
				
		}finally{
			closeAll(pstmt,conn);
		}
		return vo;
	}
	public ArrayList<String> getTagsByContent(String content){			//writing logic 
		ArrayList<String> tlist = new ArrayList<String>();
		String[] arr = content.split(" ");
		for(int i=0;i<arr.length;i++) {
			if(arr[i].startsWith("#")) {
				tlist.add(arr[i].substring(1));
			}
		}
		return tlist;
	}
	
	public static void main(String[] args) throws SQLException {		//�떒�쐞�뀒�뒪�듃
		/*ArrayList<ReviewVO> vo = new ArrayList<ReviewVO>();
		vo = TourDao.getInstance().getScrapList("yun");
		for(ReviewVO r : vo) {
			System.out.println(r.toString());
		}*/
	}
}
