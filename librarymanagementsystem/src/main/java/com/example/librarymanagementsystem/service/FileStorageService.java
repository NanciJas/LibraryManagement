package com.example.librarymanagementsystem.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.example.librarymanagementsystem.model.FileStoragePojo;

public interface FileStorageService {
	
	 
		
		
	 public String storeFile(MultipartFile file);
	 
	 
	 public Resource loadFileAsResource(String fileName) ;
}
