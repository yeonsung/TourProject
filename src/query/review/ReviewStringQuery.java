package query.review;

public interface ReviewStringQuery {
	String SEARCH_BY_TAG = "SELECT review_num,location,city,title,content,date_writing,likes,id "
			+ "FROM review WHERE review_num = all(select review_num from tag where word=?)";
	String GET_SCRAP_LIST = "select * from review where review_num in (select review_num from scrap where id=?)";
	String CHECK_REVIEW = "select * from review where review_num = ?";
	String GET_MY_REVIEW = "select * from review where id=?";
	String DELETE_REVIEW = "delete from review where review_num=?";
	String UPDATE_REVIEW = "update review set location=?, city=?, title=?, content=? where review_num=?";
	String TOTAL_SCRAP_COUNT = "select count(-1) from scrap";
}
