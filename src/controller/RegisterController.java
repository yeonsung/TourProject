package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.MemberVO;

public class RegisterController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		int ssn = Integer.parseInt(request.getParameter("ssn"));
		String mail = request.getParameter("mail");
		String tel = request.getParameter("tel");
		
		MemberVO rvo = new MemberVO(username, ssn, id, password, tel, mail);
		TourDao.getInstance().register(rvo);
		
		return new ModelAndView("login.jsp");
	}

}
