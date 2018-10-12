package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TourDao;
import model.vo.AttractionVO;

public class GetDataController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNo = request.getParameter("pageNo");	
		if(pageNo == null) pageNo="1";
		String tag = request.getParameter("search");
		ArrayList<AttractionVO> alist = null;
		boolean emptyFlag = false;
		boolean flag = false;

		String path = "relatedreview.do";
		
		alist = TourDao.getInstance().getData(tag);
		System.out.println(alist+"이건 ㅂ어라");
		if(!alist.isEmpty()) 
			emptyFlag = true;
		
		else {
			/*boolean tagExist = TourDao.getInstance().tagExist(tag);
			if(tagExist) {
				String check = TourDao.getInstance().checkTag(tag);
				if(check.equals("location"))
					path = "index.jsp"; // 나중에 location(v1) 페이지로 이동
					
				else if(check.equals("city")) 
					path = "index.jsp"; // 나중에 city(v2) 페이지로 이동
			}
			else {
				alist = TourDao.getInstance().checkSpot(tag);
				flag = true;
			}*/
			
			String check = TourDao.getInstance().checkTag(tag);
			System.out.println(check+"check 확인");
			if(check.equals("location")) {				//검색어가 location 이름이면
				path = "locationpage.do?location=path-"+tag; // 나중에 location(v1) 페이지로 이동
				return new ModelAndView(path);
			}
				
			else if(check.equals("city")) {				//검색어가 city 이름이면
				ArrayList<String> locationList = TourDao.getInstance().checkLocationByCity(tag);
				if(locationList.size()!=1) {
					request.setAttribute("locationList", locationList);
					request.setAttribute("city", tag);
					path = "searchLocationByCity.jsp"; // 나중에 city(v2) 페이지로 이동
					return new ModelAndView(path);
				} else {
					path = "getAttraction.do?city="+tag+"&&location="+locationList.get(0);
					return new ModelAndView(path);
				}
				
			}
			
			else {										//검색어가 둘다 아니면
				boolean tagExist = TourDao.getInstance().tagExist(tag);
				System.out.println("tagExist : "+tagExist);
				if(!tagExist) {							//검색어가 리뷰tag에 없어
					alist = TourDao.getInstance().checkSpot(tag);
					flag = true;
					System.out.println(alist+" tagExist 안에 alist");
					if(alist.isEmpty()) {				//검색어가 관광지에도 없어
						if(tag.indexOf(" ") == -1) {	//띄어쓰기 없어
							path ="noResult.jsp"; 
							System.out.println("히히히히히히");
						}
						
						else {							//띄어쓰기 있어
							String[] arr = tag.trim().split(" ");
							String strL = arr[arr.length-2];			//그 앞에꺼
							String strC = arr[arr.length-1];			//제일뒤에꺼
							String checkC = TourDao.getInstance().checkTag(strC);
							boolean cflag = false;
							System.out.println(checkC+"   checkC를 확인해보자    strL : "+strL+"     strC"+strC);
							
							if(checkC.equals("city")) {
								ArrayList<String> locationList = TourDao.getInstance().checkLocationByCity(strC);
								if(locationList.size()!=1) {
									for(int i = 0; i < locationList.size(); i++) {
										if(locationList.get(i) == strL) {
											path = "getAttraction.do?location="+strL+"&&city="+strC;
											cflag = true;
										}
									}
									if(!cflag)
										path ="noResult.jsp";
										
								}
								path = "getAttraction.do?location="+strL+"&&city="+strC;
								
								return new ModelAndView(path);
								
							}//if
							else {
								alist = TourDao.getInstance().checkSpot(strC);
							}
						}//else
					}//if alist.empty
					
					else {								//검색어가 관광지에 있어
						System.out.println(alist+"!@#");
					}//else !alist.empty
				}//tagExist
			}
		}
		
		request.setAttribute("alist", alist);
		request.setAttribute("emptyFlag", emptyFlag);
		
		return new ModelAndView(path + "?pageNo="+pageNo+"&&tag="+tag+"&&flag="+flag);
	}
}

