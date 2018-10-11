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
	
	public ListVO getBestReviewByTag(String location, String tag, String pageNo) throws SQLException{
		int pn = 0;
		if(pageNo==null) pn=1;
		else pn = Integer.parseInt(pageNo);
		
		ArrayList<ReviewVO> list = dao.getBestReviewByTag(location, tag, pn);
		int total = dao.totalReviewNumber();
		PagingBean pb = new PagingBean(total, pn);

		return new ListVO(list, pb); 
	}
	
	public ListVO getBestReviewCityByTag(String location, String city, String tag, String pageNo) throws SQLException{
		int pn = 0;
		if(pageNo==null) pn=1;
		else pn = Integer.parseInt(pageNo);
		
		ArrayList<ReviewVO> list = dao.getBestReviewByTagCity(location, city, tag, pn);
		int total = dao.totalReviewNumber();
		PagingBean pb = new PagingBean(total, pn);

		return new ListVO(list, pb); 
	}
	
	public ListVO getRecentReviews(String pageNo) throws SQLException{
	      int pn = 0;
	      if(pageNo==null) pn=1;
	      else pn = Integer.parseInt(pageNo);
	      
	      ArrayList<ReviewVO> list = dao.getRecentReviews(pn);
	      int total = dao.getTotalReview();
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
	
	public ListVO relatedReviews(String tag, String pageNo) throws SQLException { // �뒪�겕�옪 紐⑸줉 媛��졇�삤湲�
		int pn = 0;
		if(pageNo == null) pn=1;
		else pn = Integer.parseInt(pageNo);
		
		ArrayList<ReviewVO> list = dao.relatedReviews(tag, pn);
		int total = dao.totalRelatedReviewNumber(tag);
		PagingBean pb = new PagingBean(total, pn);
		return new ListVO(list, pb); 
	}
	
	public ListVO getReviewBySearch(String tag, String pageNo) throws SQLException { // �뒪�겕�옪 紐⑸줉 媛��졇�삤湲�
		int pn = 0;
		if(pageNo == null) pn=1;
		else pn = Integer.parseInt(pageNo);
		
		ArrayList<ReviewVO> list = dao.getReviewBySearch(tag, pn);
		int total = dao.totalRelatedReviewNumber(tag);
		PagingBean pb = new PagingBean(total, pn);
		return new ListVO(list, pb); 
	}
}
