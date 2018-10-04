package model.vo;

public class CommentVO {
	int reviewNum;
	String id;
	String comment;
	
	
	public CommentVO() {
		super();
	}
	public CommentVO(String id, String comment) {
		super();
		this.id = id;
		this.comment = comment;
	}
	public int getReviewNum() {
		return reviewNum;
	}
	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "CommentVO [reviewNum=" + reviewNum + ", id=" + id + ", comment=" + comment + "]";
	}
	
	
}
