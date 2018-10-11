package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.ReviewVO;

public class GetReviewController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean flag = true; // 글작성인지 수정인지 확인
		request.setAttribute("flag", flag);
		
		int reviewNum = Integer.parseInt(request.getParameter("reviewNum"));
		ReviewVO rvo = TourDao.getInstance().checkReview(reviewNum);
		request.setAttribute("rvo", rvo);
		
		ArrayList<String> tlist = TourDao.getInstance().getTags(reviewNum);
		request.setAttribute("tlist", tlist);
		
		ArrayList<String> ilist = TourDao.getInstance().getImages(reviewNum);
		request.setAttribute("ilist", ilist); /* ilist를 write.jsp의 캐러셀에 추가해주세여~! */
		
		return new ModelAndView("write.jsp");
	}
}
