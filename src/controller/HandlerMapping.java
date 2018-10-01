package controller;

public class HandlerMapping {
	private static HandlerMapping mapping = new HandlerMapping();
	private HandlerMapping() {}
	public static HandlerMapping getInstance() {
		return mapping;
	}
	
	public Controller createController(String command) {
		switch(command) {
		case "locationpage.do":	//command
			return new GoLocationPageController();
		}
		
		return null;
	}
	
	
}
