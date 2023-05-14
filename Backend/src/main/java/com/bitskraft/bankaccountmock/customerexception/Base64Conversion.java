package com.bitskraft.bankaccountmock.customerexception;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Base64Conversion extends  RuntimeException{
    private String message;

}
