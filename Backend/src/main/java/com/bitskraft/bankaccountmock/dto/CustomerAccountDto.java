package com.bitskraft.bankaccountmock.dto;

import com.bitskraft.bankaccountmock.entity.Customer;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@ToString
public class CustomerAccountDto {
    private String customerAccountId;
    @NotNull
    @NotEmpty
    @NotBlank

    private String accountType;
    @NotNull @NotEmpty @NotBlank
    private String currency;
    @NotNull @NotEmpty @NotBlank @Min(1000) @Max(1000000000)
    private String currentBalance;
    @Column(unique = true)
    private String accountNumber;
    //private boolean accountStatus;
    private String customerId;
    private String accountOpenDate;
    private String accountUpdatedDate;

    private Customer customer;

}
