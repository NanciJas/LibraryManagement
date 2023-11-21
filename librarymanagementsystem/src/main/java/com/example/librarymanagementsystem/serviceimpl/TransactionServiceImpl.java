package com.example.librarymanagementsystem.serviceimpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import com.example.librarymanagementsystem.dao.TransactionDao;
import com.example.librarymanagementsystem.model.Transaction;
import com.example.librarymanagementsystem.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	TransactionDao transactionDao;
	
	@Override
	public void addTransaction(Transaction transaction) {
	transactionDao.addTransaction(transaction);
		
	}

	@Override
	public void updateTransaction(Transaction transaction) {
		transactionDao.updateTransaction(transaction);
		
	}

	@Override
	public Transaction getTransaction(int id) {
		
		return transactionDao.getTransaction(id);
	}

	@Override
	public List<Transaction> getAllTransaction() {
		
		return transactionDao.getAllTransaction();
	}

	@Override
	@Async(value = "threadPoolTaskExecutor")
	public void generateRecord() throws FileNotFoundException {
	 
		LocalDate localDate = LocalDate.now();
		Date date = java.sql.Date.valueOf(localDate);
		String record = "";
		int LendingCount = 0;
		int returningCount = 0;
		String folderName = "D:\\tempflod\\";
		//String fileName = "DailyReport"+date;
		//String path = folderName + fileName;
		
		
		
	    long millis = System.currentTimeMillis();
	    @SuppressWarnings("deprecation")
		String datetime = new Date(millis).toGMTString();
	    datetime = datetime.replace(" ", "");
	    datetime = datetime.replace(":", "");
	    String fileName = "DailyReport" + "_" + datetime + "_" + millis;
		
		
	    String path = folderName + fileName;
		
		
		
		
		
		 int j = 0;
		BufferedWriter out = null;
		PrintWriter writer = new PrintWriter(path);
		writer.print("");
		
		 System.out.println(date);
		 System.out.println(date.getClass().getName());

			try {
				File file = new File(path);
				/*
				 * System.out.println("file path:"+file); if(file.exists()) {
				 * System.out.println("file path: exists"); j++; path = path+"("+j+")";
				 * 
				 * } file = new File(path);
				 */
				List<Transaction> rs = transactionDao.getAllTransaction();
				
				out = new BufferedWriter(new FileWriter(file, true));
					
				out.write(String.format("%-20s %-10s %-20s %-10s %-20s %-10s %-20s %-10s %-20s %-10s %-20s %-10s %-20s  \r\n", "StudentName","|", "BookName","|","Penalty","|","BookStatus","|","TransactionStatus","|","LendDate","|","ReturnDate"));
				out.write("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				out.newLine();	
				for(int i = 0;i<rs.size();i++) {
					 Transaction s = rs.get(i);
					 if(s.getLendDate().compareTo(date) == 0) {
							LendingCount++;
					 out.write(String.format("%-20s %-10s %-20s %-10s %-20s %-10s %-20s %-10s %-20s %-10s %-20s %-10s %-20s  \r\n",s.getStudents().getName(),"|",s.getBooks().getBookName(),"|",s.getPenalty(),"|",s.getBookStatus(),"|",s.getTransactionStatus(),"|",s.getLendDate(),"|",s.getReturnDate()));
					 out.write("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					 out.newLine();	
					 }
					 
					 if(s.getReturnDate().compareTo(date) == 0) {
							returningCount++;
							//s.setBookStatus("Returned");
							 out.write(String.format("%-20s %-10s %-20s %-10s %-20s %-10s %-20s %-10s %-20s %-10s %-20s %-10s %-20s  \r\n",s.getStudents().getName(),"|",s.getBooks().getBookName(),"|",s.getPenalty(),"|",s.getBookStatus(),"|",s.getTransactionStatus(),"|",s.getLendDate(),"|",s.getReturnDate()));
							 out.write("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
							 out.newLine();
						}
				}
				out.newLine();
				out.write(" Book lended count : " +LendingCount );
				out.newLine();
				out.write(" Book retruned count :" +returningCount);				
				out.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}
	}
	/*
	 * @Override public int calculatePenalty(int id) {
	 * 
	 * int penalty = 0; LocalDate localDate = LocalDate.now(); Date date =
	 * java.sql.Date.valueOf(localDate); List<Transaction> transaction =
	 * (List<Transaction>) transactionDao.getTransactionByStudentId(id); long
	 * exceedingDays = 0; long exceedingDays1 = 0; for(int i
	 * =0;i<transaction.size();i++) { Transaction t = transaction.get(i);
	 * System.out.println("returnDate   " +t.getReturnDate());
	 * System.out.println("date   " +date); if(date.compareTo(t.getReturnDate()) > 0
	 * ) { exceedingDays = (date.getTime() - t.getReturnDate().getTime());
	 * exceedingDays1 = TimeUnit.MILLISECONDS.toDays(exceedingDays); } }
	 * 
	 * 
	 * 
	 * System.out.println("exceedingDays :  " +exceedingDays1); penalty = (int)
	 * (exceedingDays1 * 30); System.out.println(penalty);
	 * 
	 * return penalty; }
	 */

	
	@Override
	public int calculatePenalty(int id) {
		
		int penalty = 0;
		long exceedingDays = 0;
		long returndate = 0;
		LocalDate localDate = LocalDate.now();
		Date date = java.sql.Date.valueOf(localDate);
	
		SimpleDateFormat sdf  = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		
		
		List<Transaction> transaction = transactionDao.getTransactionByStudentId(id);
		for(int i =0;i<transaction.size();i++) {
			Transaction t = transaction.get(i);
			
			if(date.compareTo(t.getLendDate()) > 0 ) {
				
				if(t.getBookStatus().equals ("lended")) {
					exceedingDays =  (date.getTime() - t.getLendDate().getTime());
					exceedingDays = TimeUnit.MILLISECONDS.toDays(exceedingDays);
					System. out. println("returndate1   : "+exceedingDays);
					
					if(exceedingDays > 30) {
						exceedingDays = exceedingDays - 30;	
						penalty = (int) (exceedingDays * 30);
					}
						/*
						 * cal.setTime(t.getReturnDate()); cal.add(Calendar.DAY_OF_MONTH, (int)
						 * returndate); String dateAfter = sdf.format(cal.getTime()); System. out.
						 * println("dateWith5Days   : "+dateAfter);
						 */
						
				
				}else{
					if(t.getReturnDate().compareTo(t.getLendDate()) > 0  && t.getBookStatus().equals ("returned")) {
						exceedingDays =(t.getReturnDate().getTime() - t.getLendDate().getTime());
						exceedingDays =TimeUnit.MILLISECONDS.toDays(exceedingDays);
						System.out.println(exceedingDays); 
						System.out.println(t.getLendDate());
							if(exceedingDays>30) {
								exceedingDays = exceedingDays-30;
							 }
								  
						System.out.println(exceedingDays);
						penalty = (int) (exceedingDays * 30);
					}
					
				}
			}
			
			
		}
		System.out.println(penalty);
		
		return penalty;
	}

	@Override
	public List<Transaction> getTransactionByName(String name) {
		
		return transactionDao.getTransactionByName(name);
	}

	@Override
	public List<Transaction> getTransactionByStudentId(int id) {
		
		return transactionDao.getTransactionByStudentId(id);
	}

/*
 * String startDate="01-02-2013";
SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
java.util.Date date = sdf1.parse(startDate);
java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
 */

}
