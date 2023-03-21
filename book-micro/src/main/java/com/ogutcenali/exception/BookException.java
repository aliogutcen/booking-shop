package com.ogutcenali.exception;

import lombok.Getter;

@Getter
public class BookException extends RuntimeException{
    private final EErrorType EErrorType;

    /**
     * Runtime dan miras aldığımız için hata mesajının kendisine iletilmesi gereklidir.
     * @param EErrorType
     */
    public BookException(EErrorType EErrorType){
        super(EErrorType.getMessage());
        this.EErrorType = EErrorType;
    }

    public BookException(EErrorType EErrorType, String message){
        super(message);
        this.EErrorType = EErrorType;
    }
}
