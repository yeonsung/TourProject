package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TourDao;
import model.vo.MemberVO;

public class scrapCheckedController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String flag = request.getParameter("flag"); //up or down
		String reviewNum = request.getParameter("reviewNum");
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("vo");
		if(session.getAttribute("vo")!=null) {
		if(flag.equals("up")) {
			TourDao.getInstance().addScrap(vo.getId(), Integer.parseInt(reviewNum));
		}else if(flag.equals("down")){
			TourDao.getInstance().delScrap(vo.getId(), Integer.parseInt(reviewNum));
		}
		}
		request.setAttribute("reviewNum", reviewNum);
		System.out.println(flag+"   "+"   "+reviewNum+"!!!!!!!!!!");
		return new ModelAndView("index.jsp");
	}

}
