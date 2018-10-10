
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

public class GetAttractionController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String city=request.getParameter("city");
		String pageNo = request.getParameter("pageNo");

		String location= request.getParameter("location");
		String tag=request.getParameter("tag");
		ListVO blist = ReviewService.getInstance().getBestReviewByTag(location,tag,pageNo);
		ArrayList<AttractionVO> avo= TourDao.getInstance().getAttraction(city);
		System.out.println(avo+"\n ÇÏ ÂÍ ...");
		request.setAttribute("blist", blist);
		request.setAttribute("avo", avo);
		

		return new ModelAndView("attraction.jsp");
	}

}
