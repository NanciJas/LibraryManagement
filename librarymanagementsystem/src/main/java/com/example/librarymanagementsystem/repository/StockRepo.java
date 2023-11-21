package com.example.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.librarymanagementsystem.model.Stocks;



public interface StockRepo extends JpaRepository<Stocks,Integer>{

}
