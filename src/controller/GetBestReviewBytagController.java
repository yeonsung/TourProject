package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.ReviewVO;

public class GetBestReviewBytagController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String category = request.getParameter("tag");
		String location = request.getParameter("location");
		ArrayList<ReviewVO> relist = null;
		try {
			relist = TourDao.getInstance().getBestReviewByTag(location, category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("relist", relist);
		return new ModelAndView("tabResult.jsp");
	}
}
