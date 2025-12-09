package com.satwik.account.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.satwik.account.constants.AccountsConstants;
import com.satwik.account.dto.AccountsDto;
import com.satwik.account.dto.CustomerAccountDto;
import com.satwik.account.dto.CustomerDto;
import com.satwik.account.entity.Accounts;
import com.satwik.account.entity.Customer;
import com.satwik.account.exception.CustomerAlreadyExistsException;
import com.satwik.account.exception.ResourceNotFoundException;
import com.satwik.account.mapper.AccountsMapper;
import com.satwik.account.mapper.CustomerMapper;
import com.satwik.account.repository.AccountsRepository;
import com.satwik.account.repository.CustomerRepository;
import com.satwik.account.service.IAccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

  private AccountsRepository accountsRepository;
  private CustomerRepository customerRepository;
  /**
   * @param customerDto
   */
  @Override
  public void createAccount(CustomerDto customerDto) {
    Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
    Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
    if(optionalCustomer.isPresent()){
      throw new CustomerAlreadyExistsException("Customer already registered with given mobile Number"+ customerDto.getMobileNumber());
    }
    Customer savedCustomer = customerRepository.save(customer);
    Accounts newAccount = accountsRepository.save(createNewAccount(savedCustomer));
  }

  /**
   *
   * @param customer
   * @return
   */
  private Accounts createNewAccount(Customer customer){
    Accounts accounts = new Accounts();
    accounts.setCustomerId(customer.getCustomerId());
    long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
    accounts.setAccountNumber(randomAccNumber);
    accounts.setAccountType(AccountsConstants.SAVINGS);
    accounts.setBranchAddress(AccountsConstants.ADDRESS);
    return accounts;
  }



  /**
   * @param mobileNumber
   * @return
   */
  @Override
  public CustomerAccountDto fetchAccountDetails(String mobileNumber) {
    Customer customer =customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("customer", "mobileNumber", mobileNumber));
    Accounts accounts =accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
    CustomerAccountDto customerAccountDto = new CustomerAccountDto();
    customerAccountDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
    customerAccountDto.setCustomerDto(CustomerMapper.mapToCustomerDto(customer, new CustomerDto()));
    return customerAccountDto;
  }

  /**
   *
   * @param customerAccountDto
   * @return
   */
  @Override
  public boolean updateAccount(CustomerAccountDto customerAccountDto) {
    boolean isUpdated = false;

    AccountsDto accountsDto = customerAccountDto.getAccountsDto();
    Accounts accounts = null;
    if (accountsDto != null) {
      accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
          () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString()));
//write a logic to match account number matches with customer Name and phoneNumber, only then set the accountNumber and mapper
    AccountsMapper.mapToAccounts(accountsDto, accounts);
    accounts = accountsRepository.save(accounts);
    Long customerId = accounts.getCustomerId();
    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));
    Customer customer1 = CustomerMapper.mapToCustomer(customerAccountDto.getCustomerDto(), customer);
    customerRepository.save(customer1);
   isUpdated=true;
    }
    return isUpdated;
  }

  /**
   * @param mobileNumber
   * @return
   */
  @Override
  public boolean deleteAccount(String mobileNumber) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
    accountsRepository.deleteByCustomerId(customer.getCustomerId());
    customerRepository.deleteById(customer.getCustomerId());
    return true;
  }



}
