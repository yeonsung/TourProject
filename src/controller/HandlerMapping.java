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

		switch(command) {
		case "locationpage.do":	//command
			return new GoLocationPageController();
		case "myreviews.do":
			return new MyReviewController();
		case "scrap.do":
			return new ScrapController();
		case "delete.do":
			return new DeleteController();
		case "deleteScrap.do":	
			return new DeleteScrapController();
		case "getBestReviewBytag.do":
			return new GetBestReviewBytagController();
		case "login.do":	//command
			return new LoginController();
		case "register.do":
			return new RegisterController();
		case "write.do":	//command
			return new WriteController();
		case "idcheck.do":
			System.out.println("¸¸µé¾îÁü");
			return new IdcheckController();
		case "registerupdate.do":
			return new RegisterUpdateController();
		case "logout.do":
			return new LogoutController();
		case "findidpass.do":
			return new FindIdPassController();
		case "getdata.do":
			return new GetDataController();
		case "relatedreview.do":
			return new RelatedReviewController();
		case "getAttraction.do":	//command
			return new GetAttractionController();
		case "getRecentReviews.do":
			return new GetRecentReviewsController();
		case "checkReview.do":	//command
			return new CheckReviewController();
		case "updatereview.do":
			return new UpdateReviewController();
		case "getreview.do":
			return new GetReviewController();
		case "memberreview.do":
			return new MemberReviewController();
		case "deleteiamge.do":
			return new DeleteIamgeController();
		case "like.do":
			return new likeCheckedController();
		case "scrapCheck.do":
			return new scrapCheckedController();
		case "getBestReviewCityBytag.do":
			return new GetBestReviewCityBytagController();
		}
		return null;
	}
}
