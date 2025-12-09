package com.satwik.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    name = "Customer",
    description = "Schema to hold Customer information"
)

public class CustomerDto {
  private Long customerId;

  @Schema(
      description = "Name of the customer", example = "Eazy Bytes"
  )
  @NotEmpty(message = "Name cannot be null or empty")
  @Size(min = 5, max = 30, message = "length of name should be btn 5 & 30")
  private String name;
  @Schema(
      description = "Email address of the customer", example = "tutor@eazybytes.com"
  )
  @NotEmpty(message = "Name cannot be null or empty")
  @Email(message = "Email is not in valid format")
  private String email;
  @Schema(
      description = "Mobile Number of the customer", example = "9345432123"
  )
  @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile Number must be 10 digits")
  private String mobileNumber;
}
