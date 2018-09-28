package service;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PagingBean;
import model.review.ReviewDao;
import model.review.ReviewVO;
/*
 * 주로 SELECT 와 관련
 */
public class ReviewService {
	
	private ReviewDao dao;
	private static ReviewService service = new ReviewService();
	private ReviewService() {
		dao = ReviewDao.getInstance();
	}
	public static ReviewService getInstance() {
		return service;
	}
	
/*	public ListVO getPostingList(String pageNo) throws SQLException{
		int pn = 0;
		if(pageNo==null) pn=1;
		else pn = Integer.parseInt(pageNo);
		
		int totalContents = dao.totalReviewNumber();
		ArrayList<ReviewVO> list = dao.getReviewList(pn);
		PagingBean pb = new PagingBean(totalContents, pn);
		
		return new ListVO(list, pb);
	}*/
	
}
