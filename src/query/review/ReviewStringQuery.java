package query.review;

import model.CommonConstants;
import model.vo.ReviewVO;

public interface ReviewStringQuery {

	String GETCITIES = "SELECT city FROM location WHERE location=?"; // 寃쎄린�룄 -> �뼇�룊,怨좎뼇�벑�벑
	String SEARCH_REVIEW_LIKE = "select likes from review where review_num=?"; // 醫뗭븘�슂�닔 由ы꽩?
	String INSERT_REVIEW = "INSERT INTO review(review_num, location, city, title, content, date_writing, id)"
			+ "VALUES(review_seq.nextVal, ?, ?, ?, ?, sysdate, ?)";
	String CURRENT_NO = "SELECT review_seq.currVal FROM dual";
	String GET_IMAGE_LIST = "select img from tourspot where city=?";
	String SEARCH_BY_TAG = "SELECT review_num,location,city,title,content,date_writing,likes,id "
			+ "FROM review WHERE review_num = all(select review_num from tag where word=?)";
	/*String BEST_REVIEW_LOCATION_TAG = "select review_num, title, likes,city from (select * from review order by likes desc) where rownum<4"
			+ " AND review_num IN ((SELECT review_num FROM tag WHERE word=?)) AND location=?"; // v1에서 왼쪽 리뷰 리스트
*/
 	String INSERT_REVIEWIMAGE = "INSERT INTO review_image(review_num, review_image) VALUES(?, ?)";
  String INSERT_TAG = "INSERT INTO tag(review_num, word) VALUES(?, ?)";
	String CHECK_REVIEW = "select * from review where review_num = ?"; // 글 정보 return
	
	String BEST_REVIEW_LOCATION_TAG = "select review_num, title, likes,city from (select * from review order by likes desc) where rownum<4"
			+ " AND review_num IN ((SELECT review_num FROM tag WHERE word=?)) AND location=?"; // v1에서 왼쪽 리뷰 리스트
	String SCRAP = "insert into scrap values(?,?)";									// 스크랩
	String GET_ATTRACTION = "select spot_name,address,location,city,info from tourspot where city=?"; // city별 관광지 정보 return
	String GET_ATTRACTION_IMG= "select spot_image from spot_image where spot_name=?";					  // 관광지 이미지 리턴
	String GET_FESTIVAL_INFO = "select festival_Name,festival_Location,location,city,start_Date,end_Date,agency,img from festival where location=?" + 
			" AND ((start_Date BETWEEN SYSDATE AND SYSDATE+7) OR (SYSDATE BETWEEN start_Date AND end_Date))";// location별 축제정보 return 안되면 start,end Date에 ''추가
//	String SEARCH_BY_TAG = "SELECT review_num,location,city,title,content,date_writing,likes,id "
//			+ "FROM review WHERE review_num = all(select review_num from tag where word=?)";	// �떎�떆
	//String GET_SCRAP_LIST = "select * from review where review_num in (select review_num from scrap where id=?)";
	//String GET_MY_REVIEW = "select * from review where id=?";
	//String GET_BEST_REVIEWS_BY_TAG = "SELECT location,city,title,review_num,likes FROM (SELECT location,title.review_num,likes,city ORDER BY likes desc) "
	//		+ "WHERE rownum<10 review_num IN (SELECT review_num FROM tag WHERE word=?)";			// index review list

	String DELETE_REVIEW = "delete from review where review_num=?";
	String DELETE_ALL_SCRAP = "delete from scrap where review_num=?";
	String DELETE_TAG = "delete from tag where review_num=?";
	String DELETE_REVIEW_IMG = "delete from review_image where review_num=? and review_image=?";
	// 스크랩
		String INSERT_SCRAP = "insert into scrap(id,review_num) values(?,?)";
		String DELETE_SCRAP = "delete from scrap where id=? AND review_num=?";
		String SCRAP_SELECT = "select * from scrap where id=? AND review_num=?";
		String CHECK_SCRAP = "select * from scrap where id=? AND review_num=?";
		String GET_SCRAP_LIST = "select * from review where review_num in" + " (select review_num from"
				+ " (select review_num, ceil(rownum/" + CommonConstants.CONTENT_NUMBER_PER_PAGE + ") page from"
				+ " (select review_num from scrap where id=? order by review_num desc)) where page=?)"; // �뒪�겕�옪 由ъ뒪�듃 由ы꽩
		
	//좋아요
		String LIKE_ADD = "update review set likes=likes+1 where review_num=?"; 
		String LIKE_REMOVE = "update review set likes=likes-1 where review_num=?";
		String INSERT_CHECK = "insert into likes(id,review_num) values(?,?)";
		String DELETE_CHECK = "delete from likes where id=? AND review_num=?";
		String CHECK_SELECT = "select * from likes where id=? AND review_num=?";	
		
	String UPDATE_REVIEW = "update review set location=?, city=?, title=?, content=? where review_num=?";
	String TOTAL_SCRAP_COUNT = "select count(-1) from scrap where id=?";
	String TOTAL_MY_REVIEW_COUNT = "select count(-1) from review where id=?";
	String TOTAL_RELATED_REVIEW_COUNT = "select count(-1) from review where review_num in"
			+ " (select review_num from tag where word=?)";

	String GET_REVIEW_TAGS = "select word from tag where review_num=?"; // 由щ럭 tag�뱾 return
	String GET_REVIEW_IMAGES = "SELECT review_image FROM review_image WHERE review_num = ?"; // 由щ럭 img�뱾 return
	String GET_REVIEW_COMMENTS = "SELECT id,content FROM comments WHERE review_num = ?"; // 由щ럭 comment�뱾 return
	
	String GET_MY_REVIEW = "select review_num, title, date_writing, id from"
			+ " (select review_num, title, date_writing, id, ceil(rownum/" + CommonConstants.CONTENT_NUMBER_PER_PAGE
			+ ") page from"
			+ " (select review_num, title, date_writing, id from review where id=? order by review_num desc)) where page=?"; // 由щ럭
	
	/*
	 * String TEST = "select * from review where review_num in\n" +
	 * "(select review_num from\n" + "(select review_num, ceil(rownum/" +
	 * CommonConstants.CONTENT_NUMBER_PER_PAGE + ") page from\n" +
	 * "(select review_num from review order by likes desc)) where page=?)";
	 */

	String GET_BESTREVIEW_BY_TAG_LOCA = "SELECT * FROM " + "(SELECT review_num,city,title,ceil(rownum/3) page FROM ("
			+ "SELECT review_num,title,city FROM (SELECT * FROM review WHERE review_num IN ((SELECT review_num from tag WHERE word=?)) AND location=?) ORDER BY likes desc))"
			+ "WHERE page<=?";
	
	String GET_BESTREVIEW_BY_TAG_CITY = "SELECT * FROM " + "(SELECT review_num,title,ceil(rownum/3) page FROM ("
			+ "SELECT review_num,title FROM (SELECT * FROM review WHERE review_num IN ((SELECT review_num from tag WHERE word=?)) AND city =? AND location=?) ORDER BY likes desc))"
			+ "WHERE page<=?";
	
	String TOTAL_REVIEW_COUNT = "select count(-1) from review";
	String RELATED_REVIEWS = "select * from review where review_num in" + " ((select review_num from"
			+ " (select review_num, ceil(rownum/" + CommonConstants.CONTENT_NUMBER_PER_PAGE + ") page from"
			+ " (select review_num from tag where word=? order by review_num desc)) where page=?))";

	String GET_DATA = "select * from tourspot where spot_name =" + "(select distinct word from tag where word=?)";

	String CHECK_SPOT = "select * from tourspot where spot_name=?";

	String GET_RECENT_REVIEWS = "SELECT * FROM" 				//index.jsp
			+ "(SELECT review_num, title, location, city,id, ceil(rownum/10) page"
			+ " FROM (SELECT * FROM review ORDER BY review_num desc)" + 
			") WHERE page<=?";
	String INSERT_REVIEWIMAGE = "INSERT INTO review_image(review_num, review_image) VALUES(?, ?)";
	String INSERT_TAG = "INSERT INTO tag(review_num, word) VALUES(?, ?)";
	String GET_REVIEW_BY_SEARCH = "select * from review where review_num in" + " (select review_num from"
      + " (select review_num, ceil(rownum/" + CommonConstants.CONTENT_NUMBER_PER_PAGE + ") page from"
			+ " (select review_num from tag where word="
			+ "(select city from tourspot where spot_name=?) order by review_num desc)) where page=?)";

	String REVIEW_IMG = "SELECT review_image FROM review_image WHERE review_num=?";
	String CHECK_TAG_BY_LOCATION = "select distinct location from location where location=?";
	String CHECK_TAG_BY_CITY = "select distinct city from location where city=?";
	String TAG_EXIST = "select * from tag where word=?";
	String GET_TOTAL_REVIEW = "SELECT COUNT(-1) FROM review";
}

/*

 * 1-1) .. GET_DATA (해당 태그가 tourspot인지 아닌지 검색도 가능..)
 * select * from tourspot where spot_name =
 * (select distinct word from tag where word='두물머리');
 */

/*
 * GET_DATA에서 얻은 값이 null이 아닐 때
 * 
 * ===========================================================
 * 

 * 1-2), 3-1) .. RELATED_REVIEWS
 * select * from review where review_num in
 *  (select review_num from
 *  (select review_num, ceil(rownum/6) page from
 *  (select review_num from tag where word='두물머리' order by review_num desc)) where page=1);
 *  
 *  
 * 4-1) ..
 * select * from tourspot where spot_name='한택식물원';
 * 
 * 4-2)
 * select * from review where review_num in
 *  (select review_num from
 *  (select review_num, ceil(rownum/2) page from
 *  (select review_num from tag where word=
 * (select city from tourspot where spot_name='한택식물원') order by review_num desc)) where page=1);
 * 
 * 
 * 
 * 2..
 * CHECK_TAG_BY_LOCATION
 * CHECK_TAG_BY_CITY
 */

/*
 * 1. 검색결과가 tag에 있는 경우(tag 내용이 tourspot 일 때)
 *    1) tag에 대한 정보 상단에 표시... ok
 *    2) tag와 관련된 리뷰 표시... ok
 *    
 * 2. 검색결과가 tag에 있는 경우(tag 내용이 tourspot이 아닐 때-location, city)
 *    1) v1, v2 페이지로의 링크 걸어주기... ok
 *    
 * 3. 검색결과가 tag에 있는 경우(tag 내용이 tourspot이 아닐 때-다른 태그)
 *    1) tag와 관련된 리뷰 표시... ok
 *    
 * 4. 검색결과가 tag에 없는 경우(tag 내용이 tourspot 일 때)
 *    1) 검색결과에 대한 정보 상단에 표시... ok
 *    2) 검색어 가지고 tourspot에 가서 거기에 있는 city 리턴하고
 *       city와 관련된 리뷰 표시... ok
 *    
 * 5. 검색결과가 tag에 없는 경우(tag 내용이 city,location 일 때)
 *    1) v1, v2 페이지로의 링크 걸어주기... ok
 *    
 * 6. 검색결과가 tag에 없는 경우(tag 내용이 tourspot,city,location가 아닐 때)
 *    1) 검색..x.....
 */


/*
 * updateReview
 * 
 * 수정버튼 누름 -> controller(내용 받아오기- id, location, city, title, categorys, content, image)
 * -> write.jsp ->  표시 -> 작성누르면 -> updatedate controller (기존 것들 삭제, 업데이트)
 * 
 * controller
 * CHECK_REVIEW(checkReview) + GET_REVIEW_TAGS(getTags) + GET_REVIEW_IMAGES(getImages)
 * 
 * 
 * 
 * updatedate controller (기존 것들 삭제, 업데이트)
 * 
 * update review set location=?, city=?, title=?, content=? where review_num=?;
 * delete from tag where review_num=?;
 * INSERT_TAG
 * 
*/


/*
 * delete from review_image where review_num=? and review_image=?;
 * 
 */


