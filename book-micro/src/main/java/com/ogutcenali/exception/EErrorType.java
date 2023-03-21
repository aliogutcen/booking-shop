package com.ogutcenali.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {

    BAD_REQUEST_ERROR(1201,"Geçersiz Parametre Girişi Yaptınız",BAD_REQUEST),
    INTERNAL_ERROR(3000,"Sunucuda beklenmeyen hata",INTERNAL_SERVER_ERROR),

    AUTHOR_HAS_BEEN(2302,"Author has been",BAD_REQUEST),

    AUTHOR_NOT_FOUND(2303,"Author not found",NOT_FOUND),
    SUBJECT_NOT_FOUND(2303,"Subject not found",NOT_FOUND),
    SUBJECT_HAS_BEEN(3302,"Subject has been",BAD_REQUEST),
    BOOK_HAS_BEEN(4302,"Book has been",BAD_REQUEST),
    BOOK_NOT_FOUND(4303,"Book_ID Not Found",BAD_REQUEST),
    TOKEN_ERROR(3001,"An error occurred during token creation",INTERNAL_SERVER_ERROR),
    TOKEN_DECODE_ERROR(3002,"No user of the token found",INTERNAL_SERVER_ERROR);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
