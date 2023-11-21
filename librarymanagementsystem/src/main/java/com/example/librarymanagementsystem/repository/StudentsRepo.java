package com.example.librarymanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.librarymanagementsystem.model.Books;
import com.example.librarymanagementsystem.model.Students;



public interface StudentsRepo extends JpaRepository<Students, Integer>{
	
	List<Students> findByname(String name);
	
	
	@Query(value = "SELECT * FROM students limit  :limit offset  :offset" ,nativeQuery = true)
	List<Students> getStudents(@Param("limit")int limit,@Param("offset")int offset);
	
	@Query(value = "SELECT count(*) FROM students",nativeQuery = true)
	int getCount();
	

	
	
	  @Query(value =
	  "SELECT * FROM students s WHERE s.name LIKE %?3%" +
	  " OR s.gender LIKE %?3%" + " OR CONCAT(s.age, '') LIKE %?3%   limit  ?1 offset  ?2 ", nativeQuery =
	  true) List<Students> searchStudents(int limit, int offset, String keyword);
	 
	  
	 @Query(value = "SELECT count(*) FROM students s WHERE s.name LIKE %?1%"
	            + " OR s.gender LIKE %?1%"
	            + " OR CONCAT(s.age, '') LIKE %?1%",nativeQuery = true)
	int  searchStudentsCount(String keyword);
}

