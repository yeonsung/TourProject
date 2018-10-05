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
		String id = request.getParameter("id");
		String location = request.getParameter("loaction");
		String city = request.getParameter("city");
		String title = request.getParameter("title");
		String[] categorys = request.getParameterValues("category");
		String content = request.getParameter("smarteditor");
		int count = Integer.parseInt(request.getParameter("count"));
		String[] imagepaths = new String[count];
		
		for(int i=0 ; i<count;i++) {
			System.out.println(request.getParameter("img"+(i+1)));
			imagepaths[i] = request.getParameter("img"+(i+1));
		}
		
		for(int i=0; i<count; i++) {
			System.out.println("포문안입니다"+imagepaths[i]);
		}

		

		ReviewVO rvo = new ReviewVO(title, id, location, city, content);
		TourDao.getInstance().writeReview(rvo);
		

		
		/*ArrayList<String> tags = TourDao.getInstance().getTagsByContent(content);
		rvo.setTags(tags);
		System.out.println(content);
		System.out.println("나는 태그입니다"+tags);*/ //태그쪽 다시 한번
		
		
		
		
		
		
		request.setAttribute("rvo", rvo);
		ModelAndView mv = new ModelAndView();
		mv.setPath("result.jsp");
		return mv;
	}

}
