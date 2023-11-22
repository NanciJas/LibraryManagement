package com.example.librarymanagementsystem.service;

import java.util.List;

import com.example.librarymanagementsystem.model.StudentCounter;

public interface StudentCounterService {
	
	//public StudentCounter addStudentCounter(StudentCounter studentcounter);
	public List<StudentCounter> getStudentCounter();
	public String addStudentId();
}
