package com.salonService.app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salonService.app.entity.SalonService;


@Repository
public interface ISalonRepository extends JpaRepository<SalonService, Long>{
public List<SalonService> findByServicePrice(String servicePrice);
    
    public List<SalonService> findBySeviceName(String seviceName);

	public List<SalonService> findByServiceDuration(String serviceDuration);
}
