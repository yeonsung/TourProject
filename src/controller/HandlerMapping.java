package controller;

public class HandlerMapping {
	private static HandlerMapping mapping = new HandlerMapping();

	private HandlerMapping() {
	}

	public static HandlerMapping getInstance() {
		return mapping;
	}

	public Controller createController(String command) {
		Controller controller = null;

		switch (command) {

		case "locationpage.do": // command
			return new GoLocationPageController();
		case "myreviews.do":
			return new MyReviewController();
		case "scrap.do":
			return new ScrapController();
		case "delete.do":
			return new DeleteController();
		case "getBestReviewBytag.do":
			return new GetBestReviewBytagController();
		case "login": // command
			return new LoginController();
		case "register":
			return new RegisterController();
		}
		return null;
	}
}
