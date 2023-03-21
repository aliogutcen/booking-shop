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

    BAD_REQUEST_ERROR(1201,"Geçersiz Parametre Girişi Yaptınız",BAD_REQUEST),
    INTERNAL_ERROR(3000,"Sunucuda beklenmeyen hata",INTERNAL_SERVER_ERROR),


    AUTH_PASSWORD_ERROR(1301,"The passwords you entered do not match!",BAD_REQUEST),
    AUTH_LOGIN_ERROR(1303,"Username or password is incorrect",BAD_REQUEST),
    TOKEN_ERROR(3001,"An error occurred during token creation",INTERNAL_SERVER_ERROR),
    TOKEN_DECODE_ERROR(3002,"No user of the token found",INTERNAL_SERVER_ERROR),
    KULLANICI_BULUNAMADI(2301,"Aradığınız id ye ait kullanıcı bulunamamıştır",INTERNAL_SERVER_ERROR);
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
