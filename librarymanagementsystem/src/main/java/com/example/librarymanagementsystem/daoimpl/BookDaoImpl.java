package com.example.librarymanagementsystem.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.librarymanagementsystem.dao.BookDao;
import com.example.librarymanagementsystem.model.Books;
import com.example.librarymanagementsystem.repository.BooksRepo;

@Repository
public class BookDaoImpl implements BookDao{
	
	@Autowired
	BooksRepo bookRepo;
	
	@Override
	public void addBook(Books book) {
		bookRepo.save(book);
		
	}

	@Override
	public void updateBook(Books book) {
		bookRepo.save(book);
	}

	@Override
	public void deleteBook(int id) {
		bookRepo.deleteById(id);
	}

	@Override
	public List<Books> getAllBooks() {
		return bookRepo.findAll();
	}

	@Override
	public Books getBook(int id) {
		return bookRepo.findById(id).get();
	}

	@Override
	public List<Books> searchBook(@RequestBody Books book) {
		String name = book.getBookName();
		return bookRepo.findBybookName(name);
	}

	@Override
	public List<Books> searchBookByAuthor(String name) {
		return  bookRepo.findByauthorName(name);
	}

	@Override
	public List<Books> searchBookByIsbn(String name) {
		
		return bookRepo.findByIsbn(name);
	}

	@Override
	public List<Books> searchBookByGenre(String name) {
		
		return bookRepo.findByGenre(name);
	}

}
