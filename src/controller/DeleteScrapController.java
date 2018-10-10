package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;

public class DeleteScrapController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		int reviewNum = Integer.parseInt(request.getParameter("reviewNum"));
		TourDao.getInstance().deleteScrap(reviewNum);
		
		return new ModelAndView("scrap.do?id=" + id);
	}

}
