package com.salonService.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salonService.app.entity.Payment;


@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long >{
	

	

}
