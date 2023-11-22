package com.example.librarymanagementsystem.daoimpl;

import java.util.List;

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

	@Override
	public List<StudentCounter> getStudentCounter() {
		
		return studentcounterrepo.findAll();
	}

	@Override
	public String addStudentId() {
		int count = studentcounterrepo.getCount();
		count = count+1;
		String text = String.format("%05d", count);
		//String id = Integer.toString(count);
		
		String studentId = "ST_"+text;
		return studentId;
		
		
	}

}
