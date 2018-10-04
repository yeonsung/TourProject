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
import model.vo.AttractionVO;
import model.vo.CommentVO;
import model.vo.FestivalVO;
import model.vo.ReviewVO;
import query.review.ReviewStringQuery;

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
			System.out.println("드라이버 로딩 성공ㅎㅎ");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<String> getCities(String location) throws SQLException {
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

	public ArrayList<ReviewVO> getBestReviews(String tag) throws SQLException { // index�� ī�װ�(tag)�� review
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
	}

	public void addLike(int reviewNum) {
		/*
		 * �� �޼��尡 ȣ��Ǹ� �۹�ȣ post_num�� ������ like �� 1 ���Ѵ�.
		 */
		// post_num �� �� ã��
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
				System.out.println("reviewNum�� like�� 1 ��! :: " + rs.getInt("likes"));
			}
		} catch (Exception e) {
		}
	}// addLike �����

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

	public ArrayList<ReviewVO> getBestReviewByTag(String location, String tag) throws SQLException { // location : ��/��
																										// tag: review
																										// ī�װ�

		// * �� �޼��尡 ȣ��Ǹ�.. DB�� ����� ��� Review�� ��ƿ� �� ���ؼ� �ϴ�� ��� 3������
		// ����Ʈ�� �����ϱ�! �׸��� ��
		// * ����Ʈ�� review_num�� tag ���̺� �ִ� review_num�̶� ��� �� Ȯ���ؼ�

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
				vo.setCity(rs.getString("city"));
				list.add(vo);
			}
			for (int i = 0; i < list.size(); i++) {
				ArrayList<String> tList = getTags(list.get(i).getReviewNum(), conn);
				ArrayList<String> iList = getImages(list.get(i).getReviewNum(), conn);
				list.get(i).setImages(iList);
				list.get(i).setTags(tList);
			} // for
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}// getBestReview �����

	public ArrayList<FestivalVO> getFestivalInfo(String location) throws SQLException { /// ���غ���
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
	}// getFestivalInfo ö��

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
	}// getAttraction ö�� �̹���!!!!

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
	} // scrap ö��

	public ReviewVO checkReview(int reviewNum) throws SQLException { // �� �ȸ�ϱ�
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
			rvo.setImages(getImages(rvo.getReviewNum(), conn)); // image list
			rvo.setComments(getComments(rvo.getReviewNum(), conn)); // comment list
			// �ڸ�Ʈ. �̹���. ��� ����;���..
		} finally {
			closeAll(rs, ps, conn);
		}
		return rvo;
	} // checkReview ��־�

	public int totalScrapNumber() throws SQLException {
		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.TOTAL_SCRAP_COUNT);
			rs = ps.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
		} finally {
			closeAll(rs, ps, conn);
		}
		return count;
	}

	public ArrayList<ReviewVO> searchByTag(String tag) throws SQLException { // ȣ���ϴ°�� ��� �ʿ��ұ�
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.SEARCH_BY_TAG);
			ps.setString(1, tag);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ReviewVO(rs.getInt("review_num"), rs.getString("title"), rs.getString("id"),
						rs.getString("location"), rs.getString("city"), rs.getString("content"),
						rs.getString("date_writing"), rs.getInt("likes")));

			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;

	}

	public ArrayList<ReviewVO> getScrapList(String id) throws SQLException { // ��ũ�� ��� ������
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_SCRAP_LIST);
			ps.setString(1, id);
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

	public ArrayList<ReviewVO> getMyReview(String id) throws SQLException { // ���� �� �� ������
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();

		try {
			conn = getConnect();
			ps = conn.prepareStatement(ReviewStringQuery.GET_MY_REVIEW);
			ps.setString(1, id);
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

	public void deleteReview(int reviewNum) throws SQLException { // �� ����ϱ�
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
	public void updateReview(ReviewVO rvo) throws SQLException { // �� ����ϱ�(����� ��������......)
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

	public ArrayList<String> getImages(int reviewNum, Connection conn) throws SQLException { // ����# ����� �̹���� ����
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
	
	public ArrayList<String> getTags(int reviewNum, Connection conn) throws SQLException { // ����# ����� �̹���� ����
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

	public ArrayList<CommentVO> getComments(int review_num, Connection conn) throws SQLException {
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

	public void register(MemberVO vo) throws SQLException {
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
				// �޾ƾߵǴ°� id
				vo = new MemberVO(rs.getString("id"));
			}

		} finally {
			closeAll(pstmt, conn);
		}
		return vo;
	}

	public ArrayList<String> getTagsByContent(String content) {
		ArrayList<String> tlist = new ArrayList<String>();
		String[] arr = content.split(" ");
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].startsWith("#")) {
				tlist.add(arr[i].substring(1));
			}
		}
		return tlist;
	}

	public static void main(String[] args) throws SQLException { // 단위테스트
		/*
		 * ArrayList<ReviewVO> vo = new ArrayList<ReviewVO>(); vo =
		 * TourDao.getInstance().getScrapList("yun"); for(ReviewVO r : vo) {
		 * System.out.println(r.toString()); }
		 */
		//TourDao.getInstance().getBestReviewByTag("경기도", "맛집");
	
	}
}