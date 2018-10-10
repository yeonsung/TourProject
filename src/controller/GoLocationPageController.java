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
//		System.out.println(location);

		ArrayList<FestivalVO> flist = TourDao.getInstance().getFestivalInfo(location);
		ArrayList<String> clist = TourDao.getInstance().getCities(location);
		ListVO relist = ReviewService.getInstance().getBestReviewByTag(location, "맛집",pageNo);
		
//		ListVO relist = ReviewService.getInstance().getBestReviewByTag(location, "留쏆쭛", pageNo);
		request.setAttribute("clist", clist);
		request.setAttribute("flist", flist);
		request.setAttribute("location", location);
//		request.setAttribute("relist", relist);
		System.out.println(flist.size());
		return new ModelAndView("v1.jsp");
	}
	/*public String getLocation(String pathConstant) {
		switch (pathConstant) {
		case "0":
			return "�젣二쇰룄";
		case "1":
			return "寃쎌긽�궓�룄";
		case "2":
			return "寃쎌긽遺곷룄";
		case "3":
			return "�쟾�씪�궓�룄";
		case "4":
			return "�쟾�씪遺곷룄";
		case "5":
			return "異⑹껌�궓�룄";
		case "6":
			return "異⑹껌遺곷룄";
		case "7":
			return "媛뺤썝�룄";
		case "8":
			return "寃쎄린�룄";
		case "9":
			return "�슱�궛";
		case "10":
			return "���쟾";
		case "11":
			return "愿묒＜";
		case "12":
			return "�씤泥�";
		case "13":
			return "��援�";
		case "14":
			return "遺��궛";
		case "15":
			return "�꽌�슱";
		}
		return null;
	}*/
}
