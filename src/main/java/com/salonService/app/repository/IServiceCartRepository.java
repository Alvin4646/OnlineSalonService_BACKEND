package com.salonService.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salonService.app.entity.ServiceCart;



@Repository
public interface IServiceCartRepository extends JpaRepository<ServiceCart, Long>{

}
