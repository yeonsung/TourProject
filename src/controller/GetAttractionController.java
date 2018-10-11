
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
		
		ArrayList<AttractionVO> avo = TourDao.getInstance().getAttraction(city);
		/*
		request.setAttribute("pageNo", pageNo);
		*/
		request.setAttribute("avo", avo);
		request.setAttribute("city", city);
		request.setAttribute("location", location);

		return new ModelAndView("attraction2.jsp");
	}

}
