
package com.salonService.app.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salonService.app.entity.Customer;



@Repository 
public interface ICustomerRepository extends JpaRepository <Customer, Integer>{

  
	
}

