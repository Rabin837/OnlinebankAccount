package com.bitskraft.bankaccountmock.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
public class Customer {
    @Id
    private String customerId;
    private String name;
    @Column(name="dateOfBirth")
    private String dob;
    private String gender;
    private String phone;
    private String email;
    @JoinColumn(name="permanentAddressId")
    @ManyToOne(targetEntity=Address.class ,  cascade = CascadeType.ALL)
    private Address permanentAddress;
    @JoinColumn(name="temporaryAddressId")
    @ManyToOne(targetEntity=Address.class ,  cascade = CascadeType.ALL)
    private Address temporaryAddress;

    private String nationality;
    private String fatherName;
    private String motherName;
    private String grandFatherName;
    private String documentType;

    private String citizenshipNumber;
    @Transient
    private String citizenshipFrontImageName;
    @Transient//not creating column name in database but use in some calculation  to do
    private String citizenshipFrontEncodedImage;
    private String citizenshipFrontImagePath;
    @Transient
    private String citizenshipBackImageName;
    @Transient
    private String citizenshipBackEncodedImage;
    private String citizenshipBackImagePath;

    private String passportNumber;
    @Transient
    private String passportImageName;
    @Transient
    private String passportEncodedImage;
    private String passportImagePath;
@Transient
    private String profileImageName;
    @Transient
    private String profileEncodedImage;
    private String profileImagePath;


    private String cifId;
    private String branch;
    private String branchCode;

    private String customerAddedDate;
    private String customerUpdatedDate;

}

