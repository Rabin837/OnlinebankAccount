package com.bitskraft.bankaccountmock.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {
    private String addressId;
    private int country;
    private String states;
    private String districts;
    private String municipalities;
}
