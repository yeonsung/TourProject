package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.ReviewVO;

public class GetBestReviewBytagController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String tag = request.getParameter("tag");
		String location = request.getParameter("location");
		System.out.println(tag+"        "+location);
		ArrayList<ReviewVO> relist = null;
	
		try {
			relist = TourDao.getInstance().getBestReviewByTag(location, tag);
			for(ReviewVO vo : relist) {
				if(vo.getImages().size()!=0)
					vo.setMainImage(vo.getImages().get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("tabResult.jsp");
	}
}

