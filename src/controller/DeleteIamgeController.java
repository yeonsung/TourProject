package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;

public class DeleteIamgeController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int reviewNum = Integer.parseInt(request.getParameter("reviewNum"));
		String img = request.getParameter("img");
		
		TourDao.getInstance().deleteImage(reviewNum, img);
		
		
		return null;
	}
}
