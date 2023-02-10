package com.salonService.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Payment;
import com.salonService.app.entity.ServiceCart;
import com.salonService.app.services.IAppointmentService;

class AppointmentServiceTest {
	@Autowired
	private IAppointmentService iAppointmentService;
	@Test
	void addAppointmentTest() throws Exception{
		Payment pay=new Payment(null, null, null);
		ServiceCart cart=new ServiceCart(null, null, null);
		assertNotNull(iAppointmentService.addAppointment(new Appointment(100,"testlocation",null,null,cart,pay,null)));
	}

}
