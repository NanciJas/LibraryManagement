package com.example.librarymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagementsystem.model.Stocks;
import com.example.librarymanagementsystem.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {

	@Autowired
	StockService stockService;
	
	@PostMapping("/create")
	public void addStock(@RequestBody Stocks stock) {
		stockService.addStock(stock);
	}
	
	@DeleteMapping("/delete")
	public void deleteStock(@PathVariable int id) {
		stockService.deleteStock(id); 
	}
	
	@GetMapping("/get/{id}")
	public Stocks getStock(@PathVariable int id) {
		return stockService.getStock(id);
	}
	
	@PutMapping("/update")
	public void updateStock(@RequestBody Stocks stock) {
		stockService.updateStock(stock);
	}
}
