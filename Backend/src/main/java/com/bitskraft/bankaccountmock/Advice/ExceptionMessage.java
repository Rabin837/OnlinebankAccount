package com.bitskraft.bankaccountmock.Advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {
    private String message;
    private int statusCode;
private HttpStatus status;
}
