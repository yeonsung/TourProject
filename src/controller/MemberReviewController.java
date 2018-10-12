package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ListVO;
import service.ReviewService;

public class MemberReviewController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String pageNo = request.getParameter("pageNo");		
		
		ListVO lvo = ReviewService.getInstance().getMyReview(id, pageNo);
		request.setAttribute("lvo", lvo);
		
		return new ModelAndView("memberReview.jsp?id="+id);
	}

}
