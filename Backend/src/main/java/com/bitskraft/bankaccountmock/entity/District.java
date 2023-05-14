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
@Table(name="District")
public class District {
    @Id
    @Column(name = "district_id")
    private String id;

    @Column(name="district_name")
    private String name;

    @ManyToOne(cascade = {CascadeType.ALL})
    private States states;
}