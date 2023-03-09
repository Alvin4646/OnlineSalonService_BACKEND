package com.salonService.app.repository;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Appointment.AppointmentStatus;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long>{

	List<Appointment> findByAppointmentStatus(AppointmentStatus status);

	List<Appointment> findByPreferredDate(LocalDate date);

	

}
