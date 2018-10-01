package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.ReviewVO;

public class MyReviewController implements Controller{
/*
 *  ArrayList<ReviewVO> getMyReview(String id)
 *  void deleteReview(int reviewNum)
 *  void updateReview(ReviewVO rvo)
 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		ArrayList<ReviewVO> rlist = TourDao.getInstance().getMyReview(id);
		request.setAttribute("rlist", rlist);
		
		return new ModelAndView("myReview.jsp");
	}

}

