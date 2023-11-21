package com.example.librarymanagementsystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarymanagementsystem.dao.GenreDao;
import com.example.librarymanagementsystem.model.Genres;
import com.example.librarymanagementsystem.service.GenreService;

@Service
public class GerneServiceImpl implements GenreService{

	
	@Autowired
	GenreDao genreDao;
	
	@Override
	public void addGenre(Genres genre) {
		genreDao.addGenre(genre);
		
	}

	@Override
	public void updateGenre(Genres book) {
		genreDao.updateGenre(book);
		
	}

	@Override
	public void deleteGenre(int id) {
		genreDao.deleteGenre(id);
		
	}

	@Override
	public List<Genres> getAllGenre() {
		 return genreDao.getAllGenre();
	}

	@Override
	public Genres getGenre(int id) {
		return genreDao.getGenre(id);
	}


}
