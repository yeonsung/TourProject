package query.review;

public interface ReviewStringQuery {
	String GETCITIES = "SELECT city FROM location WHERE location=?";
	String SEARCH_REVIEW = "select likes from review where review_num=?";
	String LIKE_ADD = "update review set likes=? where review_num=?";
	String BEST_REVIEW = "select review_num, content, likes from (select * from review order by likes desc) where rownum<4";
	String GET_TAG_LIST = "select word from tag where review_num=?";
	String SCRAP = "insert into scrap values(?,?)";
	String GET_ATTRACTION = "select spot_name,address,location,city,info,img from tourspot where city=?";
	String GET_FESTIVAL_INFO = "select festival_Name,festival_Location,location,city,start_Date,end_Date,agency from festival where location=?";
	String GET_IMAGE_LIST = "select img from tourspot where city=?";
	String CHECK_REVIEW = "select * from review where review_num = ?";
}
