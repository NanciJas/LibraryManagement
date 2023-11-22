package com.example.librarymanagementsystem.dao;

import java.util.List;

import com.example.librarymanagementsystem.model.Books;
import com.example.librarymanagementsystem.model.StudentCounter;
import com.example.librarymanagementsystem.model.Students;

public interface StudentCounterDao {

	public StudentCounter addStudentCounter(StudentCounter studentcounter);
	
	
	public List<StudentCounter> getStudentCounter();
	
	public String addStudentId();
}
