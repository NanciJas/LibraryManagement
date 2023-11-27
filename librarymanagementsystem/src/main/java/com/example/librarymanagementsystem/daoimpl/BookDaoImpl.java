package com.example.librarymanagementsystem.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.librarymanagementsystem.dao.BookDao;
import com.example.librarymanagementsystem.model.Books;
import com.example.librarymanagementsystem.model.PaginateStudent;
import com.example.librarymanagementsystem.model.Students;
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

	@Override
	public PaginateStudent getBooks(PaginateStudent paginateStudent) {
		PaginateStudent pgst = new PaginateStudent();
		List<Books> books;
		int count;
		int offset = paginateStudent.getOffset();
		int pageNo = paginateStudent.getPageNo();
		int limit = paginateStudent.getPageSize();
		String keyword = paginateStudent.getKeyword();
		if(keyword == "") {
			books = bookRepo.getBooks(limit, offset);
			 count = bookRepo.getCount();
		}
		else {
			books =bookRepo.searchBooks(limit, offset,keyword);
			 count = bookRepo.searchBooksCount(keyword);
		}
		
		System.out.println("count  "+count);
		System.out.println("students  "+books);
		pgst.setCount(count);
		pgst.setBookList(books);
		pgst.setPageNo(pageNo);
		pgst.setOffset(offset);
		pgst.setPageSize(limit);
		pgst.setKeyword(keyword);
		return pgst;
		
	}

	
		

}
