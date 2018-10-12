package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.ReviewVO;

public class UpdateReviewController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int reviewNum = Integer.parseInt(request.getParameter("reviewNum"));
		String id = request.getParameter("id");
		String location = request.getParameter("loaction");
		String city = request.getParameter("city");
		String title = request.getParameter("title");
		String[] categorys = request.getParameterValues("category");
		String content = request.getParameter("smarteditor");
		String date = request.getParameter("date");
		int count = Integer.parseInt(request.getParameter("count"));
		
		ReviewVO rvo = new ReviewVO(reviewNum,title, id, location, city, content, date);
		TourDao.getInstance().updateReview(rvo);

		ArrayList<String> tags = TourDao.getInstance().getTagsByContent(content);
		if(categorys!=null) {
			for(int i=0; i<categorys.length;i++) {
				tags.add(categorys[i]);
			}	
		}
		rvo.setTags(tags);
		TourDao.getInstance().writeTag(reviewNum, tags);
		
		ArrayList<String> imagepaths = new ArrayList<String>();
		for(int i=0 ; i<count;i++) {
			imagepaths.add(request.getParameter("img"+(i+1)));
		}
		rvo.setImages(imagepaths);
		TourDao.getInstance().writeReviewImage(reviewNum, imagepaths);
		
		return new ModelAndView("checkReview.do?num="+reviewNum);
	}

}
