package com.example.librarymanagementsystem.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.librarymanagementsystem.model.Books;
import com.example.librarymanagementsystem.model.Genres;

public interface GenreDao {
	public void addGenre(Genres genre);
	public void updateGenre(Genres genre);
	public void deleteGenre(int id);
	public List<Genres> getAllGenre();
	public Genres getGenre(int id);
}
