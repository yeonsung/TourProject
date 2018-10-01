package model.vo;

public class CommentVO {
	int review_num;
	String id;
	String content;
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public CommentVO(String id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	public CommentVO() {
		
	}
	
	
}
