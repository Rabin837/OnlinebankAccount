package com.bitskraft.bankaccountmock.dto;

import com.bitskraft.bankaccountmock.entity.Address;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.util.Date;

@Data
public class CustomerDto {
    //For Customer
    private String customerId;
    @NotNull @NotEmpty @NotBlank

    private String name;
    @NotNull
   // @Past
    private String dob;
    @NotNull @NotEmpty @NotBlank
    private String gender;
    @NotNull @NotEmpty @NotBlank
    @Pattern(regexp = "[0-9]{10}", message = "invalid phone number !!")
    @Column(unique = true)
    private String phone;
    @NotNull @NotEmpty @NotBlank
    //@Email
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "invalid email address !!")
    @Column(unique = true)
    private String email;
//Permanent Address
private int permanentCountryId;
    private String permanentStatesId;
    private String permanentDistrictsId;
    private String permanentMunicipalitiesId;

    //for return
    private String permanentAddressId;
private Address permanentAddress;
    //temporary Address
    private int temporaryCountryId;
    private String temporaryStatesId;
    private String temporaryDistrictsId;
    private String temporaryMunicipalitiesId;
    //for return
    private String temporaryAddressId;
    private Address temporaryAddress;

    @NotNull @NotEmpty @NotBlank
    private String nationality;
    @NotNull @NotEmpty @NotBlank
    private String fatherName;
    @NotNull @NotEmpty @NotBlank
    private String motherName;
    @NotNull @NotEmpty @NotBlank
    private String grandFatherName;
    @NotNull @NotEmpty @NotBlank
    private String documentType;

    private String citizenshipNumber;

    private String citizenshipFrontEncodedImage;
    private String citizenshipFrontImagePath;

    private String citizenshipBackEncodedImage;
    private String citizenshipBackImagePath;

    private String passportNumber;
    private String passportEncodedImage;
    private String passportImagePath;

    private String profileEncodedImage;
    private String profileImagePath;

    @Column(unique = true)
    private String cifId;
    @NotNull @NotEmpty @NotBlank
    private String branch;
    @NotNull @NotEmpty @NotBlank
    private String branchCode;
    private String customerAddedDate;

    private String customerUpdatedDate;

}
