package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.ReviewVO;

public class GetRecentReviewsController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tag;
		if(request.getParameter("tag").equals(null))
			 tag = "¸ÀÁý";
		tag = request.getParameter("tag");
		ArrayList<ReviewVO> rlist = TourDao.getInstance().getRecentReviews(tag);
		request.setAttribute("rlist", rlist);
		System.out.println(rlist+"^^");
		return new ModelAndView("tabResultIndex.jsp");
	}

}
