package com.satwik.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    name = "Customer and Account information",
    description = "Schema to hold Customer and Account information"
)

public class CustomerAccountDto {
  @Schema(
      name = "Account",
      description = "Account details of the Customer"
  )
  private AccountsDto accountsDto;
  @Schema(
      name = "Customer",
      description = "Customer details of Eazy Bank"
  )
  private CustomerDto customerDto;
}
