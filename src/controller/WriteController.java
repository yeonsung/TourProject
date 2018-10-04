package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.ReviewVO;

public class WriteController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println("æ»≥Á«œººø‰");
		String id = request.getParameter("id");
		String location = request.getParameter("loaction");
		String city = request.getParameter("city");
		String title = request.getParameter("title");
		String[] categorys = request.getParameterValues("category");
		String content = request.getParameter("smarteditor");
		
		ReviewVO rvo = new ReviewVO(title, id, location, city, content);
		TourDao.getInstance().writeReview(rvo);
		
		ArrayList<String> tags = TourDao.getInstance().getTagsByContent(content);
		rvo.setTags(tags);
		
		
		System.out.println(content);
		
		
		
		request.setAttribute("rvo", rvo);
		ModelAndView mv = new ModelAndView();
		mv.setPath("result.jsp");
		return mv;
	}

}
