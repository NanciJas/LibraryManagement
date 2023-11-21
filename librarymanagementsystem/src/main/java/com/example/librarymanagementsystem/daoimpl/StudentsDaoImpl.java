package com.example.librarymanagementsystem.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.librarymanagementsystem.dao.StudentsDao;
import com.example.librarymanagementsystem.data.APIResponse;
import com.example.librarymanagementsystem.data.PaginationMeta;
import com.example.librarymanagementsystem.data.StudentData;
import com.example.librarymanagementsystem.model.PaginateStudent;
import com.example.librarymanagementsystem.model.Students;
import com.example.librarymanagementsystem.repository.StudentsRepo;

@Repository
public class StudentsDaoImpl implements StudentsDao{
	@Autowired
	StudentsRepo studentsRepo;
	
	@Override
	public void addStudent(Students students) {
		studentsRepo.save(students);
	}

	@Override
	public void updateStudent(Students students) {
		studentsRepo.save(students);
		
	}

	@Override
	public void deleteStudent(int id) {
		studentsRepo.deleteById(id);
		
	}

	@Override
	public Students getStudent( int id) {
		try {
			Students s = studentsRepo.findById(id).get();
			return s;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return studentsRepo.findById(id).get();
	}



	@Override
	public List<Students> getAllStudents() {
		return studentsRepo.findAll();
	}
	
	

	

	
	@Override
	public PaginateStudent getStudents(PaginateStudent paginateStudent) {
		PaginateStudent pgst = new PaginateStudent();
		List<Students> students;
		int count;
		int offset = paginateStudent.getOffset();
		int pageNo = paginateStudent.getPageNo();
		int limit = paginateStudent.getPageSize();
		String keyword = paginateStudent.getKeyword();
		if(keyword == "") {
			students = studentsRepo.getStudents(limit, offset);
			 count = studentsRepo.getCount();
		}
		else {
			students =studentsRepo.searchStudents(limit, offset,keyword);
			 count = studentsRepo.searchStudentsCount(keyword);
		}
		
		System.out.println("count  "+count);
		System.out.println("students  "+students);
		pgst.setCount(count);
		pgst.setStudentList(students);
		pgst.setPageNo(pageNo);
		pgst.setOffset(offset);
		pgst.setPageSize(limit);
		pgst.setKeyword(keyword);
		return pgst;
		
		
	}

	@Override
	public List<Students> searchStudent(Students students) {
		
		String name = students.getName();
		return studentsRepo.findByname(name);
	}

	@Override
	public Students addStudents(Students students) {
		return studentsRepo.save(students);
		// TODO Auto-generated method stub
		
	}
	

}
