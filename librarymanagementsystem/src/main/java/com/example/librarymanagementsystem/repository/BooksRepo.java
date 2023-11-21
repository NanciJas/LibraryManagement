package com.example.librarymanagementsystem.repository;

import java.io.Console;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.librarymanagementsystem.model.Books;

public interface BooksRepo extends JpaRepository<Books,Integer>{

	List<Books> findBybookName(String name);
	List<Books> findByauthorName(String name);
	List<Books> findByIsbn(String isbn);
	
	
	@Query(value ="Select b.id,b.author_name ,b.book_name,b.isbn,b.genre_id,g.genre_name from Books b Left join Genre g ON b.genre_id = g.id where g.genre_name = ?",nativeQuery = true)
	List<Books> findByGenre(String name);
}