package com.salonService.app.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salonService.app.entity.Appointment;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long>{
	

}
