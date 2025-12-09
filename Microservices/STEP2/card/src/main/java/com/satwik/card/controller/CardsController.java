package com.satwik.card.controller;

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

import com.satwik.card.constants.CardsConstants;
import com.satwik.card.dto.CardsDto;
import com.satwik.card.dto.ErrorResponseDto;
import com.satwik.card.dto.ResponseDto;
import com.satwik.card.service.ICardsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@Tag(
    name = "CRUD REST APIs for Cards in EazyBank",
    description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE card details"
)
@RestController
@RequestMapping(path = "/card", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CardsController {

  private ICardsService iCardsService;

  @RequestMapping("/health")
  public String healthCheck(){
    return "card service is healthy";
  }

  @Operation(
      summary = "Create Card REST API",
      description = "REST API to create new Card inside EazyBank"
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
  @PostMapping("/createcard")
  public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam
  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
  String mobileNumber){
    iCardsService.createCard(mobileNumber);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
  }

  @Operation(
      summary = "Fetch Card Details REST API",
      description = "REST API to fetch card details based on a mobile number"
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
  })
  @GetMapping("/fetchcard")
  public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam
  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
  String mobileNumber) {
    CardsDto cardsDto = iCardsService.fetchCards(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
  }


  @PutMapping("/updatecard")
  public ResponseEntity<ResponseDto> updateCardDetails(@Valid  @RequestBody CardsDto cardsDto) {
    boolean isUpdated = iCardsService.updateCard(cardsDto);
    if(isUpdated) {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
    }else{
      return ResponseEntity
          .status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
    }
  }


  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam
  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
  String mobileNumber) {
    boolean isDeleted = iCardsService.deleteCard(mobileNumber);
    if(isDeleted) {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
    }else{
      return ResponseEntity
          .status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
    }
  }

}
