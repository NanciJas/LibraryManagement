package com.example.librarymanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.librarymanagementsystem.model.StudentCounter;


public interface StudentCounterRepo extends JpaRepository<StudentCounter, Integer>{
	
	

	@Query(value ="Select count from StudentCounter  where id = 1",nativeQuery = true)
	int getCount();

}
