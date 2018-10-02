package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import config.OracleInfo;
import model.vo.MemberVO;
import query.user.UserStringQuery;
import model.CommonConstants;
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
			System.out.println("�뱶�씪�씠踰� 濡쒕뵫 �꽦怨�");
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
	
	public ArrayList<ReviewVO> getBestReviews(String tag) throws SQLException {				//index�뿉 移댄뀒怨좊━(tag)蹂� review
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

	public ArrayList<ReviewVO> getBestReviewByTag(String location , String tag) throws SQLException {		//location : �떆/援�     tag: review 移댄뀒怨좊━
		
		 //* �씠 硫붿꽌�뱶媛� �샇異쒕릺硫�.. DB�뿉 ���옣�맂 紐⑤뱺 Review�쓽 醫뗭븘�슂 �닔瑜� 鍮꾧탳�빐�꽌 �씪�떒�� �긽�쐞 3媛쒓퉴吏�留� 由ъ뒪�듃濡� 由ы꽩�븯湲�! 洹몃━怨� �씠
		// * 由ъ뒪�듃�뿉 review_num�씠 tag �뀒�씠釉붿뿉 �엳�뒗 review_num�씠�옉 媛숈� 吏� �솗�씤�빐�꽌
		 
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
	}// getBestReview �씗�젙�벐

	public ArrayList<FestivalVO> getFestivalInfo(String location) throws SQLException {			///�깮媛곹빐蹂닿린
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
	}// getAttraction 泥좎쭊�벐			�씠誘몄�!!!!

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
	} // scrap 泥좎쭊�벐

	public ReviewVO checkReview(int reviewNum) throws SQLException { // 湲� 議고쉶�븯湲�
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
			if(rs.next()) count = rs.getInt(1);
		} finally {
			closeAll(rs, ps, conn);
		}
		return count;
	}
	
	public ArrayList<ReviewVO> searchByTag(String tag) throws SQLException {			//�샇異쒗븯�뒗怨녹뿉�꽌 �뼱�뼡寃� �븘�슂�븷源�
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
	
	public ArrayList<ReviewVO> getScrapList(String id, int pageNo) throws SQLException { // �뒪�겕�옪 紐⑸줉 媛��졇�삤湲�
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
	
	public ArrayList<ReviewVO> getMyReview(String id, int pageNo) throws SQLException { // �궡媛� �벖 湲� 媛��졇�삤湲�
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
	
	public void deleteReview(int reviewNum) throws SQLException { // 湲� �궘�젣�븯湲�
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
	
	public void deleteScrap(int reviewNum) throws SQLException { // 湲� �궘�젣�븯湲�
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
	public void updateReview(ReviewVO rvo) throws SQLException { // 湲� �닔�젙�븯湲�(蹂대쪟蹂대쪟 蹂대쪟蹂대쪟蹂대쪟蹂대쪟酉�......)
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
	
	public ArrayList<String> getImages(int reviewNum,Connection conn) throws SQLException{			//由щ럭# 媛�吏�怨� �씠誘몄��뱾 由ы꽩
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
	
	public void register(MemberVO vo) throws SQLException{
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
	
	public void updateInfo(MemberVO vo) throws SQLException{
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
	
	public boolean idCheck(String id) throws SQLException{
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
	
	public static void main(String[] args) throws SQLException {		//�떒�쐞�뀒�뒪�듃
		/*ArrayList<ReviewVO> vo = new ArrayList<ReviewVO>();
		vo = TourDao.getInstance().getScrapList("yun");
		for(ReviewVO r : vo) {
			System.out.println(r.toString());
		}*/
	}
}