package com.example.librarymanagementsystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.librarymanagementsystem.dao.StudentsDao;
import com.example.librarymanagementsystem.data.APIResponse;
import com.example.librarymanagementsystem.model.Books;
import com.example.librarymanagementsystem.model.PaginateStudent;
import com.example.librarymanagementsystem.model.StudentCounter;
import com.example.librarymanagementsystem.model.Students;
import com.example.librarymanagementsystem.service.StudentsService;
@Service
public class StudentsServiceImpl implements StudentsService {
	
	@Autowired
	StudentsDao studentsDao;

	@Override
	public void addStudent(Students students) {
		studentsDao.addStudent(students);
		
	}

	@Override
	public void updateStudent(Students students) {
		studentsDao.updateStudent(students);
		
	}

	@Override
	public void deleteStudent( int id) {
		studentsDao.deleteStudent(id);
		
	}

	@Override
	public List<Students> getAllStudents() {
		
		return studentsDao.getAllStudents();
	}

	@Override
	public Students getStudent(int id) {
		
		return studentsDao.getStudent(id);
	}
	

	/*
	 * @Override public APIResponse getStudentsPaginated(Pageable pageable) {
	 * APIResponse apiResponse = studentsDao.getStudentsPaginated(pageable);
	 * 
	 * return apiResponse; }
	 * 
	 * @Override public Page<Students> findByPagination(int pageNo, int size) {
	 * 
	 * return studentsDao.findByPagination(pageNo, size); }
	 */
	 
	 
	/*
	 * @Override public List<Students> getPaginatedStudents(int pageNo, int
	 * pageSize) {
	 * 
	 * 
	 * 
	 * return studentsDao.getPaginatedStudents(pageNo, pageSize); }
	 * 
	 * public PaginateStudent getAllStudent() { return studentsDao.getAllStudent();
	 * 
	 * }
	 */

//	@Override
//	public PaginateStudent getStudents(int limit, int pageNo) {
//		int offset = (pageNo - 1) * limit;
//		
//		return studentsDao.getStudents(limit, offset);
//	}
	
	
	/*
	 * @Override public PaginateStudent getStudents(int limit, int pageNo, String
	 * keyword) { int offset = (pageNo - 1) * limit;
	 * 
	 * return studentsDao.getStudents(limit,offset,keyword); }
	 */

	@Override
	public PaginateStudent getStudents(PaginateStudent paginateStudent) {
		int pageNo = paginateStudent.getPageNo();
		int limit = paginateStudent.getPageSize();
		String keyword = paginateStudent.getKeyword();
		 int offset = (pageNo - 1) * limit;
		 paginateStudent.setOffset(offset);
		
		return studentsDao.getStudents(paginateStudent);
	}

	@Override
	public List<Students> searchStudent(Students students) {
		
		return studentsDao.searchStudent(students);
	}

	@Override
	public Students addStudents(Students students) {
		Students s = studentsDao.addStudents(students);
		
		
		return s;
	}
	 
}
