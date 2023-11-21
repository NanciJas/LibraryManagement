package com.example.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.librarymanagementsystem.model.Genres;

public interface GenreRepo extends JpaRepository<Genres, Integer>{

}
