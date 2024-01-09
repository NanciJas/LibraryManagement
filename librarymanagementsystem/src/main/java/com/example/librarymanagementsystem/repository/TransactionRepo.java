package com.example.librarymanagementsystem.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.librarymanagementsystem.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer>{
	
	
	@Query(value="select lend_date,return_date,student_id,book_status,book_id,transaction_status,penalty,s.id from Transaction t left join students s ON t.student_id = s.id where s.name = ?",nativeQuery = true)
	public List<Transaction> findByName(String name);
	
	@Query(value="select lend_date,return_date,student_id,book_status,book_id,transaction_status,penalty,s.id from Transaction t left join students s ON t.student_id = s.id where s.id = ?",nativeQuery = true)
	public List<Transaction> findByStudentId(int id);
	
	
	@Query(value="select * FROM transaction where return_date >= '2024-01-08'",nativeQuery = true)
	 public List<Transaction> findByRetrunDate();
}
