package com.bitskraft.bankaccountmock.serviceImpl;


import com.bitskraft.bankaccountmock.customerexception.AlreadyExist;
import com.bitskraft.bankaccountmock.customerexception.Base64Conversion;
import com.bitskraft.bankaccountmock.customerexception.EntityNotFound;
import com.bitskraft.bankaccountmock.dto.*;
import com.bitskraft.bankaccountmock.dto.response.BaseResponse;
import com.bitskraft.bankaccountmock.entity.*;
import com.bitskraft.bankaccountmock.repository.CustomerRepository;
import com.bitskraft.bankaccountmock.service.AddressService;
import com.bitskraft.bankaccountmock.service.CustomerAccountService;
import com.bitskraft.bankaccountmock.service.CustomerService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressService addressService;
    @Lazy  //for  removing circular dependency
    @Autowired
    private CustomerAccountService customerAccountService;



    //Add customer
    @Override
    public BaseResponse addCustomer(CustomerDto customerDto) {
        boolean phoneExist = customerRepository.existsByPhone(customerDto.getPhone());
        boolean emailExist = customerRepository.existsByEmail(customerDto.getEmail());

        if (phoneExist) {
            throw new AlreadyExist("Phone number already exist");
        } else if (emailExist) {
            throw new AlreadyExist("Email already exist");

        } else {
            AddressDTO permanentAddressDTOData = this.customerPermAddressData(customerDto);
            Address permanentAddress = addressService.save(permanentAddressDTOData);

            AddressDTO temporaryAddressDTOData = this.customerTempAddressData(customerDto);
            Address temporaryAddress = addressService.save(temporaryAddressDTOData);


            // convert DTO to entity
            Customer customer = this.convertDtoToEntity(customerDto, permanentAddress, temporaryAddress);




            try {
                String folderName="images";

                //Creating new directory for storing images
                String absolutePath = System.getProperty("user.dir") + File.separator +folderName;
                Path path =Paths.get(absolutePath+ File.separator +customer.getCustomerId());
                Files.createDirectories(path );


//for saving image path to database
                String relativePath = "/"+folderName +"/" +customer.getCustomerId();
                FileOutputStream fos1 = new FileOutputStream(path.toString()+File.separator+"citizenshipFront");
                byte[] citizenFront = Base64.getMimeDecoder().decode(customer.getCitizenshipFrontEncodedImage());
                fos1.write(citizenFront);
                fos1.close();

                FileOutputStream fos2 = new FileOutputStream(path.toString()+File.separator+"citizenshipBack");
                byte[] citizenBack = Base64.getMimeDecoder().decode(customer.getCitizenshipBackEncodedImage());
                fos2.write(citizenBack);
                fos2.close();

                if(customer.getPassportEncodedImage()!=null){

                    FileOutputStream fos3 = new FileOutputStream(path.toString()+File.separator+"passport");
                byte[] passport = Base64.getMimeDecoder().decode(customer.getPassportEncodedImage());
                fos3.write(passport);
                fos3.close();
                }
                FileOutputStream fos4 = new FileOutputStream(path.toString()+File.separator+"profile");
                byte[] profile = Base64.getMimeDecoder().decode(customer.getProfileEncodedImage());
                fos4.write(profile);
                fos4.close();


                customer.setCitizenshipFrontImagePath(relativePath+"/"+"citizenshipFront");
                customer.setCitizenshipBackImagePath(relativePath+"/"+"citizenshipBack");
                if(customer.getPassportEncodedImage()!=null) {
                    customer.setPassportImagePath(relativePath + "/" + "passport");
                }
                    customer.setPassportImagePath(relativePath + "/" + "passport");

                customer.setProfileImagePath(relativePath+"/"+"profile");

            } catch (Exception e) {
                throw new Base64Conversion("Image uploaded error occurred: " + e.getMessage());

            }
            customerRepository.save(customer);
            return new BaseResponse("Customer added successfully", HttpStatus.CREATED.value(), HttpStatus.CREATED);

        }
    }


    //Getting single customer details
    @Override
    public CustomerDto getCustomer(String customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new EntityNotFound("Customer not found"));
            CustomerDto customerDto=this.convertEntityToDto(customer,customer.getPermanentAddress(),customer.getTemporaryAddress());
            return customerDto;
    }
    //update customer
    @Override
    public BaseResponse updateCustomer(String customerId, CustomerDto customerDto) {
        Customer customer=customerRepository.findById(customerId).orElseThrow(()->new EntityNotFound("Customer not found"));

        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        String currentDate= DateFor.format(date);


        AddressDTO permanentAddressDTOData=this.customerPermAddressData(customerDto);
        Address permanentAddress=addressService.update(customer.getPermanentAddress().getAddressId(),permanentAddressDTOData);


        AddressDTO temporaryAddressDTOData=this.customerTempAddressData(customerDto);
        Address temporaryAddress=addressService.update(customer.getTemporaryAddress().getAddressId(),temporaryAddressDTOData);

        Customer newCustomer=this.convertDtoToEntity(customerDto, permanentAddress, temporaryAddress);
customer.setCustomerId(customer.getCustomerId());//old id
            customer.setName(newCustomer.getName());
            customer.setDob(newCustomer.getDob());
            customer.setGender(newCustomer.getGender());
            customer.setPhone(newCustomer.getPhone());
            customer.setEmail(newCustomer.getEmail());
customer.setPermanentAddress(newCustomer.getPermanentAddress());
        customer.setTemporaryAddress(newCustomer.getTemporaryAddress());
            customer.setNationality(newCustomer.getNationality());
            customer.setFatherName(newCustomer.getFatherName());
            customer.setMotherName(newCustomer.getMotherName());
            customer.setGrandFatherName(newCustomer.getGrandFatherName());
            customer.setCitizenshipNumber(newCustomer.getCitizenshipNumber());
            customer.setPassportNumber(newCustomer.getPassportNumber());

            customer.setCifId(newCustomer.getCifId());
            customer.setBranch(newCustomer.getBranch());
            customer.setBranchCode(newCustomer.getBranchCode());
            customer.setCustomerAddedDate(newCustomer.getCustomerAddedDate());
            customer.setCustomerUpdatedDate(currentDate);

            customerRepository.save(customer);
        return new BaseResponse("Customer updated successfully",HttpStatus.OK.value(), HttpStatus.OK);

    }

    //delete customer
    @Override
    public BaseResponse deleteCustomerCustomerAccount(String customerId) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new EntityNotFound("Customer not found") );

            customerRepository.delete(customer);
        return new BaseResponse("Customer deleted successfully",HttpStatus.OK.value(), HttpStatus.OK);


    }


    //finding all customer list
    @Override
    public PaginationDto getAllCustomer(Integer pageNumber, Integer pageSize) {

        // convert all list entity to DTO
       Page<Customer> customersPageList=customerRepository.findAll(PageRequest.of(pageNumber,pageSize));
        List<CustomerDto> customerDtoPageList = customersPageList.getContent().stream().map(this::convertEntityToDtos).collect(Collectors.toList());

        if (customersPageList.isEmpty()) {
            throw new EntityNotFound("Customer not found on this page");
        } else {
            PaginationDto paginationDto=new PaginationDto();
            paginationDto.setCustomer(customerDtoPageList);
            paginationDto.setTotalCustomerNumber(customerRepository.count());
            paginationDto.setCurrentPageNumber(pageNumber);
            paginationDto.setCurrentPageSize(pageSize);
            paginationDto.setTotalPage(customerRepository.count()/pageSize);
            return paginationDto;

        }


    }



    @Override
    public Customer returnCustomer(String customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new EntityNotFound("Customer not found"));
        return customer;
    }

    @Override
    public CustomerCustomerAccountDto findByCifId(String cifId) {

        Optional<Customer> customer= customerRepository.findByCifId(cifId);
        if(customer.isEmpty()){
            throw new EntityNotFound("Cif id "+cifId+" not found");
        }
        else  {
            try{

                //Creating new directory for storing images
                String absolutePath = System.getProperty("user.dir");

                byte[] citizenshipFrontImg= FileUtils.readFileToByteArray(new File(absolutePath+customer.get().getCitizenshipFrontImagePath().toString()));
                String citizenshipFrontEncodedImg= Base64.getEncoder().encodeToString(citizenshipFrontImg);
                customer.get().setCitizenshipFrontEncodedImage(citizenshipFrontEncodedImg);

                byte[] citizenshipBackImg= FileUtils.readFileToByteArray(new File(absolutePath+customer.get().getCitizenshipBackImagePath().toString()));
                String citizenshipBackEncodedImg= Base64.getEncoder().encodeToString(citizenshipBackImg);
                customer.get().setCitizenshipBackEncodedImage(citizenshipBackEncodedImg);

                byte[] passportImg= FileUtils.readFileToByteArray(new File(absolutePath+customer.get().getPassportImagePath().toString()));
                String passportEncodedImg= Base64.getEncoder().encodeToString(passportImg);
                customer.get().setPassportEncodedImage(passportEncodedImg);

                byte[] profileImg= FileUtils.readFileToByteArray(new File(absolutePath+customer.get().getProfileImagePath().toString()));
                String profileEncodedImg= Base64.getEncoder().encodeToString(profileImg);
                customer.get().setProfileEncodedImage(profileEncodedImg);

            } catch (IOException e) {
                throw new Base64Conversion("Image retrieving error : "+e.toString());
            }
            CustomerDto customerDto=convertEntityToDto(customer.get(),customer.get().getPermanentAddress(), customer.get().getTemporaryAddress());
List<CustomerAccountDto> customerAccountDto= customerAccountService.findAccountByCustomerId(customer.get().getCustomerId());

CustomerCustomerAccountDto customerCustomerAccountDto=new CustomerCustomerAccountDto();
customerCustomerAccountDto.setCustomerDto(customerDto);
    customerCustomerAccountDto.setCustomerAccountDtoList(customerAccountDto);

return customerCustomerAccountDto;

        }

    }
    public Customer convertDtoToEntity(CustomerDto customerDto,Address permanentAddress,Address temporaryAddress){
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        String currentDate= DateFor.format(date);

        Customer customer=new Customer();

        customer.setCustomerId(UUID.randomUUID().toString());
        customer.setName(customerDto.getName());
        customer.setDob(customerDto.getDob());
        customer.setGender(customerDto.getGender());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());
        customer.setPermanentAddress(permanentAddress);
        customer.setTemporaryAddress(temporaryAddress);
        customer.setNationality(customerDto.getNationality());
        customer.setFatherName(customerDto.getFatherName());
        customer.setMotherName(customerDto.getMotherName());
        customer.setGrandFatherName(customerDto.getGrandFatherName());
        customer.setCitizenshipNumber(customerDto.getCitizenshipNumber());
        customer.setCitizenshipFrontImageName(customerDto.getCitizenshipFrontImageName());
        customer.setCitizenshipFrontEncodedImage(customerDto.getCitizenshipFrontEncodedImage());
        customer.setCitizenshipBackImageName(customerDto.getCitizenshipBackImageName());
        customer.setCitizenshipBackEncodedImage(customerDto.getCitizenshipBackEncodedImage());
        customer.setPassportNumber(customerDto.getPassportNumber());
        customer.setPassportImageName(customerDto.getPassportImageName());
        customer.setPassportEncodedImage(customerDto.getPassportEncodedImage());
        customer.setProfileImageName(customerDto.getProfileImageName());
        customer.setProfileEncodedImage(customerDto.getProfileEncodedImage());
        customer.setCifId("R"+this.cifId());
        customer.setBranch(customerDto.getBranch());
        customer.setBranchCode(customerDto.getBranchCode());
        customer.setCustomerAddedDate(currentDate);
        customer.setCustomerUpdatedDate(null);



        return  customer;

    }
    public CustomerDto convertEntityToDto(Customer customer,Address permanentAddress,Address temporaryAddress){
        CustomerDto customerDto=new CustomerDto();

        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setName(customer.getName());
        customerDto.setDob(customer.getDob());
        customerDto.setGender(customer.getGender());
        customerDto.setPhone(customer.getPhone());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPermanentCountryId(permanentAddress.getCountry().getId());
        customerDto.setPermanentStatesId(permanentAddress.getStates().getId());
        customerDto.setPermanentDistrictsId(permanentAddress.getDistricts().getId());
        customerDto.setPermanentMunicipalitiesId(permanentAddress.getMunicipalities().getId());
        customerDto.setPermanentAddressId(permanentAddress.getAddressId());
customerDto.setPermanentAddress(permanentAddress);
        customerDto.setTemporaryCountryId(temporaryAddress.getCountry().getId());
        customerDto.setTemporaryStatesId(temporaryAddress.getStates().getId());
        customerDto.setTemporaryDistrictsId(temporaryAddress.getDistricts().getId());
        customerDto.setTemporaryMunicipalitiesId(temporaryAddress.getMunicipalities().getId());
        customerDto.setTemporaryAddressId(temporaryAddress.getAddressId());

        customerDto.setTemporaryAddress(temporaryAddress);

        customerDto.setNationality(customer.getNationality());
        customerDto.setFatherName(customer.getFatherName());
        customerDto.setMotherName(customer.getMotherName());
        customerDto.setGrandFatherName(customer.getGrandFatherName());
        customerDto.setCitizenshipNumber(customer.getCitizenshipNumber());
        customerDto.setCitizenshipFrontImagePath(customer.getCitizenshipFrontImagePath());
        customerDto.setCitizenshipFrontEncodedImage((customer.getCitizenshipFrontEncodedImage()));
        customerDto.setCitizenshipBackImagePath(customer.getCitizenshipBackImagePath());
        customerDto.setCitizenshipBackEncodedImage(customer.getCitizenshipBackEncodedImage());
        customerDto.setPassportNumber(customer.getPassportNumber());
        customerDto.setPassportImagePath(customer.getPassportImagePath());
        customerDto.setPassportEncodedImage(customer.getPassportEncodedImage());
customerDto.setProfileImagePath(customer.getProfileImagePath());
customerDto.setProfileEncodedImage(customer.getProfileEncodedImage());
        customerDto.setCifId(customer.getCifId());
        customerDto.setBranch(customer.getBranch());
        customerDto.setBranchCode(customer.getBranchCode());
        customerDto.setCustomerAddedDate(customer.getCustomerAddedDate());
        customerDto.setCustomerUpdatedDate(customer.getCustomerUpdatedDate());

        return  customerDto;
    }
    //for returning all customer list
    private CustomerDto convertEntityToDtos(Customer customer){

        CustomerDto customerDto=this.convertEntityToDto(customer,customer.getPermanentAddress(),customer.getTemporaryAddress());

        return customerDto;
    }
    //for getting AddressDTO data from CustomerDTO
    private AddressDTO customerPermAddressData(CustomerDto customerDto){
        AddressDTO permanentAddressDTOData=new AddressDTO();

        permanentAddressDTOData.setCountry(customerDto.getPermanentCountryId());
        permanentAddressDTOData.setStates(customerDto.getPermanentStatesId());
        permanentAddressDTOData.setDistricts(customerDto.getPermanentDistrictsId());
        permanentAddressDTOData.setMunicipalities(customerDto.getPermanentMunicipalitiesId());
return  permanentAddressDTOData;
    }
    private AddressDTO customerTempAddressData(CustomerDto customerDto){
        AddressDTO temporaryAddressDTOData=new AddressDTO();

        temporaryAddressDTOData.setCountry(customerDto.getTemporaryCountryId());
        temporaryAddressDTOData.setStates(customerDto.getTemporaryStatesId());
        temporaryAddressDTOData.setDistricts(customerDto.getTemporaryDistrictsId());
        temporaryAddressDTOData.setMunicipalities(customerDto.getTemporaryMunicipalitiesId());
return temporaryAddressDTOData;
    }

    private List<String> imageNameList(Customer customer) {
        List<String> list=new ArrayList<>();
        list.add(customer.getCitizenshipFrontImageName());
        list.add(customer.getCitizenshipBackImageName());
        list.add(customer.getPassportImageName());
        list.add(customer.getProfileImageName());

        return list;
    }
    private List<String> imageEncodedList(Customer customer) {
        List<String> list=new ArrayList<>();
        list.add(customer.getCitizenshipFrontEncodedImage());
        list.add(customer.getCitizenshipBackEncodedImage());
        list.add(customer.getPassportEncodedImage());
        list.add(customer.getProfileEncodedImage());

        return list;
    }
    private String cifId() {
        long customerInformationId = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        return  Long.toString(customerInformationId);
    }
}
