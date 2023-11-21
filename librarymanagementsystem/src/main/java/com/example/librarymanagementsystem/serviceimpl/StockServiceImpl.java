package com.example.librarymanagementsystem.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarymanagementsystem.dao.StockDao;
import com.example.librarymanagementsystem.model.Stocks;
import com.example.librarymanagementsystem.service.StockService;

@Service
public class StockServiceImpl implements StockService{

	@Autowired
	StockDao stockDao;
	
	@Override
	public void addStock(Stocks stock) {
		stockDao.addStock(stock);
		
	}

	@Override
	public void updateStock(Stocks stock) {
	stockDao.updateStock(stock);
		
	}

	@Override
	public void deleteStock(int id) {
		stockDao.deleteStock(id);
		
	}

	@Override
	public Stocks getStock(int id) {
		
		return stockDao.getStock(id);
	}

}
