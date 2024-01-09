package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.model.EmailDetails;
import com.example.librarymanagementsystem.model.Transaction;

public interface EmailService {


    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);
 
    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);

	void sendMail(Transaction action);

	void sendreminders();

	//static void sendMailWithAttachment(String string, String string2, String string3, String string4) {
		// TODO Auto-generated method stub
		
	//}
}
