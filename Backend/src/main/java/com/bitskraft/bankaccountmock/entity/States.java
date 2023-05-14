package com.bitskraft.bankaccountmock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "States")
public class States {
    @Id
    @Column(name="state_id")
    private String id;
    @Column(name="state_name")
    private String name;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Country country;
}
