package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ListVO;
import service.ReviewService;

public class RelatedReviewController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String flag = request.getParameter("flag");
		String pageNo = request.getParameter("pageNo");
		if(pageNo == null) pageNo="1";
		String tag = request.getParameter("tag");
		ListVO lvo = new ListVO();
		
		if(flag.equals("true")) {
			lvo = ReviewService.getInstance().getReviewBySearch(tag, pageNo);
			System.out.println(lvo);
		}
		else if(flag.equals("false")) {
			lvo = ReviewService.getInstance().relatedReviews(tag, pageNo);
		}
		
		request.setAttribute("lvo", lvo);
		request.setAttribute("flag", flag);
		
		return new ModelAndView("searchResult.jsp");
	}

}

