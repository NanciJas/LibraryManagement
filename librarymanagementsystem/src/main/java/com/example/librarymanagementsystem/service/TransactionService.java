package com.example.librarymanagementsystem.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.example.librarymanagementsystem.model.Transaction;

public interface TransactionService {

	public void addTransaction(Transaction transaction);
	public void updateTransaction(Transaction transaction);
	public Transaction getTransaction(int id);
	public List<Transaction> getAllTransaction();
	public void generateRecord() throws FileNotFoundException ;
	public int calculatePenalty(int id);
	public List<Transaction> getTransactionByName(String name);
	public List<Transaction> getTransactionByStudentId(int id);

}
