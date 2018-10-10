
package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.AttractionVO;

import model.vo.ReviewVO;
import query.review.ReviewStringQuery;
import service.ListVO;
import service.ReviewService;

public class GetAttractionController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String city = request.getParameter("city");
		String pageNo = request.getParameter("pageNo");

		String location = request.getParameter("location");
		String tag = request.getParameter("tag");
		ListVO blist = ReviewService.getInstance().getBestReviewByTag(location, tag, pageNo);
		ListVO cblist = ReviewService.getInstance().getBestReviewByTagCity(city, tag, pageNo);
		ArrayList<AttractionVO> avo = TourDao.getInstance().getAttraction(city);
		request.setAttribute("blist", blist);
		request.setAttribute("cblist", cblist);
		request.setAttribute("avo", avo);

		return new ModelAndView("attraction.jsp");
	}

}
