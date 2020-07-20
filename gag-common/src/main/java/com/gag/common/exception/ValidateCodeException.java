package com.gag.common.exception;

/**
 * Description:
 * User: scot
 * Date: 2020-07-16
 * Time: 9:04
 */

public class ValidateCodeException extends Exception{

    private static final long serialVersionUID = 7514854456967620043L;

    public ValidateCodeException(String message){
        super(message);
    }
}
