package com.example.librarymanagementsystem.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.librarymanagementsystem.dao.StockDao;
import com.example.librarymanagementsystem.model.Stocks;
import com.example.librarymanagementsystem.repository.StockRepo;

@Repository
public class StockDaoImpl implements StockDao{

	@Autowired
	StockRepo stockRepo;
	
	@Override
	public void addStock(Stocks stock) {
		stockRepo.save(stock);
		
	}

	@Override
	public void updateStock(Stocks stock) {
		stockRepo.save(stock);
		
	}

	@Override
	public void deleteStock(int id) {
		stockRepo.deleteById(id);
		
	}

	@Override
	public Stocks getStock(int id) {
		
		return stockRepo.findById(id).get();
	}

}
