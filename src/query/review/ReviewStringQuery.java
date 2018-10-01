package query.review;

public interface ReviewStringQuery {

	String GETCITIES = "SELECT city FROM location WHERE location=?";
	String SEARCH_REVIEW_LIKE = "select likes from review where review_num=?";
	String LIKE_ADD = "update review set likes=likes+1 where review_num=?";
	String BEST_REVIEW_LOCATION_TAG = "select review_num, title, likes from (select * from review order by likes desc) where rownum<4"
			+ " AND review_num IN (SELECT review_num FROM tag WHERE tag=?) AND location=?";
	String GET_TAG_LIST = "select word from tag where review_num=?";
	String SCRAP = "insert into scrap values(?,?)";
	String GET_ATTRACTION = "select spot_name,address,location,city,info,img from tourspot where city=?";
	String GET_FESTIVAL_INFO = "select festival_Name,festival_Location,location,city,start_Date,end_Date,agency from festival where location=?";
	String GET_IMAGE_LIST = "select img from tourspot where city=?";
	String CHECK_REVIEW = "select * from review where review_num = ?";
	String SEARCH_BY_TAG = "SELECT review_num,location,city,title,content,date_writing,likes,id "
			+ "FROM review WHERE review_num = all(select review_num from tag where word=?)";
	String GET_SCRAP_LIST = "select * from review where review_num in (select review_num from scrap where id=?)";
	String GET_MY_REVIEW = "select * from review where id=?";
	String GET_BEST_REVIEWS = "SELECT location,city,title,review_num,likes FROM (SELECT location,title.review_num,likes,city ORDER BY likes desc) "
			+ "WHERE rownum<10 review_num IN (SELECT review_num FROM tag WHERE tag=?)";
	String DELETE_REVIEW = "delete from review where review_num=?";
	String UPDATE_REVIEW = "update review set location=?, city=?, title=?, content=? where review_num=?";
	String TOTAL_SCRAP_COUNT = "select count(-1) from scrap";
	
	String GET_REVIEW_IMAGES = "SELECT review_image FROM review_image WHERE review_num = ?";
	String GET_REVIEW_COMMENTS = "SELECT id,comment FROM comment WHERE review_num = ?";
}
