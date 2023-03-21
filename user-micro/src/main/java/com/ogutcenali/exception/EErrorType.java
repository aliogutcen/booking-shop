package com.ogutcenali.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {

    BAD_REQUEST_ERROR(1201, "Invalid Parameter Input", BAD_REQUEST),
    INTERNAL_ERROR(3000, "Unexpected error on the server", INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND(4002, "User not found!!", BAD_REQUEST),
    TOKEN_ERROR(3001, "An error occurred during token creation", INTERNAL_SERVER_ERROR),
    TOKEN_DECODE_ERROR(3002, "No user of the token found", INTERNAL_SERVER_ERROR);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
