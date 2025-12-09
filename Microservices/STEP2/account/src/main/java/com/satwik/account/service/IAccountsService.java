package com.satwik.account.service;

import com.satwik.account.dto.CustomerAccountDto;
import com.satwik.account.dto.CustomerDto;

public interface IAccountsService {

  /**
   *
   * @param customerDto
   */
  void createAccount(CustomerDto customerDto);

  /**
   *
   * @param mobileNumber
   * @return
   */
  CustomerAccountDto fetchAccountDetails(String mobileNumber);

  /**
   *
   * @param customerAccountDto
   * @return
   */
  boolean updateAccount(CustomerAccountDto customerAccountDto);

  /**
   *
   * @param mobileNumber
   * @return
   */
  boolean deleteAccount(String mobileNumber);

}
