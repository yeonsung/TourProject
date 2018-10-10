package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.MemberVO;

public class FindIdPassController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userName= request.getParameter("username");
		int ssn = Integer.parseInt(request.getParameter("ssn"));
		String tel = request.getParameter("tel");
		String path = "findidpass.jsp";
		String flag = "fail";
		MemberVO rvo = TourDao.getInstance().findIdPass(userName, ssn, tel);
		if(rvo != null) {
			flag = "ok";
			request.setAttribute("rvo", rvo);
			request.setAttribute("check", flag);
		} else {
			flag = "fail";
		}
		System.out.println(flag);
		return new ModelAndView(path);
	}

}
