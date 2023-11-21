package com.example.librarymanagementsystem.dao;

import java.util.List;

import com.example.librarymanagementsystem.model.Transaction;

public interface TransactionDao {
	
	public void addTransaction(Transaction transaction);
	public void updateTransaction(Transaction transaction);
	public Transaction getTransaction(int id);
	public List<Transaction> getAllTransaction();
	public List<Transaction> getTransactionByName(String name);
	public List<Transaction> getTransactionByStudentId(int id);
	
}
