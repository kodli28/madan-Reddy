package com.satwik.card.service;

import com.satwik.card.dto.CardsDto;

public interface ICardsService {

  /**
   *
   * @param mobileNumber
   */
  void createCard(String mobileNumber);

  /**
   *
   * @param mobileNumber
   * @return
   */
  CardsDto fetchCards(String mobileNumber);

  /**
   *
   * @param cardsDto
   * @return
   */
  boolean updateCard(CardsDto cardsDto);

  /**
   *
   * @param mobileNumber
   * @return
   */
  boolean deleteCard(String mobileNumber);
}
