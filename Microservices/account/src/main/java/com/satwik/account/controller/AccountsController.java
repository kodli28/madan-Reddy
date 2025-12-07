package com.satwik.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.satwik.account.constants.AccountsConstants;
import com.satwik.account.dto.CustomerAccountDto;
import com.satwik.account.dto.CustomerDto;
import com.satwik.account.dto.ErrorResponseDto;
import com.satwik.account.dto.ResponseDto;
import com.satwik.account.service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

/**
 * @Author Satwik K S
 */
@Tag(
    name = "CRUD REST APIS for Accounts in Eazy Bank ",
    description = "CRUD REST APIS in EAZY BANK to FETCH,CREATE UPDATE and DELETE account details "
)
@RestController
@RequestMapping(path = "/account", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

  private IAccountsService iAccountsService;

  @GetMapping("/health")
  public String healthCheck() {
    return "account service is healthy";
  }

  @Operation(
      summary = "Create Account REST API",
      description = "REST API to create new Customer &  Account inside EazyBank"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "201",
          description = "HTTP Status CREATED"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP Status Internal Server Error",
          content = @Content(
              schema = @Schema(implementation = ErrorResponseDto.class)
          )
      )
  }
  )
  @PostMapping("/createaccount")
  public ResponseEntity<ResponseDto> createAccount(
      @Valid
      @RequestBody CustomerDto customerDto) {
    iAccountsService.createAccount(customerDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
  }

  ;
  @Operation(
      summary = "Fetch Account Details REST API",
      description = "REST API to fetch Customer &  Account details based on a mobile number"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "HTTP Status OK"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP Status Internal Server Error",
          content = @Content(
              schema = @Schema(implementation = ErrorResponseDto.class)
          )
      )
  }
  )
  @GetMapping("/getaccount")
  public ResponseEntity<CustomerAccountDto> fetchAccountDetails(
       @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile Number must be 10 digits")
      @RequestParam String mobileNumber) {
    CustomerAccountDto customerAccountDto = iAccountsService.fetchAccountDetails(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK).body(customerAccountDto);
  }

  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "HTTP Status OK"
      ),
      @ApiResponse(
          responseCode = "417",
          description = "Expectation Failed"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP Status Internal Server Error",
          content = @Content(
              schema = @Schema(implementation = ErrorResponseDto.class)
          )
      )
  }
  )
  @PutMapping("/updateaccount")
  public ResponseEntity<ResponseDto> updateAccount(@Valid
      @RequestBody CustomerAccountDto customerAccountDto){
    boolean isUpdated = iAccountsService.updateAccount(customerAccountDto);
    if(isUpdated){
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }else{
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
    }
  }


  @Operation(
      summary = "Delete Account & Customer Details REST API",
      description = "REST API to delete Customer &  Account details based on a mobile number"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "HTTP Status OK"
      ),
      @ApiResponse(
          responseCode = "417",
          description = "Expectation Failed"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP Status Internal Server Error",
          content = @Content(
              schema = @Schema(implementation = ErrorResponseDto.class)
          )
      )
  }
  )
  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteAccount(
      @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile Number must be 10 digits")
      @RequestParam String mobileNumber){
    boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
    if(isDeleted){
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }else{
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
    }
  }


}
