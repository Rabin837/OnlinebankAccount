package com.bitskraft.bankaccountmock.Advice;

import com.bitskraft.bankaccountmock.customerexception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice

public class GlobalException {
    //common to all
    @ExceptionHandler(EntityNotFound.class)
    @ResponseStatus(HttpStatus.OK)

    public @ResponseBody ExceptionMessage entityNotFound(EntityNotFound entityNotFound){

        return new ExceptionMessage( entityNotFound.getMessage(),HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(FieldNotEmpty.class)
    @ResponseStatus(HttpStatus.OK)

    public @ResponseBody ExceptionMessage FieldNotEmpty(FieldNotEmpty fieldNotEmpty){

        return new ExceptionMessage(fieldNotEmpty.getMessage(),HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AlreadyExist.class)
    @ResponseStatus(HttpStatus.OK)

    public @ResponseBody ExceptionMessage AlreadyExist(AlreadyExist alreadyExist){

        return new ExceptionMessage(alreadyExist.getMessage(),HttpStatus.CONFLICT.value(),HttpStatus.CONFLICT);
    }


    @ExceptionHandler(Base64Conversion.class)
    @ResponseStatus(HttpStatus.OK)

    public @ResponseBody ExceptionMessage Base64Conversion(Base64Conversion base64Conversion){

        return new ExceptionMessage(base64Conversion.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)

    public @ResponseBody Map<String,String> handleInvalidArgument(MethodArgumentNotValidException validationException){
        Map<String,String> errorMap=new HashMap<>();
        validationException.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return errorMap;
    }

}

