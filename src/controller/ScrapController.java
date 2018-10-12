package controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ListVO;
import service.ReviewService;

public class ScrapController implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNo = request.getParameter("pageNo");		
		String id = request.getParameter("id");
		ListVO lvo = ReviewService.getInstance().getScrapList(id, pageNo);
		request.setAttribute("lvo", lvo);
		
		return new ModelAndView("scrap.jsp");
	}
}
