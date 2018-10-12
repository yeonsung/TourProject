package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.FestivalVO;
import model.vo.ReviewVO;
import service.ListVO;
import service.ReviewService;

public class GoLocationPageController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String pageNo = request.getParameter("pageNo");
		String location = request.getParameter("location")
				.substring(request.getParameter("location").indexOf("-") + 1);

		ArrayList<FestivalVO> flist = TourDao.getInstance().getFestivalInfo(location);
		ArrayList<String> clist = TourDao.getInstance().getCities(location);
		ListVO relist = ReviewService.getInstance().getBestReviewByTag(location, "맛집",pageNo);
		
		request.setAttribute("clist", clist);
		request.setAttribute("flist", flist);
		request.setAttribute("location", location);
//		request.setAttribute("relist", relist);
		System.out.println(flist.size());
		return new ModelAndView("v1.jsp");
	}
}

