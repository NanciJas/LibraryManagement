package com.example.librarymanagementsystem.model;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;



////////Not Used



@ResponseStatus(HttpStatus.NOT_FOUND)
public class MentionedFileNotFoundException extends RuntimeException {
    public MentionedFileNotFoundException(String message) {
        super(message);
    }

    public MentionedFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
