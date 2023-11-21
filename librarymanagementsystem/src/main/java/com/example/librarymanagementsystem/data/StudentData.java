package com.example.librarymanagementsystem.data;

import java.util.List;

import com.example.librarymanagementsystem.model.Students;

public class StudentData {
	 private List<Students> students;
	 private PaginationMeta pagination;
	 private int count;
	 
	 
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Students> getStudents() {
		return students;
	}
	public void setStudents(List<Students> students) {
		this.students = students;
	}
	public PaginationMeta getPagination() {
		return pagination;
	}
	public void setPagination(PaginationMeta pagination) {
		this.pagination = pagination;
	}
	 
	 
}
