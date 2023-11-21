package com.example.librarymanagementsystem.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.librarymanagementsystem.dao.TransactionDao;
import com.example.librarymanagementsystem.model.Transaction;
import com.example.librarymanagementsystem.repository.TransactionRepo;

@Repository
public class TransactionDaoImpl implements  TransactionDao{

	@Autowired
	TransactionRepo transactionRepo;
	
	
	@Override
	public void addTransaction(Transaction transaction) {
		transactionRepo.save(transaction);
		
	}

	@Override
	public void updateTransaction(Transaction transaction) {
		transactionRepo.save(transaction);
		
	}

	@Override
	public Transaction getTransaction(int id) {
	
		return transactionRepo.findById(id).get();
	}

	@Override
	public List<Transaction> getAllTransaction() {
		
		return transactionRepo.findAll();
	}


	@Override
	public List<Transaction> getTransactionByName(String name) {
		
		return transactionRepo.findByName(name);
	}

	@Override
	public List<Transaction> getTransactionByStudentId(int id) {
		
		return transactionRepo.findByStudentId(id);
	}




}
