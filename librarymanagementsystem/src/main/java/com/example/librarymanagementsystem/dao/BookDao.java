package com.example.librarymanagementsystem.dao;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.librarymanagementsystem.model.Books;
import com.example.librarymanagementsystem.model.Students;

public interface BookDao {
	public void addBook(Books book);
	public void updateBook(Books book);
	public void deleteBook(int id);
	public List<Books> getAllBooks();
	public Books getBook(int id);
	
	
	public List<Books> searchBook(@RequestBody Books book);
	public List<Books> searchBookByAuthor(String name);
	public List<Books> searchBookByIsbn(String name);
	public List<Books> searchBookByGenre(String name);
	//public List<Books> searchBookByGenre();

}
