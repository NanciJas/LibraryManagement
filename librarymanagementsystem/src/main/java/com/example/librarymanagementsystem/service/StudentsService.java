package com.example.librarymanagementsystem.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.librarymanagementsystem.dao.StudentsDao;
import com.example.librarymanagementsystem.data.APIResponse;
import com.example.librarymanagementsystem.model.Books;
import com.example.librarymanagementsystem.model.PaginateStudent;
import com.example.librarymanagementsystem.model.Students;


public interface StudentsService {
	
	public void addStudent(Students students);
	public void updateStudent(Students students);
	public void deleteStudent(int id);
	public List<Students> getAllStudents();
	public Students getStudent(int id);
	public List<Students> searchStudent(Students students);
	
	public PaginateStudent getStudents(PaginateStudent paginateStudent);
	
	
	public Students addStudents(Students students);
	
	
}
