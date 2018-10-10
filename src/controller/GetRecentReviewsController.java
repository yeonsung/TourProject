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
		String tag;
		if(request.getParameter("tag").equals(null))
			 tag = "¸ÀÁý";
		tag = request.getParameter("tag");
		String pageNo = request.getParameter("PageNo");
		ListVO rlist = ReviewService.getInstance().getRecentReviews(tag, pageNo);
		request.setAttribute("rlist", rlist);
		return new ModelAndView("tabResultIndex.jsp");
	}

}