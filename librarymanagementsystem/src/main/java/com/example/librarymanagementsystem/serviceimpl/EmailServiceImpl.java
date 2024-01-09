package com.example.librarymanagementsystem.serviceimpl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.librarymanagementsystem.model.EmailDetails;
import com.example.librarymanagementsystem.model.Transaction;
import com.example.librarymanagementsystem.repository.TransactionRepo;
import com.example.librarymanagementsystem.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{

	
	 @Autowired 
	 private JavaMailSender javaMailSender;
	 @Autowired 
	 private TransactionRepo transaction;
	 
	    @Value("${spring.mail.username}") 
	    private String sender;
	
	@Override
	 public String sendSimpleMail(EmailDetails details)
    {
 
        // Try block to check for exceptions
        try {
 
            // Creating a simple mail message
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();
 
            // Setting up necessary details
           // System.out.println("sender : "+sender);
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
 
            // Sending the mail
            
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
 
        // Catch block to handle the exceptions
        catch (Exception e) {
        	e.printStackTrace();
            return "Error while Sending Mail";
        }
    }

	@Override
	 public String
	    sendMailWithAttachment(EmailDetails details)
	    {
	        // Creating a mime message
	        MimeMessage mimeMessage
	            = javaMailSender.createMimeMessage();
	        MimeMessageHelper mimeMessageHelper;
	 
	        try {
	 
	            // Setting multipart as true for attachments to
	            // be send
	            mimeMessageHelper
	                = new MimeMessageHelper(mimeMessage, true);
	            mimeMessageHelper.setFrom(sender);
	            mimeMessageHelper.setTo(details.getRecipient());
	            mimeMessageHelper.setText(details.getMsgBody());
	            mimeMessageHelper.setSubject(
	                details.getSubject());
	 
	            // Adding the attachment
	            FileSystemResource file
	                = new FileSystemResource(
	                    new File(details.getAttachment()));
	 
	            mimeMessageHelper.addAttachment(
	                file.getFilename(), file);
	 
	            // Sending the mail
	            javaMailSender.send(mimeMessage);
	            return "Mail sent Successfully";
	        }
	 
	        // Catch block to handle MessagingException
	        catch (MessagingException e) {
	 
	            // Display message when exception occurred
	            return "Error while sending mail!!!";
	        }
	    }

	
	

	public List<Transaction> Items;
 

    @Scheduled(cron = "${scheduling.job.cron}")
    @Override
    public void sendreminders() {

        Date date = new Date();
        //get the list of actions from db which due date is greater than current date
        
		try {
			Items = transaction.findByRetrunDate();
		} catch (Exception e) {
			// TODO Auto-generated catch 

			
			e.printStackTrace();
		}
		

        for (Transaction action : Items) {
            sendMail(action);
        }

    }

    // method to send reminders to users
    @Override
    public void sendMail(Transaction action) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(action.getEmail());
        message.setSubject("Pending Tasks");
        message.setText( " due date is:" + action.getReturnDate());
        javaMailSender.send(message);
    }
	
}
