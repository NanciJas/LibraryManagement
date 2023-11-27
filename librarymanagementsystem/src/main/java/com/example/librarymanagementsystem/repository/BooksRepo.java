package com.example.librarymanagementsystem.repository;

import java.io.Console;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.librarymanagementsystem.model.Books;
import com.example.librarymanagementsystem.model.Students;

public interface BooksRepo extends JpaRepository<Books,Integer>{

	List<Books> findBybookName(String name);
	List<Books> findByauthorName(String name);
	List<Books> findByIsbn(String isbn);
	
	
	@Query(value ="Select b.id,b.author_name ,b.book_name,b.isbn,b.genre_id,g.genre_name from Books b Left join Genre g ON b.genre_id = g.id where g.genre_name = ?",nativeQuery = true)
	List<Books> findByGenre(String name);
	
	
	
	
	@Query(value = "SELECT * FROM Books limit  :limit offset  :offset" ,nativeQuery = true)
	List<Books> getBooks(@Param("limit")int limit,@Param("offset")int offset);
	
	@Query(value = "SELECT count(*) FROM Books",nativeQuery = true)
	int getCount();
	
	
	
	 @Query(value = 
			  "Select b.id,b.author_name ,b.book_name,b.isbn,b.genre_id,g.genre_name from Books b Left join Genre g ON b.genre_id = g.id  WHERE b.book_name LIKE %?3%" + " OR b.ISBN LIKE %?3%" +
			  " OR b.author_name LIKE %?3%" + " OR g.genre_name LIKE %?3%   limit  ?1 offset  ?2 ", nativeQuery =
			  true) List<Books> searchBooks(int limit, int offset, String keyword);

//	
//	  @Query(value =
//	  "SELECT * FROM Books s WHERE s.book_name LIKE %?3%" + " OR s.ISBN LIKE %?3%" +
//	  " OR s.author_name LIKE %?3%" + " OR s.genre_id LIKE %?3%   limit  ?1 offset  ?2 ", nativeQuery =true)
//	  List<Books> searchBooks(int limit, int offset, String keyword);
//	 
	  
	 @Query(value = "SELECT count(*) FROM    Books s Left join Genre g ON s.genre_id = g.id  WHERE s.book_name LIKE %?1%"
	            + " OR s.author_name LIKE %?1%"+ " OR s.isbn LIKE %?1%"
	            + " OR  g.genre_name LIKE %?1%",nativeQuery = true)
	int  searchBooksCount(String keyword);
}