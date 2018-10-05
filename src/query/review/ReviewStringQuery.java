package query.review;

import model.CommonConstants;

public interface ReviewStringQuery {


	String GETCITIES = "SELECT city FROM location WHERE location=?";				
	String SEARCH_REVIEW_LIKE = "select likes from review where review_num=?";		
	String LIKE_ADD = "update review set likes=likes+1 where review_num=?";			
	String INSERT_REVIEW = "INSERT INTO review(review_num, location, city, title, content, date_writing, id)"
			+ "VALUES(review_seq.nextVal, ?, ?, ?, ?, sysdate, ?)";
	String CURRENT_NO = "SELECT review_seq.currVal FROM dual";
	String BEST_REVIEW_LOCATION_TAG = "select review_num, title, likes, city from (select * from review order by likes desc) where rownum<10"
			+ " AND review_num IN ((SELECT review_num FROM tag WHERE word=?)) AND location=?"; // v1�뿉�꽌 �쇊履� 由щ럭 由ъ뒪�듃
	String SCRAP = "insert into scrap values(?,?)";									// �뒪�겕�옪
	String GET_ATTRACTION = "select spot_name,address,location,city,info,img from tourspot where city=?"; // city蹂� 愿�愿묒� �젙蹂� return
	String GET_ATTRACTION_IMG= "select spot_image from spot_image where spot_name=?";					  // 愿�愿묒� �씠誘몄� 由ы꽩
	String GET_FESTIVAL_INFO = "select festival_Name,festival_Location,location,city,start_Date,end_Date,agency from festival where location=?" + 
			" AND (start_Date <=(SELECT SYSDATE + 7 FROM DUAL) OR SYSDATE BETWEEN start_Date AND end_Date)";// location蹂� 異뺤젣�젙蹂� return �븞�릺硫� start,end Date�뿉 ''異붽�
	String CHECK_REVIEW = "select * from review where review_num = ?";				// 湲� �젙蹂� return
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
	String GET_RECENT_REVIEWS_BY_TAG = "SELECT review_num, title, location, city,id FROM (SELECT * FROM review ORDER BY review_num desc)" + 
			" WHERE review_num IN((SELECT review_num FROM tag WHERE word = ?)) AND rownum<";		//index review list
	String TEST = "select * from review where review_num in\n" + 
			"(select review_num from\n" + 
			"(select review_num, ceil(rownum/" + CommonConstants.CONTENT_NUMBER_PER_PAGE + ") page from\n" + 
			"(select review_num from review order by likes desc)) where page=?)";
	String TOTAL_REVIEW_COUNT = "select count(-1) from review";
}










