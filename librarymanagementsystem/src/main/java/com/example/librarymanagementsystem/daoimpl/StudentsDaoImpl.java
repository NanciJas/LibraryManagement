package com.example.librarymanagementsystem.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.librarymanagementsystem.dao.StudentsDao;
import com.example.librarymanagementsystem.data.APIResponse;
import com.example.librarymanagementsystem.data.PaginationMeta;
import com.example.librarymanagementsystem.data.StudentData;
import com.example.librarymanagementsystem.model.PaginateStudent;
import com.example.librarymanagementsystem.model.StudentCounter;
import com.example.librarymanagementsystem.model.Students;
import com.example.librarymanagementsystem.repository.StudentCounterRepo;
import com.example.librarymanagementsystem.repository.StudentsRepo;

@Repository
public class StudentsDaoImpl implements StudentsDao{
	@Autowired
	StudentsRepo studentsRepo;
	
	@Autowired
	StudentCounterRepo studentCounterRepo;
	
	@PersistenceContext
	private EntityManager em;
	
	public Connection getConnection() throws SQLException {
		EntityManagerFactoryInfo info = (EntityManagerFactoryInfo) em.getEntityManagerFactory();
	    Connection connection = info.getDataSource().getConnection();
	    
	    return connection;
	}
	
	@Transactional
	public Students addStudentInfo(Students students) throws SQLException {
		Connection connection = getConnection();
		connection.setAutoCommit(false);
		
		try {
			
			
			String s_id = students.getStudentId();
			s_id = s_id.substring(3);
			System.out.println("Students s_id :  "+s_id);
			
			//Count from User
			int count = Integer.parseInt(s_id);
			System.out.println("Students count from user :  "+count);
			
			
			//Count from CounterTable
			int countInCounterTable = studentCounterRepo.getCount();
			
			System.out.println("Students countInCounterTable :  "+countInCounterTable+1);
			
			if(count < countInCounterTable+1) {
				String text = String.format("%05d", countInCounterTable+1);
				//String id = Integer.toString(count);
				
				String studentId = "ST_"+text;
				students.setStudentId(studentId);
			}
			
			
			
		String insert = "INSERT INTO Students (age, gender, name, student_id) VALUES (?,?,?,?)";
		String update = "Update StudentCounter set count=? where id = 1";

		 PreparedStatement preparedStatement = connection.prepareStatement(insert);

	            preparedStatement.setInt(1, students.getAge());
	            preparedStatement.setString(2, students.getGender());
	            preparedStatement.setString(3, students.getName());
	            preparedStatement.setString(4, students.getStudentId());
	            preparedStatement.executeUpdate();

			
			if( (students.getAge() <=0) || (students.getGender() == null)|| (students.getName()==null) ||(students.getStudentId() == null)) {
				//System.out.println("Students  :  "+students.getStudentId());
				throw new Exception("Invalid data");
			}
			
			
			
			 PreparedStatement preparedStatement1 = connection.prepareStatement(update);
			preparedStatement1.setInt(1, count);
			preparedStatement1.executeUpdate();
			
		
			
			connection.commit();
			
			
		}
		catch(Exception e) {
			connection.rollback();
			e.printStackTrace();
			
			
		}
		return students;
		
		
	}
	
	
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
