package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.ReviewVO;

public class MainPageController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getParameter("tag").equals(null)) {
			ArrayList<ReviewVO> rlist = TourDao.getInstance().getRecentReviews("¸ÀÁý");
			return new ModelAndView("index.jsp");

		}
		
		else {
			
			
			
			return new ModelAndView();
		}
	}

}
