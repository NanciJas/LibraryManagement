package com.example.librarymanagementsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Studentcounter")
public class StudentCounter {

	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	
	@Column(name = "prefix")
	private String prefix;
	
	@Column(name = "count")
	private int count;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
