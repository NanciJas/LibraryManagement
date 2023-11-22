package com.example.librarymanagementsystem.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.example.librarymanagementsystem.data.APIResponse;
import com.example.librarymanagementsystem.model.PaginateStudent;
import com.example.librarymanagementsystem.model.Students;

public interface StudentsDao {
	public void addStudent(Students students);
	public void updateStudent(Students students);
	public void deleteStudent(int id);
	public List<Students> getAllStudents();
	public Students getStudent(int id);
	
	public PaginateStudent getStudents(PaginateStudent paginateStudent);
	public List<Students> searchStudent(Students students);
	
	
	public Students addStudents(Students students);
	
	public Students addStudentInfo(Students students) throws SQLException;
	
	
}
