package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TourDao;
import model.vo.MemberVO;

public class AddCommentController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		int reviewNum = Integer.parseInt(request.getParameter("reviewNum"));
		MemberVO vo = (MemberVO)session.getAttribute("vo");
		String content = request.getParameter("content");
		if(session.getAttribute("vo")!=null) {
			TourDao.getInstance().addComment(reviewNum, vo.getId(), content);
			
		}
		return new ModelAndView("index.jsp");
	}

}
