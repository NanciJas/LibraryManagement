package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.model.Stocks;

public interface StockService {
	public void addStock(Stocks stock);
	public void updateStock(Stocks stock);
	public void deleteStock(int id);
	//public List<Stocks> getAllStocks();
	public Stocks getStock(int id);
}
