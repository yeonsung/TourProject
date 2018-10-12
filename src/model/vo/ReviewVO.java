package model.vo;

import java.util.ArrayList;

public class ReviewVO {
	int reviewNum; 
	String title;
	String id;
	String location;
	String city;
	String content;
	String date;
	int like;
	String mainImage;
	ArrayList<String> tags;
	ArrayList<CommentVO> comments;
	ArrayList<String> images;
	
	public ReviewVO() {}

	public ReviewVO(String title, String id, String location, String city, String content) {
		this.title = title;
		this.id = id;
		this.location = location;
		this.city = city;
		this.content = content;
	}
	
	public ReviewVO(int reviewNum,String title, String id,String location, String city, String content, String date) {
		this.reviewNum = reviewNum;
		this.title = title;
		this.location = location;
		this.city = city;
		this.content = content;
		this.id = id;
		this.content = content;
		this.date = date;
	}


	// 占쏙옙占쏙옙占쏙옙占쏙옙占� 占쌩곤옙..
	public ReviewVO(String title, String location, String city, String content, ArrayList<String> tags,
			ArrayList<String> images) { 
		super();
		this.title = title;
		this.location = location;
		this.city = city;
		this.content = content;
		this.tags = tags;
		this.images = images;
	}
	
	public ReviewVO(int reviewNum, String location, String city, String title,String id){
		this.reviewNum = reviewNum;
		this.title = title;
		this.id = id;
		this.location = location;
		this.city = city;
	}
	public ReviewVO(int reviewNum, String title, String id, String date) {
		super();
		this.reviewNum = reviewNum;
		this.title = title;
		this.id = id;
		this.date = date;
	}
	public ReviewVO(int reviewNum, String title, String date) {
		super();
		this.reviewNum = reviewNum;
		this.title = title;
		this.date = date;
	}
	// 占쌩곤옙....
	public ReviewVO(int reviewNum, String title, String id, String location, String city, String content, String date,
			int like) {
		super();
		this.reviewNum = reviewNum;
		this.title = title;
		this.id = id;
		this.location = location;
		this.city = city;
		this.content = content;
		this.date = date;
		this.like = like;
	}

	

	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	public int getReviewNum() {
		return reviewNum;
	}
	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	public ArrayList<CommentVO> getComments() {
		return comments;
	}

	public void setComments(ArrayList<CommentVO> comments) {
		this.comments = comments;
	}
	public ArrayList<String> getImages() {
		return images;
	}

	public void setImages(ArrayList<String> images) {
		this.images = images;
	}

	public ReviewVO(int reviewNum, String title, String id, String location, String city, String content, String date,
			int like, ArrayList<String> tags, ArrayList<CommentVO> comments, ArrayList<String> images) {
		super();
		this.reviewNum = reviewNum;
		this.title = title;
		this.id = id;
		this.location = location;
		this.city = city;
		this.content = content;
		this.date = date;
		this.like = like;
		this.tags = tags;
		this.comments = comments;
		this.images = images;
	}
	@Override
	public String toString() {
		return "ReviewVO [reviewNum=" + reviewNum + ", title=" + title + ", id=" + id + ", location=" + location
				+ ", city=" + city + ", content=" + content + ", date=" + date + ", like=" + like + ", tags=" + tags
				+ ", comments=" + comments + ", images=" + images + "]";
	}
	
	
	
}
