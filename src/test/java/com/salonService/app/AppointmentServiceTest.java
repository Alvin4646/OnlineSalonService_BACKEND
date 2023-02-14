package com.salonService.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Appointment.AppointmentStatus;
import com.salonService.app.entity.Customer;
import com.salonService.app.entity.Payment;
import com.salonService.app.entity.SalonService;
import com.salonService.app.entity.ServiceCart;
import com.salonService.app.exception.AppointmentException;
import com.salonService.app.repository.IAppointmentRepository;
import com.salonService.app.repository.ICustomerRepository;
import com.salonService.app.services.IAppointmentService;

@SpringBootTest
class AppointmentServiceTest {

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
		List<Appointment> app = new ArrayList<>();
		Customer cust = new Customer(100, "alvin", "alvin@email.com", "pwd", "898", date, app, cart, "address");
		cust.getAppointments().add(appointment);
		when(iCustomerRepository.findById(100)).thenReturn(Optional.of(cust));
		when(repository.save(appointment)).thenReturn(appointment);
		Appointment result = iAppointmentService.addAppointmentToCustomer(appointment, 100);
		assertNotNull(result);
		assertEquals(appointment, result);
		assertTrue(cust.getAppointments().contains(appointment));
	}

	@Test
	void addAppointmentToCustomerExceptionTest() throws Exception {
		String msg = null;
		when(iCustomerRepository.findById(300)).thenReturn(Optional.empty());
		try {
			iAppointmentService.addAppointmentToCustomer(appointment, 300);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertEquals("No customer with this id " + 300 + " found", msg);
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
		LocalDate date = LocalDate.parse("2023-02-10");
		Appointment updateAppointment = new Appointment(100, "testlocations", date, null, null, null, null);

		when(repository.findById(id)).thenReturn(Optional.of(appointment));
		when(repository.save(updateAppointment)).thenReturn(updateAppointment);
		assertEquals(updateAppointment, iAppointmentService.updateAppointment(id, updateAppointment));

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
	void getAllAppointments() throws AppointmentException {
		List<Appointment> app = new ArrayList<>();
		app.add(appointment);
		when(repository.findAll()).thenReturn(app);
		assertEquals(app, iAppointmentService.getAllAppointments());
	}

	@Test
	void getAllAppointmentsExceptionTest() {
		String msg = null;
		List<Appointment> app = new ArrayList<>();
		when(repository.findAll()).thenReturn(app);
		try {
			iAppointmentService.getAllAppointments();
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertEquals("No appointments found!", msg);
	}

	@Test
	void getAppointmentByDateTest() throws AppointmentException {
		LocalDate date = LocalDate.parse("2023-02-10");
		appointment.setPreferredDate(date);
		List<Appointment> app = new ArrayList<Appointment>();
		app.add(appointment);
		when(repository.findByPreferredDate(date)).thenReturn(app);
		assertNotNull(iAppointmentService.getAppointmentByDate(date));
	}

	@Test
	void getAppointmentsByDateExceptionTest() {
		String msg = null;
		LocalDate date = LocalDate.parse("2023-02-11");
		List<Appointment> app = new ArrayList<>();
		when(repository.findByPreferredDate(date)).thenReturn(app);
		try {
			iAppointmentService.getAppointmentByDate(date);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertEquals("No appointment with this date " + date + " found", msg);
	}

	@Test
	void getOpenAppointmentsTest() throws AppointmentException {
		List<Appointment> app = new ArrayList<>();
		app.add(appointment);
		when(repository.findByAppointmentStatus(AppointmentStatus.OPEN)).thenReturn(app);
		assertNotNull(iAppointmentService.getOpenAppointments());
	}

	@Test
	void getOpenAppointmentsExceptionTest() {
		String msg = null;
		List<Appointment> app = new ArrayList<>();
		when(repository.findByAppointmentStatus(AppointmentStatus.OPEN)).thenReturn(app);
		try {
			iAppointmentService.getOpenAppointments();
		} catch (Exception e) {
			msg = e.getMessage();
		}

		assertEquals("No open appointments found !", msg);
	}

	@Test
	public void testRemoveAppointmentByid() throws AppointmentException {
		int cid = 1;
		long aid = 100;
		Customer customer = new Customer();
		customer.setUserId(cid);
		List<Appointment> appointments = new ArrayList<Appointment>();
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(aid);
		appointments.add(appointment);
		customer.setAppointments(appointments);
		when(iCustomerRepository.findById(1)).thenReturn(Optional.of(customer));
		when(iCustomerRepository.findById(cid)).thenReturn(Optional.of(customer));
		Appointment appointmentRemoved = iAppointmentService.removeAppointmentByid(cid, aid);
		assertEquals(aid, appointmentRemoved.getAppointmentId());
		verify(iCustomerRepository, times(1)).save(customer);
	}

	@Test
	public void testRemoveAppointmentByid_failure() throws AppointmentException {
		int cid = 1;
		long aid = 100;
		String msg = null;
		try {
			when(iCustomerRepository.findById(cid)).thenReturn(java.util.Optional.empty());
			iAppointmentService.removeAppointmentByid(cid, aid);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertEquals("Unable to delete no appointment with id " + aid + " found", msg);
	}

	@Test
	public void testGetServiceList() throws AppointmentException {
		long appointmentId = 1L;

		// Create a test appointment
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(appointmentId);
		ServiceCart cart = new ServiceCart();
		List<SalonService> services = new ArrayList<>();
		SalonService service1 = new SalonService();
		service1.setServiceId(1L);
		service1.setSeviceName("Service 1");
		services.add(service1);
		cart.setServiceList(services);
		appointment.setCart(cart);

		// Mock the repository method to return the test appointment
		when(repository.findById(appointmentId)).thenReturn(Optional.of(appointment));

		// Call the service method
		List<SalonService> result = iAppointmentService.getServiceList(appointmentId);

		// Verify the result
		assertEquals(services, result);
	}

	@Test
	public void testGetServiceListException() throws AppointmentException {
		long appointmentId = 1L;

		Appointment appointment = new Appointment();
		appointment.setAppointmentId(appointmentId);
		when(repository.findById(appointmentId)).thenReturn(Optional.of(appointment));
		String msg = null;
		try {
			iAppointmentService.getServiceList(appointmentId);
		} catch (Exception e) {
			msg = e.getMessage();
		}

		assertEquals("No cart found for appointment", msg);
	}

	@Test
	public void testGetServiceListEmptyException() throws AppointmentException {
		long appointmentId = 1L;

		// Create a test appointment with an empty cart
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(appointmentId);
		ServiceCart cart = new ServiceCart();
		cart.setServiceList(new ArrayList<>());
		appointment.setCart(cart);
		when(repository.findById(appointmentId)).thenReturn(Optional.of(appointment));
		String msg = null;
		try {
			iAppointmentService.getServiceList(appointmentId);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertEquals("No services found", msg);
	}

}
