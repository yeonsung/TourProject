package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TourDao;
import model.vo.AttractionVO;

public class GetDataController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tag = request.getParameter("search");
		System.out.println(tag);
		ArrayList<AttractionVO> alist = TourDao.getInstance().getData(tag);
		request.setAttribute("alist", alist);
		
		return new ModelAndView("searchResult.jsp");
	}

}
