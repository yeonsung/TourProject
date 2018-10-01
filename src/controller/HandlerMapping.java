package controller;

public class HandlerMapping {
	private static HandlerMapping mapping = new HandlerMapping();
	private HandlerMapping() {}
	public static HandlerMapping getInstance() {
		return mapping;
	}
	
	public Controller createController(String command) {
		Controller controller = null;
		System.out.println(command);
		switch(command) {

		case "locationpage.do":	//command
			return new GoLocationPageController();
		case "myreviews.do":	
			return new MyReviewController();
		case "scrap.do":	
			return new ScrapController();
		case "delete.do":	
			return new DeleteController();
		}
		
		return controller;
	}
	
	
}
