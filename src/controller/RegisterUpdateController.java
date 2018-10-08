package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TourDao;
import model.vo.MemberVO;

public class RegisterUpdateController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		String tel = request.getParameter("tel");
		
		MemberVO vo = new MemberVO(password, mail, tel);
		TourDao.getInstance().updateInfo(vo);
		// 업데이트에서 제일 중요한 부분
		// 세션에 수정된 정보를 반드시 바인딩하는 부분을 넣어야한다.(디비만 바뀌고 세션은 안바뀌기 때문)

		return new ModelAndView("registerupdate.jsp");
	}
}