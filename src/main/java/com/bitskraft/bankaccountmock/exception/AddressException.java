package com.bitskraft.bankaccountmock.exception;

public class AddressException extends  Exception{
    public AddressException(String message){super(message);}
    public static String InvalidAddressException(){
        return "Invalid Address is provided!!!";
    }
}
