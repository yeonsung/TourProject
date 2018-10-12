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
		
		ReviewVO rvo = new ReviewVO(title, id, location, city, content);
		TourDao.getInstance().writeReview(rvo);
		
		ArrayList<String> imagepaths = new ArrayList<String>();
		for(int i=0 ; i<count;i++) {
			imagepaths.add(request.getParameter("img"+(i+1)));
		}
		rvo.setImages(imagepaths);
		TourDao.getInstance().writeReviewImage(rvo.getReviewNum(), imagepaths);

		ArrayList<String> tags = TourDao.getInstance().getTagsByContent(content);
		if(categorys!=null) {
			for(int i=0; i<categorys.length;i++) {
				tags.add(categorys[i]);
			}	
		}
		tags.add(location);
		tags.add(city);
		rvo.setTags(tags);
		TourDao.getInstance().writeTag(rvo.getReviewNum(), tags);
		
		
		request.setAttribute("rvo", rvo);
		ModelAndView mv = new ModelAndView();
		mv.setPath("checkReview.do?num="+rvo.getReviewNum());
		return mv;
	}

}
