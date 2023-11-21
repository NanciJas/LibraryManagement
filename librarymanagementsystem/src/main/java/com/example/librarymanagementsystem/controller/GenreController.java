package com.example.librarymanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.librarymanagementsystem.model.Genres;
import com.example.librarymanagementsystem.service.GenreService;

@RestController
@RequestMapping("/genre")
public class GenreController {

	@Autowired
	GenreService genreService;
	
	@PostMapping("/create")
	public void addGenre(@RequestBody Genres genre) {
		genreService.addGenre(genre);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteGenre(@PathVariable int id) {
		genreService.deleteGenre(id);
	}
	
	@GetMapping("/get/{id}")
	public Genres getGenre(@PathVariable int id) {
		return genreService.getGenre(id);
		
	}
	
	@GetMapping("/getAllGenre")
	public List<Genres> getAllGenre(){
		List<Genres> list = genreService.getAllGenre();
		return list;
	}
	
	@PutMapping("/update")
	public void updateBook(@RequestBody Genres genre) {
		genreService.updateGenre(genre);
	}

}
