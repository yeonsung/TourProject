package controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import filter.MVCFilter;
import model.dao.TourDao;
import model.vo.MemberVO;

public class LoginController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String path = "loginresult.jsp";
		
		MemberVO vo=TourDao.getInstance().login(id, password);
		System.out.println(vo);
		if(vo !=null){ // 쨌횓짹횞�횓 쩌쨘째첩
			HttpSession session = request.getSession();
			session.setAttribute("vo", vo);
			request.setAttribute("check", true);
		}else{
			path ="login.jsp";
			request.setAttribute("check", false);
		}
		
		return new ModelAndView(path);
	}

}