package com.example.librarymanagementsystem.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.librarymanagementsystem.model.Transaction;
import com.example.librarymanagementsystem.model.UploadFile;
import com.example.librarymanagementsystem.service.FileStorageService;
import com.example.librarymanagementsystem.service.TransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	//FileStorageService fileStorageService;

	 private static String FILE_FOLDER = "D:\\tempflod\\";
	
	
	@PostMapping("/create")
	public void addTransaction(@RequestBody Transaction transaction) {
		// System.out.println("transaction : "+transaction);
		System.out.println("transaction : " + transaction.getLendDate());
		transactionService.addTransaction(transaction);
	}

	@PutMapping("/update")
	public void updateTransaction(@RequestBody Transaction transaction) {
		transactionService.updateTransaction(transaction);
	}

	@GetMapping("/getTransaction/{id}")
	public Transaction getTransaction(@PathVariable int id) {
		return transactionService.getTransaction(id);
	}

	@GetMapping("getAllTransaction")
	public List<Transaction> getAllTransaction() {
		return transactionService.getAllTransaction();
	}

	@GetMapping("/record")
	public String singleFileUpload() throws FileNotFoundException {
		transactionService.generateRecord();
		return "Downloaded";
	}

	@GetMapping("/getTrans/{name}")
	public List<Transaction> getTransactionByName(@PathVariable(value = "name") String name) {
		return transactionService.getTransactionByName(name);
	}

	@GetMapping("/getStudent/{id}")
	public List<Transaction> getStudentd(@PathVariable int id) {
		return transactionService.getTransactionByStudentId(id);

	}

	@GetMapping("/penalty/{id}")
	public int calculatePenalty(@PathVariable int id) {
		return transactionService.calculatePenalty(id);

	}


	
	@GetMapping("/download/{file}") 
	public void download (HttpServletRequest request, HttpServletResponse response,@PathVariable String file) throws IOException {
	  
		Path file1 = Paths.get(FILE_FOLDER+file);

	

		String contentType =Files.probeContentType(file1);
	  if (contentType == null) { 

	  contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE; }
	  
	  response.setContentType(contentType); // File Size
	  response.setContentLengthLong(Files.size(file1));
	
			  response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
			  ContentDisposition.attachment() .filename(file1.getFileName().toString(),
			  StandardCharsets.UTF_8) .build() .toString()); 
			  // Response data to the client
			  Files.copy(file1, response.getOutputStream()); 
			   
	}

	

	@GetMapping("/zipp/{f}")
    public void zipDownloadd (HttpServletRequest request, HttpServletResponse response,@PathVariable String f) throws IOException {
        
        // List of files to be downloaded
		Path file = Paths.get("D:\\tempflod\\"+f);
      
		 
		 response.setContentType("application/zip"); // zip archive format
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                                                                            .filename(f+".zip", StandardCharsets.UTF_8)
                                                                            .build()
                                                                            .toString());
        
        
        // Archiving multiple files and responding to the client
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())){
            for (Path files : file) {
                try (InputStream inputStream = Files.newInputStream(file)) {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getFileName().toString()));
                    StreamUtils.copy(inputStream, zipOutputStream);
                    zipOutputStream.flush();
                }
            }
        }
    
    }
}

	
	
	
	
	


	
/////////////////////////////////////88888888888888888888888888888888888////////////////////////////////////////////////////////////////////////////////	


	
	
	
//	@GetMapping("/download/{file}") 
//	public void download (HttpServletRequest request, HttpServletResponse response,@PathVariable String file) throws IOException {
//	  
//		
//		Path file1 = Paths.get("D:\\tempflod\\"+file);
//		
//		
//
//	  
//	  // Get the media type of the file
//		String contentType =Files.probeContentType(file1);
//	  if (contentType == null) { 
//		  // Use the defaultmedia type 
//	  contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE; }
//	  
//	  response.setContentType(contentType);
//	  // File Size
//	  response.setContentLengthLong(Files.size(file1));
//	
//			  response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
//			  ContentDisposition.attachment() .filename(file1.getFileName().toString(),
//			  StandardCharsets.UTF_8) .build() .toString()); 
//			  // Response data to the client
//			  Files.copy(file1, response.getOutputStream()); 
//			 
//			 
//	}
	
	/*
	 * @GetMapping("/zip/{f}") public void zipDownload (HttpServletRequest request,
	 * HttpServletResponse response,@PathVariable String f) throws IOException {
	 * 
	 * // List of files to be downloaded Path file = Paths.get("D:\\tempflod\\"+f);
	 * File fi = new File(UPLOADED_FOLDER + f); fileDownloadUri =
	 * ServletUriComponentsBuilder.fromCurrentContextPath().path("/download-file/")
	 * .path(f).toUriString();
	 * 
	 * response.setContentType("application/zip"); // zip archive format
	 * response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
	 * ContentDisposition.attachment() .filename(f+".zip", StandardCharsets.UTF_8)
	 * .build() .toString());
	 * 
	 * 
	 * // // Archiving multiple files and responding to the client
	 * try(ZipOutputStream zipOutputStream = new
	 * ZipOutputStream(response.getOutputStream())){ for (Path files : file) { try
	 * (InputStream inputStream = Files.newInputStream(file)) {
	 * zipOutputStream.putNextEntry(new ZipEntry(file.getFileName().toString()));
	 * StreamUtils.copy(inputStream, zipOutputStream); zipOutputStream.flush(); } }
	 * } // // return new UploadFile(f, fileDownloadUri1); // // return new
	 * UploadFile(f, fileDownloadUri1,fi); }
	 * 
	 * 
	 * 
	 * 
	 */
	

	


	
	/*
	 * private static String UPLOADED_FOLDER = "C:\\upload\\"; // "D:\\tempflod\\";
	 * 
	 * String fileDownloadUri = ""; String contentType = "";
	 * 
	 * @PostMapping("/upload") public UploadFile
	 * handleFileUpload(@RequestParam("file") MultipartFile file) {
	 * 
	 * try { File f = new File(UPLOADED_FOLDER + file.getOriginalFilename());
	 * file.transferTo(f);
	 * 
	 * fileDownloadUri =
	 * ServletUriComponentsBuilder.fromCurrentContextPath().path("/download-file/")
	 * .path(file.getOriginalFilename()).toUriString(); contentType =
	 * MediaType.APPLICATION_OCTET_STREAM_VALUE;
	 * 
	 * System.out.println("fileDownloadUri " + fileDownloadUri); } catch (Exception
	 * e) { // return //
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } return new
	 * UploadFile(file.getOriginalFilename(), fileDownloadUri); }
	 */


	/*
	 * @PostMapping("/upload-single-file") public UploadFile
	 * uploadSingleFile(@RequestParam("file") MultipartFile file) {
	 * System.out.println("file  : " +file); String
	 * fileName=fileStorageService.storeFile(file);
	 * 
	 * String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	 * .path("/download-file/") .path(fileName) .toUriString();
	 * 
	 * return new UploadFile(fileName, fileDownloadUri);
	 * 
	 * }
	 */
	

