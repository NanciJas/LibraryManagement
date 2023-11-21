package com.example.librarymanagementsystem.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.librarymanagementsystem.dao.StudentCounterDao;
import com.example.librarymanagementsystem.model.StudentCounter;
import com.example.librarymanagementsystem.repository.StudentCounterRepo;


@Repository
public class StudentCounterDaoImpl  implements StudentCounterDao{

	@Autowired
	StudentCounterRepo studentcounterrepo;
	
	@Override
	public StudentCounter addStudentCounter(StudentCounter studentcounter) {
		return studentcounterrepo.save(studentcounter);
		
	}

}
