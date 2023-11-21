package com.example.librarymanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagementsystem.data.APIResponse;
import com.example.librarymanagementsystem.model.Books;
import com.example.librarymanagementsystem.model.PaginateStudent;
import com.example.librarymanagementsystem.model.Students;
import com.example.librarymanagementsystem.service.StudentCounterService;
import com.example.librarymanagementsystem.service.StudentsService;



@RestController
@RequestMapping("/students")
public class StudentsController {
	@Autowired
	StudentsService studentsService;
	StudentCounterService studentCounterService;
	
	
	
	 @PostMapping("/add") 
	    public ResponseEntity<Students> addStuent(@RequestBody Students Students) throws Exception{ 
		 Students student = this.studentsService.addStudents(Students);
	        return new ResponseEntity<Students>(student, HttpStatus.CREATED); 
	    } 
	
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public void addStuents(@RequestBody Students students) {
		studentsService.addStudent(students);
	}
	
	@GetMapping("/get/{id}")
	public Students getStudent(@PathVariable int id) {
		
		return studentsService.getStudent(id);
		
	}
	
	@GetMapping("/getAllStudents")
	public List<Students> getAllStudents() {
		return studentsService.getAllStudents();
		
	}
	

	@GetMapping("/searchStudent")
	public List<Students> searchStudent(@RequestBody Students students){
		return studentsService.searchStudent(students);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteStudent(@PathVariable int id) {
		studentsService.deleteStudent(id);
	}
	
	@PutMapping("/update")
	public void updateStudent(@RequestBody Students students) {
		studentsService.updateStudent(students);
	}
	
	
	@PostMapping("/getStudents")
	public PaginateStudent getStudents(@RequestBody PaginateStudent paginateStudent){
		return studentsService.getStudents(paginateStudent);	
	}
	
	
}
