package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("vo") != null) { // ·Î±×ÀÎ »óÅÂÀÌ´Ù.
			session.invalidate(); 
		}
		return new ModelAndView("loginresult.jsp");
	}

}