package com.example.librarymanagementsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Students {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//@Column(name = "studentid")
	//private String studentid;
	
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "gender")
	private String gender;
	
	
	@OneToOne
	@JoinColumn(name = "studentcounter")
    private StudentCounter studentcounter;
	
	@Column(name = "studentId")
	private String studentId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public StudentCounter getStudentcounter() {
		return studentcounter;
	}
	public void setStudentcounter(StudentCounter studentcounter) {
		this.studentcounter = studentcounter;
	}
	
	
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	
}
