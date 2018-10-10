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
			 tag = "맛집";
		tag = request.getParameter("tag");
		String pageNo = request.getParameter("PageNo");
		ArrayList<ReviewVO> rlist = ReviewService.getInstance().getRecentReviews(tag);
		request.setAttribute("rlist", rlist);
		System.out.println("rlist : "+rlist);
		return new ModelAndView("tabResultIndex.jsp");
	}

}