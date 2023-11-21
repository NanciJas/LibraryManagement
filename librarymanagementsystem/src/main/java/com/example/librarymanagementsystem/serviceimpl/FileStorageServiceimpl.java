package com.example.librarymanagementsystem.serviceimpl;

import org.springframework.web.multipart.MultipartFile;

import com.example.librarymanagementsystem.model.FileStorageException;
import com.example.librarymanagementsystem.model.FileStoragePojo;
import com.example.librarymanagementsystem.model.MentionedFileNotFoundException;
import com.example.librarymanagementsystem.service.FileStorageService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;


public class FileStorageServiceimpl  implements FileStorageService{
		
	 private final Path fileStorageLocation;
		
		
		
		@Autowired
		FileStoragePojo fileStoragePojo;
		
	    public FileStorageServiceimpl() {
			
			
	        this.fileStorageLocation = Paths.get(fileStoragePojo.getUploadDir())
	                .toAbsolutePath().normalize();

	        try {
	            Files.createDirectories(this.fileStorageLocation);
	        } catch (Exception ex) {
	            throw new FileStorageException("Unable to create the directory where the uploaded files will be stored.", ex);
	        }
	    }
	
		@Override
	 public String storeFile(MultipartFile file) {
	        // Normalize file name
		
			 
			
			 
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	   	 System.out.println("fileNamecccccccccccccccccccc  : " +fileName);
	        try {
	            // Check if the file's name contains invalid characters
	            if(fileName.contains("..")) {
	                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
	            }

	            
	            
	            // Copy file to the target location (Replacing existing file with the same name)
	            Path targetLocation = this.fileStorageLocation.resolve(fileName);
	            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

	            return fileName;
	        } catch (IOException ex) {
	            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
	        }
	    }

	 
		@Override
	 public Resource loadFileAsResource(String fileName) {
	        try {
	            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	                return resource;
	            } else {
	                throw new MentionedFileNotFoundException("File not found " + fileName);
	            }
	        } catch (MalformedURLException ex) {
	            throw new MentionedFileNotFoundException("File not found " + fileName, ex);
	        }
	    }
}
