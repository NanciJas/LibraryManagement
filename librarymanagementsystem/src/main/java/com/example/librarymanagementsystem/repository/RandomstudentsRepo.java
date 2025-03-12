package com.example.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.librarymanagementsystem.model.Randomstudents;
import com.example.librarymanagementsystem.model.Students;

public interface RandomstudentsRepo  extends JpaRepository<Randomstudents, Integer>{
	

}
