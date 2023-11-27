package com.example.librarymanagementsystem.model;

import java.nio.file.Files;
import java.util.List;

public class PaginateStudent {
	List<Students> studentList;
	List<Books> bookList;
	
	int count;
	int pageNo;
	int offset;
	int pageSize;
	String keyword;
	
	
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<Students> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Students> studentList) {
		this.studentList = studentList;
	}
	
	
	
	
	public List<Books> getBookList() {
		return bookList;
	}
	public void setBookList(List<Books> bookList) {
		this.bookList = bookList;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
	
}
