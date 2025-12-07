package com.satwik.loan.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satwik.loan.dto.LoansDto;
import com.satwik.loan.entity.Loans;

public interface ILoansService {

  /**
   *
   * @param mobileNumber - Mobile Number of the Customer
   */
  void createLoan(String mobileNumber);

  /**
   *
   * @param mobileNumber - Input mobile Number
   *  @return Loan Details based on a given mobileNumber
   */
  LoansDto fetchLoan(String mobileNumber);

  /**
   *
   * @param loansDto - LoansDto Object
   * @return boolean indicating if the update of card details is successful or not
   */
  boolean updateLoan(LoansDto loansDto);

  /**
   *
   * @param mobileNumber - Input Mobile Number
   * @return boolean indicating if the delete of loan details is successful or not
   */
  boolean deleteLoan(String mobileNumber);

}