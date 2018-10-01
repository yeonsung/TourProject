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
		case "myreviews.do":	
			controller = new MyReviewController();
			break;
		case "scrap.do":	
			controller = new ScrapController();
			break;
			
		case "delete.do":	
			controller = new DeleteController();
			break;
		}
		
		return controller;
	}
}
