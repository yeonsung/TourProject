package controller;

public class HandlerMapping {
	private static HandlerMapping mapping = new HandlerMapping();
	private HandlerMapping() {}
	public static HandlerMapping getInstance() {
		return mapping;
	}
	
	public Controller createController(String command) {
		switch(command) {
		case "":	//command
			return /* Controller  */null;
		}
		
		return null;
	}
}
