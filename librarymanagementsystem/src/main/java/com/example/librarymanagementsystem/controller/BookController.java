package com.example.librarymanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagementsystem.model.Books;
import com.example.librarymanagementsystem.model.Students;
import com.example.librarymanagementsystem.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@PostMapping("/create")
	public void addBook(@RequestBody Books book) {
		bookService.addBook(book);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteBook(@PathVariable int id) {
		bookService.deleteBook(id);
	}
	
	@GetMapping("/get/{id}")
	public Books getBook(@PathVariable int id) {
		return bookService.getBook(id);
		
	}
	
	@GetMapping("/getAllBooks")
	public List<Books> getAllBooks(){
		return bookService.getAllBooks();
	}
	
	@PutMapping("/update")
	public void updateBook(@RequestBody Books book) {
		bookService.updateBook(book);
	}
	
	@GetMapping("/searchBook")
	public List<Books> searchBook(@RequestBody Books book){
		return bookService.searchBook(book);
	}
	
	@GetMapping("/searchBookByAuthor/{authorName}")
	public List<Books> searchBookByAuthor(@PathVariable(value = "authorName") String name){
		return bookService.searchBookByAuthor(name);
	}
	
	@GetMapping("/searchBookByIsbn/{isbn}")
	public List<Books> searchBookByIsbn(@PathVariable(value = "isbn") String name){
		return bookService.searchBookByIsbn(name);
	}
	@GetMapping("/searchBookByGenre/{genre}")
	public List<Books> searchBookByGenre(@PathVariable(value ="genre") String name){
		
		return bookService.searchBookByGenre(name);
	}

}
