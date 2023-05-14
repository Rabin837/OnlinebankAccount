package com.bitskraft.bankaccountmock.dto;

import lombok.*;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CountryDTO {
    private int id;
    private String name;
    private List<StatesDTO> states;
}
