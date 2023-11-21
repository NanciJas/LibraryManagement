package com.example.librarymanagementsystem.service;

import java.util.List;

import com.example.librarymanagementsystem.model.Genres;

public interface GenreService {
	public void addGenre(Genres genre);
	public void updateGenre(Genres genre);
	public void deleteGenre(int id);
	public List<Genres> getAllGenre();
	public Genres getGenre(int id);
}
