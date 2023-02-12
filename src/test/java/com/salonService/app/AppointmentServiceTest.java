package com.salonService.app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.constraints.AssertTrue;

import org.hibernate.query.criteria.internal.expression.SearchedCaseExpression.WhenClause;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Customer;
import com.salonService.app.entity.Payment;
import com.salonService.app.entity.ServiceCart;
import com.salonService.app.exception.AppointmentException;
import com.salonService.app.exception.DuplicateAppointmentException;
import com.salonService.app.repository.IAppointmentRepository;
import com.salonService.app.repository.ICustomerRepository;
import com.salonService.app.services.IAppointmentService;

@SpringBootTest
class AppointmentServiceTest {
//	public Appointment addAppointment(Appointment appointment)throws DuplicateAppointmentException;
//	public String removeAppointment(long id)throws AppointmentException;
//
//	public String updateAppointment(long id, Appointment appointments)throws AppointmentException;
//
//	public Appointment getAppointmentById(Long id) throws AppointmentException;
//
//	public List<Appointment> getAllAppointments() ;
//
//	public List<Appointment> getOpenAppointments() ;
//	
//	public Appointment addAppointmentToCustomer(Appointment appointment,int custId)throws Exception ;
//	
//	public Appointment updateAppointmentDate(long id,LocalDate preferredDate)throws AppointmentException;
//	
//	public List<Appointment> getAppointmentByDate(LocalDate date);
	@Autowired
	private IAppointmentService iAppointmentService;

	@MockBean
	private IAppointmentRepository repository;
	@MockBean
	private ICustomerRepository iCustomerRepository;

	Payment pay = new Payment(null, null, null);
	ServiceCart cart = new ServiceCart(null, null, null);
	LocalDate date = LocalDate.parse("2023-02-10");
	Appointment appointment = new Appointment(100, "testlocation", date, null, cart, pay, null);

	@Test
	void addAppointmentTest() throws Exception{
		
		when(repository.save(appointment)).thenReturn(appointment);
		assertEquals(appointment, iAppointmentService.addAppointment(appointment));
		
		//assertNotNull(iAppointmentService.addAppointment(appointment));
	}

	@Test
	void addAppointmentToCustomerTest() throws Exception {
		long id = 100;
		Customer cust=new Customer(100, "alvin", "alvin@email.com", "pwd", 898, date, null, cart, "address");
		when(iCustomerRepository.findById(100)).thenReturn(Optional.of(cust));
		when(repository.save(appointment)).thenReturn(appointment);
		//Customer cust1=new Customer(100, "alvin", "alvin@email.com", "pwd", 898, date,List.of(appointment), cart, "address");
		when(iCustomerRepository.save(cust)).thenReturn(cust);
		iAppointmentService.addAppointmentToCustomer(appointment, 100);
		assertTrue(cust.getAppointments().contains(appointment)); 
	}

	@Test
	void getAppointmentByIdTest() throws Exception {
		long id = 100;
		
		when(repository.findById(id)).thenReturn(Optional.of(appointment));
		
		assertEquals(appointment, iAppointmentService.getAppointmentById(id));
	}
 
	@Test
	void getAppointmentByIdExceptionMessageTest() {

		String msg = null;
		try {
			iAppointmentService.getAppointmentById((long) 100);
		} catch (AppointmentException e) {
			msg = e.getMessage();
		}

		assertEquals("No Appointment with this id " + 100 + " found", msg);
	}

	@Test
	void updateAppointmentTest() throws AppointmentException {
		long id = 100;
		Payment pay = new Payment(null, null, null);
		ServiceCart cart = new ServiceCart(null, null, null);
		LocalDate date = LocalDate.parse("2023-02-10");
		Appointment updateAppointment = new Appointment(100, "testlocations", date, null, cart, pay, null);

		when(repository.findById(id)).thenReturn(Optional.of(appointment));
		when(repository.save(updateAppointment)).thenReturn(updateAppointment);
		assertEquals(updateAppointment, iAppointmentService.updateAppointment(id, updateAppointment));
		// iAppointmentService.updateAppointment(long id, Appointment appointments);
	}

	@Test
	void updateAppointmentDateTest() throws AppointmentException {
		long id = 100;
		Payment pay = new Payment(null, null, null);
		ServiceCart cart = new ServiceCart(null, null, null);
		LocalDate date = LocalDate.parse("2023-02-11");
		Appointment updateAppointment = new Appointment(id, "testlocation", date, null, cart, pay, null);

		when(repository.findById(id)).thenReturn(Optional.of(appointment));
		when(repository.save(updateAppointment)).thenReturn(updateAppointment);
		assertEquals(updateAppointment, iAppointmentService.updateAppointment(id, updateAppointment));
		// iAppointmentService.updateAppointment(long id, Appointment appointments);
	}

	@Test
	void getAllAppointments() {
		assertNotNull(iAppointmentService.getAllAppointments());
	}

	@Test
	void getAppointmentByDateTest() {
		LocalDate dateToFind = LocalDate.parse("2023-02-10");
		assertNotNull(iAppointmentService.getAppointmentByDate(dateToFind));
	}

	@Test
	void getOpenAppointmentsTest() {
		assertNotNull(iAppointmentService.getOpenAppointments());
	}
}
