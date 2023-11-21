package com.example.librarymanagementsystem.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.librarymanagementsystem.dao.GenreDao;
import com.example.librarymanagementsystem.model.Books;
import com.example.librarymanagementsystem.model.Genres;
import com.example.librarymanagementsystem.repository.GenreRepo;

@Repository
public class GenreDaoImpl implements GenreDao{
	@Autowired
	GenreRepo genreRepo;
	
	@Override
	public void addGenre(Genres genre) {
		try {
			genreRepo.save(genre);
		}
		catch(Exception e){
			System.out.println();
			e.printStackTrace(System.out);
		}
		
	}

	@Override
	public void updateGenre(Genres genre) {
		genreRepo.save(genre);
		
	}

	@Override
	public void deleteGenre(int id) {
		genreRepo.deleteById(id);
		
	}

	@Override
	public List<Genres> getAllGenre() {
		 return genreRepo.findAll();
	}

	@Override
	public Genres getGenre(int id) {
		return genreRepo.findById(id).get();
	}

}



//@Override
//public ResponseEntity<Genres> addGenre(Genres genre) {
//	try {
//		genreRepo.save(genre);
//	}
//	catch(DataAccessException e){
//		return (ResponseEntity<Genres>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	return null;
