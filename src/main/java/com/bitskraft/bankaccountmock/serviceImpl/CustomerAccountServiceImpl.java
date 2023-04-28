package com.bitskraft.bankaccountmock.serviceImpl;


import com.bitskraft.bankaccountmock.customerexception.AlreadyExist;
import com.bitskraft.bankaccountmock.customerexception.Base64Conversion;
import com.bitskraft.bankaccountmock.customerexception.EntityNotFound;
import com.bitskraft.bankaccountmock.dto.CustomerAccountDto;
import com.bitskraft.bankaccountmock.dto.response.BaseResponse;
import com.bitskraft.bankaccountmock.entity.Customer;
import com.bitskraft.bankaccountmock.entity.CustomerAccount;
import com.bitskraft.bankaccountmock.repository.CustomerAccountRepository;
import com.bitskraft.bankaccountmock.service.CustomerAccountService;
import com.bitskraft.bankaccountmock.service.CustomerService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {
    @Autowired
    private CustomerAccountRepository customerAccountRepository;
    @Autowired
    private CustomerService customerService;
    private static long accountNumber;

    @Override
    public BaseResponse addAccount(String customerId,CustomerAccountDto customerAccountDto) {
        Customer customer = customerService.returnCustomer(customerId);

        Optional<CustomerAccount> customerAccount = customerAccountRepository.findByCustomerIdAccountType(customer.getCustomerId(), customerAccountDto.getAccountType());
        if (customerAccount.isEmpty()) {
            //Convert Dto to Entity
            CustomerAccount customerAccount1 = this.convertDtoToEntity(customerAccountDto,customer);
            customerAccountRepository.save(customerAccount1);

            return new BaseResponse(customerAccount1.getAccountType() + " account created successfully", HttpStatus.CREATED.value(), HttpStatus.CREATED);

        } else {
            throw new AlreadyExist("Your " + customerAccountDto.getAccountType() + " account type already exist");
        }

    }

    @Override
    public ResponseEntity<List<CustomerAccountDto>> getAccounts(String customerId) {
        customerService.returnCustomer(customerId);

//Directly convert to Customer Account to Customer Account Dto
            List<CustomerAccountDto> customerAccountDtoList = customerAccountRepository.findByCustomerId(customerId).stream().map(this::convertEntityToDto).collect(Collectors.toList());

            if (customerAccountDtoList.isEmpty()) {
                throw new EntityNotFound("You have not created any account yet");
            } else {
                return ResponseEntity.ok(customerAccountDtoList);
            }

    }
//Finding Specific Account according to accountType of corresponding customer
@Override
public CustomerAccountDto getSpecificAccount(String customerId,String customerAccountId) {
    customerService.returnCustomer(customerId);

//Directly convert to Customer Account to Customer Account Dto
    CustomerAccount customerAccount = customerAccountRepository.findById(customerAccountId).orElseThrow(() -> new EntityNotFound("Customer account not found"));
CustomerAccountDto customerAccountDto=this.convertEntityToDto(customerAccount);
        return customerAccountDto;


}
    @Override
    public BaseResponse deleteAccount(String customerAccountId) {

        CustomerAccount customerAccount = customerAccountRepository.findById(customerAccountId).orElseThrow(() -> new EntityNotFound("Customer account not found"));

            customerAccountRepository.delete(customerAccount);
        return new BaseResponse("Customer account deleted successfully", HttpStatus.OK.value(), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<List<CustomerAccountDto>> getAllAccount() {
        // convert all list entity to DTO
        List<CustomerAccountDto> customerAccountDtoList = customerAccountRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
        if (customerAccountDtoList.isEmpty()) {
            throw new EntityNotFound("You have not added any account yet");
        } else {
            return ResponseEntity.ok(customerAccountDtoList);

        }
    }

    @Override
    public BaseResponse updateAccount(String customerId, String customerAccountId, CustomerAccountDto customerAccountDto) {
       Customer customer=customerService.returnCustomer(customerId);

//Directly convert to Customer Account to Customer Account Dto
            CustomerAccount customerAccount = customerAccountRepository.findById(customerAccountId).orElseThrow(()->new EntityNotFound("Your account not found"));


        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        String currentDate= DateFor.format(date);

                CustomerAccount newCustomerAccount = this.convertDtoToEntity(customerAccountDto,customer);

                customerAccount.setAccountType(customerAccount.getAccountType());
                customerAccount.setCurrency(customerAccount.getCurrency());
                customerAccount.setCurrentBalance(newCustomerAccount.getCurrentBalance());//only able to update
                customerAccount.setAccountNumber(customerAccount.getAccountNumber());
                customerAccount.setAccountOpenDate(customerAccount.getAccountOpenDate());
                customerAccount.setAccountUpdatedDate(currentDate);
                customerAccount.setCustomer(customer);

                customerAccountRepository.save(customerAccount);
        return new BaseResponse("Customer account updated successfully", HttpStatus.OK.value(), HttpStatus.OK);

            }

    @Override
    public CustomerAccountDto findByAccNum(String customerId, String accountNumber){
     customerService.returnCustomer(customerId);
        Optional<CustomerAccount> customerAccount=customerAccountRepository.findByAccountNumber(accountNumber);
if(customerAccount.isEmpty()){
throw new EntityNotFound("Account number "+accountNumber+" not found");
}
else  {
    try{

        //Creating new directory for storing images
        String absolutePath = System.getProperty("user.dir");

        byte[] citizenshipFrontImg= FileUtils.readFileToByteArray(new File(absolutePath+customerAccount.get().getCustomer().getCitizenshipFrontImagePath().toString()));
        String citizenshipFrontEncodedImg= Base64.getEncoder().encodeToString(citizenshipFrontImg);
        customerAccount.get().getCustomer().setCitizenshipFrontEncodedImage(citizenshipFrontEncodedImg);

        byte[] citizenshipBackImg= FileUtils.readFileToByteArray(new File(absolutePath+customerAccount.get().getCustomer().getCitizenshipBackImagePath().toString()));
        String citizenshipBackEncodedImg= Base64.getEncoder().encodeToString(citizenshipBackImg);
        customerAccount.get().getCustomer().setCitizenshipBackEncodedImage(citizenshipBackEncodedImg);

        byte[] passportImg= FileUtils.readFileToByteArray(new File(absolutePath+customerAccount.get().getCustomer().getPassportImagePath().toString()));
        String passportEncodedImg= Base64.getEncoder().encodeToString(passportImg);
        customerAccount.get().getCustomer().setPassportEncodedImage(passportEncodedImg);

        byte[] profileImg= FileUtils.readFileToByteArray(new File(absolutePath+customerAccount.get().getCustomer().getProfileImagePath().toString()));
        String profileEncodedImg= Base64.getEncoder().encodeToString(profileImg);
        customerAccount.get().getCustomer().setProfileEncodedImage(profileEncodedImg);

    } catch (IOException e) {
        throw new Base64Conversion("Image retrieving error : "+e.toString());
    }
    CustomerAccountDto customerAccountDto=convertEntityToDto(customerAccount.get());
    return customerAccountDto;

        }
    }
    @Override
    public CustomerAccountDto findByAccNum(String accountNumber) {
        Optional<CustomerAccount> customerAccount=customerAccountRepository.findByAccNum(accountNumber);
        if(customerAccount.isEmpty()){
            throw new EntityNotFound("Account number "+accountNumber+" not found");
        }
        else  {
            try{

                //Creating new directory for storing images
                String absolutePath = System.getProperty("user.dir");

                byte[] citizenshipFrontImg= FileUtils.readFileToByteArray(new File(absolutePath+customerAccount.get().getCustomer().getCitizenshipFrontImagePath().toString()));
                String citizenshipFrontEncodedImg= Base64.getEncoder().encodeToString(citizenshipFrontImg);
                customerAccount.get().getCustomer().setCitizenshipFrontEncodedImage(citizenshipFrontEncodedImg);

                byte[] citizenshipBackImg= FileUtils.readFileToByteArray(new File(absolutePath+customerAccount.get().getCustomer().getCitizenshipBackImagePath().toString()));
                String citizenshipBackEncodedImg= Base64.getEncoder().encodeToString(citizenshipBackImg);
                customerAccount.get().getCustomer().setCitizenshipBackEncodedImage(citizenshipBackEncodedImg);

                byte[] passportImg= FileUtils.readFileToByteArray(new File(absolutePath+customerAccount.get().getCustomer().getPassportImagePath().toString()));
                String passportEncodedImg= Base64.getEncoder().encodeToString(passportImg);
                customerAccount.get().getCustomer().setPassportEncodedImage(passportEncodedImg);

                byte[] profileImg= FileUtils.readFileToByteArray(new File(absolutePath+customerAccount.get().getCustomer().getProfileImagePath().toString()));
                String profileEncodedImg= Base64.getEncoder().encodeToString(profileImg);
                customerAccount.get().getCustomer().setProfileEncodedImage(profileEncodedImg);

            } catch (IOException e) {
                throw new Base64Conversion("Image retrieving error : "+e.toString());
            }
            CustomerAccountDto customerAccountDto=convertEntityToDto(customerAccount.get());
            return customerAccountDto;

        }
    }

    @Override
    public List<CustomerAccountDto> findAccountByCustomerId(String customerId) {

//Directly convert to Customer Account to Customer Account Dto
        List<CustomerAccountDto> customerAccountDtoList = customerAccountRepository.findByCustomerId(customerId).stream().map(this::convertEntityToDtoForOther).collect(Collectors.toList());


            return customerAccountDtoList;

    }

    private CustomerAccount convertDtoToEntity(CustomerAccountDto customerAccountDto,Customer customer) {
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        String currentDate= DateFor.format(date);

        CustomerAccount customerAccount = new CustomerAccount();

        customerAccount.setCustomerAccountId(UUID.randomUUID().toString());
        customerAccount.setAccountType(customerAccountDto.getAccountType());
        customerAccount.setCurrency(customerAccountDto.getCurrency());
        customerAccount.setCurrentBalance(customerAccountDto.getCurrentBalance());
        customerAccount.setAccountNumber(this.accountNumber());
        customerAccount.setAccountOpenDate(currentDate);
        customerAccount.setAccountUpdatedDate(null);
customerAccount.setCustomer(customer);
        return customerAccount;
    }


    private CustomerAccountDto convertEntityToDto(CustomerAccount customerAccount) {
        CustomerAccountDto customerAccountDto = new CustomerAccountDto();

        customerAccountDto.setCustomerAccountId(customerAccount.getCustomerAccountId());
        customerAccountDto.setAccountType(customerAccount.getAccountType());
        customerAccountDto.setCurrency(customerAccount.getCurrency());
        customerAccountDto.setCurrentBalance(customerAccount.getCurrentBalance());
        customerAccountDto.setAccountNumber(customerAccount.getAccountNumber());
        customerAccountDto.setCustomerId(customerAccount.getCustomer().getCustomerId());
        customerAccountDto.setAccountOpenDate(customerAccount.getAccountOpenDate());
        customerAccountDto.setAccountUpdatedDate(customerAccount.getAccountUpdatedDate());

        customerAccountDto.setCustomer(customerAccount.getCustomer());
        return customerAccountDto;
    }
    private CustomerAccountDto convertEntityToDtoForOther(CustomerAccount customerAccount) {
        CustomerAccountDto customerAccountDto = new CustomerAccountDto();

        customerAccountDto.setCustomerAccountId(customerAccount.getCustomerAccountId());
        customerAccountDto.setAccountType(customerAccount.getAccountType());
        customerAccountDto.setCurrency(customerAccount.getCurrency());
        customerAccountDto.setCurrentBalance(customerAccount.getCurrentBalance());
        customerAccountDto.setAccountNumber(customerAccount.getAccountNumber());
        customerAccountDto.setCustomerId(customerAccount.getCustomer().getCustomerId());
        customerAccountDto.setAccountOpenDate(customerAccount.getAccountOpenDate());
        customerAccountDto.setAccountUpdatedDate(customerAccount.getAccountUpdatedDate());

        return customerAccountDto;
    }


    private String accountNumber() {
        long accountNumber = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
return  Long.toString(accountNumber);
    }
}
