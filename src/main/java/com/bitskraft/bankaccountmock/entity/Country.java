package com.bitskraft.bankaccountmock.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="Country")

public class Country {
    @Id
    @Column(name="country_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="country_name")
    private String name;
}
