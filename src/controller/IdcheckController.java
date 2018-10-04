package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;

public class IdcheckController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		boolean flag = TourDao.getInstance().idCheck(id);
		request.setAttribute("flag", flag);
		
		return new ModelAndView("idcheck.jsp");
	}
}
