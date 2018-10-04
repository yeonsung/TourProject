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
		case "login":	//command
			return new LoginController();
		case "register":
			return new RegisterController();
		case "write.do":	//command
			System.out.println("하하호호124124");
			return new WriteController();
		}
		
		return null;
	}
}
