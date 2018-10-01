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
		case "login":	//command
			controller = new LoginController();
			return controller;
		case "register":
			controller = new RegisterController();
			return controller;
		}
		return null;
	}
}
