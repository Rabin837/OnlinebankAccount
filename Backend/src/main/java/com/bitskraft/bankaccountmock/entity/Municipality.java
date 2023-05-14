package com.bitskraft.bankaccountmock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Municipality")
public class Municipality {
    @Id
    @Column(name="municipality_id")
    private String id;
   @Column(name="municipality_name")
    private String name;
   @Column(name="municipality_type")
   private String type;
   @ManyToOne(cascade = {CascadeType.ALL})
    private District districts;
}
