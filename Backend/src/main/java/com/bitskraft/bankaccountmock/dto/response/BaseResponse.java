package com.bitskraft.bankaccountmock.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private String message;
    private int statusCode;
    private HttpStatus status;
}
