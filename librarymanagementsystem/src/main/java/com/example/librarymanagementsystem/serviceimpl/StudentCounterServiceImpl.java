package com.example.librarymanagementsystem.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.librarymanagementsystem.dao.StudentCounterDao;
import com.example.librarymanagementsystem.model.StudentCounter;
import com.example.librarymanagementsystem.model.Students;
import com.example.librarymanagementsystem.service.StudentCounterService;
import com.example.librarymanagementsystem.service.StudentsService;


@Service
public class StudentCounterServiceImpl implements StudentCounterService {

	@Autowired
	StudentCounterDao studentCounterDao;
	
	StudentsService studentservice;
	
	

}
