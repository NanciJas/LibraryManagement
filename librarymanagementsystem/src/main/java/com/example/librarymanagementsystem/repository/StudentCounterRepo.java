package com.example.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.librarymanagementsystem.model.StudentCounter;
import com.example.librarymanagementsystem.model.Students;

public interface StudentCounterRepo extends JpaRepository<StudentCounter, Integer>{

}
