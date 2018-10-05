package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.ReviewVO;
import service.ListVO;
import service.ReviewService;

public class GetBestReviewBytagController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String tag = request.getParameter("tag");
		String location = request.getParameter("location");
		System.out.println(tag+"        "+location);
		String pageNo = request.getParameter("pageNo");
		
		ListVO relist = null;
	
		try {
			System.out.println("1");
			relist = ReviewService.getInstance().getBestReviewByTag(location, tag, pageNo);
			System.out.println("2");
			request.setAttribute("relist", relist);
			System.out.println(relist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("tabResult.jsp");
	}
}

