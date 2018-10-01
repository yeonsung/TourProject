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
		String path = "index.jsp";
		try{
			MemberVO vo=TourDao.getInstance().login(id, password);
			if(vo !=null){ // 로그인 성공
				HttpSession session = request.getSession();
				session.setAttribute("vo", vo);
				path="login_ok.jsp";
			}else{
				path = "login_fail.jsp";
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return new ModelAndView("index.jsp");
	}
}