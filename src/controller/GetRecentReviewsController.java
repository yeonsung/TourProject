package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.ReviewVO;
import service.ListVO;
import service.ReviewService;

public class GetRecentReviewsController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNo = request.getParameter("pageNo");
		ListVO rlist = ReviewService.getInstance().getRecentReviews(pageNo);
		request.setAttribute("rlist", rlist);
		request.setAttribute("count", Integer.parseInt(pageNo)+1);
		return new ModelAndView("tabResultIndex.jsp");
	}

}