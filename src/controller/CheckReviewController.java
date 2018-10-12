package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TourDao;
import model.vo.MemberVO;
import model.vo.ReviewVO;

public class CheckReviewController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int reviewNum = Integer.parseInt(request.getParameter("num"));
		HttpSession session = request.getSession();
		boolean flag=false;
		boolean flag2=false;
		MemberVO vo = null;
		
		if(session.getAttribute("vo")!=null) {
			vo = (MemberVO)session.getAttribute("vo");
			System.out.println(vo+"!!!!!!!!!!!");
			flag=TourDao.getInstance().checkLike(vo.getId(), reviewNum);
			flag2=TourDao.getInstance().checkScrap(vo.getId(), reviewNum);
		}
	
		
		request.setAttribute("likeFlag", flag);
		request.setAttribute("scrapFlag", flag2);
		ReviewVO rvo=TourDao.getInstance().checkReview(reviewNum);
		request.setAttribute("rvo", rvo);
		System.out.println(vo);
		request.setAttribute("vo", vo);	
		return new ModelAndView("checkReview.jsp");
	}

}
