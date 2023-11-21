package com.example.librarymanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.librarymanagementsystem.model.Genres;
import com.example.librarymanagementsystem.model.Students;
import com.example.librarymanagementsystem.service.GenreService;
import com.example.librarymanagementsystem.service.StudentsService;

@Controller
//@RequestMapping("/ui")
public class uiController {
	
	@Autowired
	StudentsService studentsService;
	GenreService genreService;
	
	
	@GetMapping("/student")
    public String student() {
        return "Student/register";
    } 
	
	@GetMapping("/studentrecord")
    public String studentrecord() {
        return "Student/studentrecord";
	}
	
	@GetMapping("/book")
    public String book() {
        return "Book/addbook";
    } 
	
	@GetMapping("/bookrecord")
    public String bookrecord() {
        return "Book/bookrecord";
    } 
	
	@GetMapping("/generatedailyreport")
    public String generatedailyreport() {
        return "Transaction/generatedailyreport";
    } 
	
	
	@GetMapping("/issuebook")
    public String issuebook() {
        return "Transaction/issuebook";
    } 

	@GetMapping("/returnbook")
    public String returnbook() {
        return "Transaction/returnbook";
    } 
	
	
	
	@GetMapping("/penalty")
    public String penalty() {
        return "Transaction/penalty";
    } 
	 @GetMapping("/modal")
	    public String students() {
	        return "test";
	    } 
		
}
