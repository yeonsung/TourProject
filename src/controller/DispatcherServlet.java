package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/DispatcherServlet", "*.do" })
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURI());
		System.out.println(request.getContextPath());
		String command = request.getRequestURI().substring(request.getContextPath().length()+1);
		System.out.println(command+"11");
		
		Controller controller = HandlerMapping.getInstance().createController(command);
		String path = "error.jsp";
		ModelAndView mv = null;
		try {
			mv = controller.handleRequest(request, response);
			path = mv.getPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(mv==null) {}//error
		else if(mv.isRedirect()) response.sendRedirect(path);
		else request.getRequestDispatcher(path).forward(request, response);
	}

}
