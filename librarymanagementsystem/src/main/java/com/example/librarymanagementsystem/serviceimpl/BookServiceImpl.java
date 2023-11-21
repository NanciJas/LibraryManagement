
package com.example.librarymanagementsystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarymanagementsystem.dao.BookDao;
import com.example.librarymanagementsystem.model.Books;
import com.example.librarymanagementsystem.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookDao bookDao;
	
	@Override
	public void addBook(Books book) {
		bookDao.addBook(book);
		
	}

	@Override
	public void updateBook(Books book) {
		bookDao.updateBook(book);
		
	}

	@Override
	public void deleteBook(int id) {
		bookDao.deleteBook(id);
	}

	@Override
	public List<Books> getAllBooks() {
		return bookDao.getAllBooks();
	}

	@Override
	public Books getBook(int id) {
		return bookDao.getBook(id);
	}

	@Override
	public List<Books> searchBook(Books book) {
		
		return bookDao.searchBook(book);
	}

	@Override
	public List<Books> searchBookByAuthor(String name) {
		return bookDao.searchBookByAuthor(name);
	}

	@Override
	public List<Books> searchBookByIsbn(String name) {
		
		return bookDao.searchBookByIsbn(name);
	}

	@Override
	public List<Books> searchBookByGenre(String name) {
		
		return bookDao.searchBookByGenre(name);
	}

	
}
