package com.ogutcenali.exception;

import lombok.Getter;

@Getter
public class UserProfileException extends RuntimeException{
    private final EErrorType EErrorType;

    /**
     * Runtime dan miras aldığımız için hata mesajının kendisine iletilmesi gereklidir.
     * @param EErrorType
     */
    public UserProfileException(EErrorType EErrorType){
        super(EErrorType.getMessage());
        this.EErrorType = EErrorType;
    }

    public UserProfileException(EErrorType EErrorType, String message){
        super(message);
        this.EErrorType = EErrorType;
    }
}
