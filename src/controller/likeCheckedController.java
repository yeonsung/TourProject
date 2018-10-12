package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TourDao;
import model.vo.MemberVO;

public class likeCheckedController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String flag = request.getParameter("flag"); //up or down
		String reviewNum = request.getParameter("reviewNum");
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("vo");
		if(session.getAttribute("vo")!=null) {
		if(flag.equals("up")) {
			TourDao.getInstance().addLike(Integer.parseInt(reviewNum));
			TourDao.getInstance().insertLike(vo.getId(), Integer.parseInt(reviewNum));
		}else if(flag.equals("down")){
			TourDao.getInstance().downLike(Integer.parseInt(reviewNum));
			TourDao.getInstance().deleteLike(vo.getId(), Integer.parseInt(reviewNum));
		}
		}
		request.setAttribute("reviewNum", reviewNum);
		System.out.println(flag+"   "+"   "+reviewNum+"!!!!!!!!!!");
		return new ModelAndView("index.jsp");
	}

}
