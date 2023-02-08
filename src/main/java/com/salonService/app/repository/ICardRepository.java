package com.salonService.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.salonService.app.entity.Card;


public interface ICardRepository extends JpaRepository<Card, Long > {

}