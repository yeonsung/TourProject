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
		int size=0;
		if(request.getParameter("size")!=null) 
			size = Integer.parseInt(request.getParameter("size")); // relist size
		String location = request.getParameter("location");
		
		System.out.println(tag + "        " + location);
		String pageNo = request.getParameter("pageNo");

		ListVO relist = null;

		try {
			relist = ReviewService.getInstance().getBestReviewByTag(location, tag, pageNo);
			request.setAttribute("relist", relist);
			
			request.setAttribute("listSize", relist.getList().size() + "");
			if (relist.getList().size() - size < 3) {
				// 더 가져올 게 없는 거지.
				request.setAttribute("flag", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("tabResult.jsp");
	}
}
