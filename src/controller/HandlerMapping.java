package controller;

public class HandlerMapping {
	private static HandlerMapping mapping = new HandlerMapping();
	private HandlerMapping() {}
	public static HandlerMapping getInstance() {
		return mapping;
	}
	
	public Controller createController(String command) {
		Controller controller = null;
		
		switch(command) {
		case "myreview":	
			controller = new MyReviewController();
			break;
		case "scrap":	
			controller = new ScrapController();
			break;
		}
		
		return controller;
	}
}
