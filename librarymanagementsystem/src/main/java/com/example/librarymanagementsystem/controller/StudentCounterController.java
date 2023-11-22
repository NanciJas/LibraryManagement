package com.example.librarymanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagementsystem.model.StudentCounter;
import com.example.librarymanagementsystem.service.StudentCounterService;

@RestController
@RequestMapping("/studentsCounter")
public class StudentCounterController {

	@Autowired
	StudentCounterService studentCounterService;
	
	@GetMapping("/getAllStudentCounter")
	public List<StudentCounter> getAllStudents() {
		return studentCounterService.getStudentCounter();
		
	}
	 
	 @PostMapping("/getStudentId")
		public String getStudentId(){
			return studentCounterService.addStudentId();
		}
}
