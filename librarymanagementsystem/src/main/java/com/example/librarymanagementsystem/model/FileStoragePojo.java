package com.example.librarymanagementsystem.model;

import org.springframework.boot.context.properties.ConfigurationProperties;





////////Not Used


@ConfigurationProperties(prefix = "file")
	public class FileStoragePojo {

		private String uploadDir;

	    public String getUploadDir() {
	        return uploadDir;
	    }

	    public void setUploadDir(String uploadDir) {
	        this.uploadDir = uploadDir;
	    }
}
