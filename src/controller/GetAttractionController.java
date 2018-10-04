package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.AttractionVO;
import model.vo.ReviewVO;
import query.review.ReviewStringQuery;

public class GetAttractionController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String city=request.getParameter("city");
		String location= request.getParameter("location");
		String tag=request.getParameter("tag");
		ArrayList<ReviewVO> blist = new ArrayList<ReviewVO>();
		blist = TourDao.getInstance().getBestReviewByTag(location,tag);
		ArrayList<AttractionVO> avo= TourDao.getInstance().getAttraction(city);
		
		request.setAttribute("blist", blist);
		request.setAttribute("avo", avo);
		
		return new ModelAndView("bootstraptemplete.jsp");
	}

}
