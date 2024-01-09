package com.example.librarymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagementsystem.model.EmailDetails;
import com.example.librarymanagementsystem.service.EmailService;

@RestController
public class EmailController {

	
	 @Autowired 
	 private EmailService emailService;
	 
	    // Sending a simple Email
	    @PostMapping("/sendMail")
	    public String sendMail(@RequestBody EmailDetails details)
	    {
	        String status
	            = emailService.sendSimpleMail(details);
	 
	        return status;
	    }
	 
	    // Sending email with attachment
	    @PostMapping("/sendMailWithAttachment")
	    public String sendMailWithAttachment(@RequestBody EmailDetails details)
	    {
	        String status
	            = emailService.sendMailWithAttachment(details);
	 
	        return status;
	    }
	
	    
	    // Sending a simple Email
	    @PostMapping("/sendMailSchedulder")
	    public void sendMailSchedulder()
	    {
	        emailService.sendreminders();
	 
	       
	    }
}
