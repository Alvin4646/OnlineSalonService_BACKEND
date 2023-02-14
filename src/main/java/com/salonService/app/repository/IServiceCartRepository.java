package com.salonService.app.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salonService.app.entity.SalonService;
import com.salonService.app.entity.ServiceCart;

@Repository
public interface IServiceCartRepository extends JpaRepository<ServiceCart, Long>{
   
}
