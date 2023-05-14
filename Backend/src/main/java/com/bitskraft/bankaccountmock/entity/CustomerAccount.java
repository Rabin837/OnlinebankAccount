package com.bitskraft.bankaccountmock.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table
public class CustomerAccount {
    @Id
    private String customerAccountId;

    private String accountType;
    private String currency;
    private String currentBalance;
    private String accountNumber;
//    @Column(nullable = false)
//    private boolean accountStatus;
private String accountOpenDate;
private String accountUpdatedDate;

   @ManyToOne(targetEntity=Customer.class ,  cascade = CascadeType.ALL)
   @JoinColumn(name="customerId")//, referencedColumnName="customerId",nullable=false)
private Customer customer;
}
