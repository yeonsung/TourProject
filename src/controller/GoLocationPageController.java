package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.FestivalVO;
import model.vo.ReviewVO;

public class GoLocationPageController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String pathConstant = request.getParameter("location").substring(request.getParameter("location").indexOf("-")+1);
		System.out.println(pathConstant);
		String location = getLocation(pathConstant);
		System.out.println(location);
		ArrayList<FestivalVO> flist = TourDao.getInstance().getFestivalInfo(location);
		ArrayList<String> clist = TourDao.getInstance().getCities(location);
		ArrayList<ReviewVO> relist = TourDao.getInstance().getBestReviewByTag(location, "맛집");
		
		request.setAttribute("clist", clist);
		request.setAttribute("flist", flist);
		request.setAttribute("location", location);
		request.setAttribute("relist", relist);

		return new ModelAndView("v1.jsp?location="+location);
	}
	
	public String getLocation(String pathConstant) {
		switch(pathConstant) {
		case "0": return "제주도";
		case "1": return "경상남도";
		case "2": return "경상북도";
		case "3": return "전라남도";
		case "4": return "전라북도";
		case "5": return "충청남도";
		case "6": return "충청북도";
		case "7": return "강원도";
		case "8": return "경기도";
		case "9": return "울산";
		case "10": return "대전";
		case "11": return "광주";
		case "12": return "인천";
		case "13": return "대구";
		case "14": return "부산";
		case "15": return "서울";
		}
		return null;
	}
}
