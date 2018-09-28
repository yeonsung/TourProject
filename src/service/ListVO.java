package service;

import java.util.ArrayList;

import model.PagingBean;
import model.vo.ReviewVO;

/*
 * 특정한 페이지의 게시물들을 저장하는 VO
 * 특정한 페이지 정보와 책 리스트 정보가 동시에 저장
 */
public class ListVO {
	private ArrayList<ReviewVO> list;
	private PagingBean pb;

	public ListVO(ArrayList<ReviewVO> list, PagingBean pb) {
		this.list = list;
		this.pb = pb;
	}
	public ListVO() {}
	public ArrayList<ReviewVO> getList() {
		return list;
	}
	public void setList(ArrayList<ReviewVO> list) {
		this.list = list;
	}
	public PagingBean getPb() {
		return pb;
	}
	public void setPb(PagingBean pb) {
		this.pb = pb;
	}
	@Override
	public String toString() {
		return "ListVO [list=" + list + ", pb=" + pb + "]";
	}

}
