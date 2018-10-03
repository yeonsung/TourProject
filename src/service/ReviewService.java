package service;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PagingBean;
import model.dao.TourDao;
import model.vo.ReviewVO;
/*
 * 주로 SELECT 와 관련
 */
public class ReviewService {
	
	private TourDao dao;
	private static ReviewService service = new ReviewService();
	private ReviewService() {
		dao = TourDao.getInstance();
	}
	public static ReviewService getInstance() {
		return service;
	}
	
	public ListVO getScrapList(String id, String pageNo) throws SQLException { // �뒪�겕�옪 紐⑸줉 媛��졇�삤湲�
		int pn = 0;
		if(pageNo==null) pn=1;
		else pn = Integer.parseInt(pageNo);
		
		ArrayList<ReviewVO> list = dao.getScrapList(id, pn);
		int total = dao.totalScrapNumber(id);
		PagingBean pb = new PagingBean(total, pn);
		return new ListVO(list, pb); 
	}
	
	public ListVO getMyReview(String id, String pageNo) throws SQLException { // �뒪�겕�옪 紐⑸줉 媛��졇�삤湲�
		int pn = 0;
		if(pageNo==null) pn=1;
		else pn = Integer.parseInt(pageNo);
		
		ArrayList<ReviewVO> list = dao.getMyReview(id, pn);
		int total = dao.totalMyReviewNumber(id);
		PagingBean pb = new PagingBean(total, pn);
		return new ListVO(list, pb); 
	}
}
