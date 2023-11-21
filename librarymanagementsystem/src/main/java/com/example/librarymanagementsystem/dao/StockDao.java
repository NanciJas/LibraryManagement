package com.example.librarymanagementsystem.dao;

import java.util.List;

import com.example.librarymanagementsystem.model.Stocks;

public interface StockDao {

	public void addStock(Stocks stock);
	public void updateStock(Stocks stock);
	public void deleteStock(int id);
	//public List<Stocks> getAllStocks();
	public Stocks getStock(int id);
}
