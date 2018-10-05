package query.review;

import model.CommonConstants;
import model.vo.ReviewVO;

public interface ReviewStringQuery {


	String GETCITIES = "SELECT city FROM location WHERE location=?";				// 寃쎄린�룄 -> �뼇�룊,怨좎뼇�벑�벑
	String SEARCH_REVIEW_LIKE = "select likes from review where review_num=?";		// 醫뗭븘�슂�닔 由ы꽩?
	String LIKE_ADD = "update review set likes=likes+1 where review_num=?";			// 醫뗭븘�슂+1
	String INSERT_REVIEW = "INSERT INTO review(review_num, location, city, title, content, date_writing, id)"
			+ "VALUES(review_seq.nextVal, ?, ?, ?, ?, sysdate, ?)";
	String CURRENT_NO = "SELECT review_seq.currVal FROM dual";
	String BEST_REVIEW_LOCATION_TAG = "select review_num, title, likes,city from (select * from review order by likes desc) where rownum<4"
			+ " AND review_num IN ((SELECT review_num FROM tag WHERE word=?)) AND location=?"; // v1에서 왼쪽 리뷰 리스트
	String SCRAP = "insert into scrap values(?,?)";									// 스크랩
	String GET_ATTRACTION = "select spot_name,address,location,city,info,img from tourspot where city=?"; // city별 관광지 정보 return
	String GET_ATTRACTION_IMG= "select spot_image from spot_image where spot_name=?";					  // 관광지 이미지 리턴
	String GET_FESTIVAL_INFO = "select festival_Name,festival_Location,location,city,start_Date,end_Date,agency,img from festival where location=?" + 
			" AND ((start_Date BETWEEN SYSDATE AND SYSDATE+7) OR (SYSDATE BETWEEN start_Date AND end_Date))";// location별 축제정보 return 안되면 start,end Date에 ''추가
	String CHECK_REVIEW = "select * from review where review_num = ?";				// 글 정보 return
//	String SEARCH_BY_TAG = "SELECT review_num,location,city,title,content,date_writing,likes,id "
//			+ "FROM review WHERE review_num = all(select review_num from tag where word=?)";	// �떎�떆
	//String GET_SCRAP_LIST = "select * from review where review_num in (select review_num from scrap where id=?)";
	//String GET_MY_REVIEW = "select * from review where id=?";
//	String GET_BEST_REVIEWS_BY_TAG = "SELECT location,city,title,review_num,likes FROM (SELECT location,title.review_num,likes,city ORDER BY likes desc) "
//			+ "WHERE rownum<10 review_num IN (SELECT review_num FROM tag WHERE word=?)";			// index review list

	String DELETE_REVIEW = "delete from review where review_num=?";			//�궡媛� �벖 由щ럭 �궘�젣
	String DELETE_SCRAP = "delete from scrap where review_num=?";			//�뒪�겕�옪 �궘�젣
	String UPDATE_REVIEW = "update review set location=?, city=?, title=?, content=? where review_num=?";	//由щ럭 �뾽�뜲�씠�듃
	String TOTAL_SCRAP_COUNT = "select count(-1) from scrap where id=?";	//�뒪�겕�옪 媛��닔
	String TOTAL_MY_REVIEW_COUNT = "select count(-1) from review where id=?";// �궡媛� �벖 由щ럭 媛��닔
	
	String GET_REVIEW_TAGS = "select word from tag where review_num=?";							//由щ럭 tag�뱾 return
	String GET_REVIEW_IMAGES = "SELECT review_image FROM review_image WHERE review_num = ?";	//由щ럭 img�뱾 return
	String GET_REVIEW_COMMENTS = "SELECT id,comment FROM comment WHERE review_num = ?";			//由щ럭 comment�뱾 return
	String GET_SCRAP_LIST="select * from review where review_num in"
			+ " (select review_num from"
			+ " (select review_num, ceil(rownum/" + CommonConstants.CONTENT_NUMBER_PER_PAGE + ") page from"
			+ " (select review_num from scrap where id=? order by review_num desc)) where page=?)";		//�뒪�겕�옪 由ъ뒪�듃 由ы꽩
	String GET_MY_REVIEW = "select review_num, title, date_writing, id from"
			+ " (select review_num, title, date_writing, id, ceil(rownum/" + CommonConstants.CONTENT_NUMBER_PER_PAGE + ") page from"
			+ " (select review_num, title, date_writing, id from review where id=? order by review_num desc)) where page=?";	//由щ럭 由ъ뒪�듃 由ы꽩
	String GET_RECENT_REVIEWS_BY_TAG = "SELECT * FROM" 				//index.jsp
			+ "(SELECT review_num, title, location, city,id, ceil(rownum/"+ CommonConstants.CONTENT_NUMBER_PER_PAGE + ") page"
			+ " FROM (SELECT * FROM review ORDER BY review_num desc)" + 
			" WHERE review_num IN((SELECT review_num FROM tag WHERE word = ?))) WHERE page=?";
	
	String TEST = "select * from review where review_num in\n" + 
			"(select review_num from\n" + 
			"(select review_num, ceil(rownum/" + CommonConstants.CONTENT_NUMBER_PER_PAGE + ") page from\n" + 
			"(select review_num from review order by likes desc)) where page=?)";
	String TOTAL_REVIEW_COUNT = "select count(-1) from review";
	String RELATED_REVIEWS = "select * from review where review_num in"
			+ " (select review_num from"
			+ " (select review_num, ceil(rownum/" + CommonConstants.CONTENT_NUMBER_PER_PAGE + ") page from"
			+ " (select review_num from tag where word=? order by review_num desc)) where page=?)";

	String GET_DATA = "select * from tourspot where spot_name ="
			+ "(select distinct word from tag where word=?)";
	
	String CHECK_SPOT = "select * from tourspot where spot_name=?;";
	
	String GET_REVIEW_BY_SEARCH = "select * from review where review_num in"
			+ " (select review_num from"
			+ " (select review_num, ceil(rownum/" + CommonConstants.CONTENT_NUMBER_PER_PAGE + ") page from"
			+ " (select review_num from tag where word="
			+ "(select city from tourspot where spot_name=?) order by review_num desc)) where page=?)";

	String REVIEW_IMG = "SELECT review_image FROM review_image WHERE review_num=?";
	String GET_TOTAL_REVIEW = "SELECT COUNT(-1) FROM review";
}

/*
 * 1-1) .. GET_DATA (�ش� �±װ� tourspot���� �ƴ��� �˻� ����..)
 * select * from tourspot where spot_name =
 * (select distinct word from tag where word='�ι��Ӹ�');
 */

/*
 * GET_DATA���� ��� ���� null�� �ƴ� ��
 * 
 * ===========================================================
 * 
 * 1-2), 3-1) .. RELATED_REVIEWS
 * select * from review where review_num in
 *  (select review_num from
 *  (select review_num, ceil(rownum/6) page from
 *  (select review_num from tag where word='�ι��Ӹ�' order by review_num desc)) where page=1);
 *  
 *  
 * 4-1) ..
 * select * from tourspot where spot_name='���ýĹ���';
 * 
 * 4-2)
 * select * from review where review_num in
 *  (select review_num from
 *  (select review_num, ceil(rownum/2) page from
 *  (select review_num from tag where word=
 * (select city from tourspot where spot_name='���ýĹ���') order by review_num desc)) where page=1);
 */

/*
 * 1. �˻��� tag�� �ִ� ���(tag ������ tourspot �� ��)
 *    1) tag�� ���� ��� ��ܿ� ǥ��
 *    2) tag�� ��õ� ���� ǥ��
 *    
 * 2. �˻��� tag�� �ִ� ���(tag ������ tourspot�� �ƴ� ��-location, city)
 *    1) v1, v2 ��������� ��ũ �ɾ��ֱ�
 *    
 * 3. �˻��� tag�� �ִ� ���(tag ������ tourspot�� �ƴ� ��-�ٸ� �±�)
 *    1) tag�� ��õ� ���� ǥ��
 *    
 * 4. �˻��� tag�� ��� ���(tag ������ tourspot �� ��)
 *    1) �˻��� ���� ��� ��ܿ� ǥ��
 *    2) �˻�� ����� tourspot�� ���� �ű⿡ �ִ� city �����ϰ�
 *       city�� ��õ� ���� ǥ��
 *    
 * 5. �˻��� tag�� ��� ���(tag ������ city,location �� ��)
 *    1) v1, v2 ��������� ��ũ �ɾ��ֱ�
 *    
 * 6. �˻��� tag�� ��� ���(tag ������ tourspot,city,location�� �ƴ� ��)
 *    1) �˻�..x.....
 */




