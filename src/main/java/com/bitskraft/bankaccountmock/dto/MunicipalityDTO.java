package com.bitskraft.bankaccountmock.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MunicipalityDTO {
    private String id;
    private String name;
    private String type;
}
