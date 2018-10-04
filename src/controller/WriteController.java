package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.ReviewVO;

public class WriteController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println("안녕하세요");
		String id = request.getParameter("id");
		String location = request.getParameter("loaction");
		String city = request.getParameter("city");
		String title = request.getParameter("title");
		String[] categorys = request.getParameterValues("category");
		String content = request.getParameter("smarteditor");
		
		System.out.println(content);
		
		ReviewVO rvo = new ReviewVO(title, id, location, city, content);
		
		TourDao.getInstance().writeReview(rvo);
		//getTagsByContent(String content)사용하자
		request.setAttribute("rvo", rvo);
		
		ModelAndView mv = new ModelAndView();
		mv.setPath("result.jsp");
		return mv;
	}

}
