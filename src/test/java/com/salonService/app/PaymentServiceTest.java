package com.salonService.app;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
//import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Payment;
import com.salonService.app.entity.Payment.ModeOfPayment;
import com.salonService.app.entity.Payment.PaymentStatus;
import com.salonService.app.exception.PaymentException;
import com.salonService.app.repository.IAppointmentRepository;
import com.salonService.app.repository.IPaymentRepository;
import com.salonService.app.services.IPaymentService;
import com.salonService.app.services.IPaymentServiceImpl;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@SpringBootTest

public class PaymentServiceTest {

	@Autowired
	private IPaymentServiceImpl paymentService;
	@Autowired
	private IPaymentService iPaymentService;

	@MockBean
	private IPaymentRepository paymentRepository;
	@Mock
	private IAppointmentRepository repository;

	Payment payment = new Payment(1L, ModeOfPayment.CARD, PaymentStatus.PAID);
	
	Appointment appointment = new Appointment();

	@Test
	@DisplayName("Test for adding Payment")
	public void addPaymentTest() {
		payment.setPaymentId(1L);
		payment.setStatus(PaymentStatus.PAID);
		payment.setType(ModeOfPayment.CARD);
		Mockito.when(paymentRepository.save(payment)).thenReturn(payment);
		assertEquals(payment, paymentService.addPayment(payment));
	}

	public void testGetPaymentByIdThrowsPaymentException() {
		long paymentId = 123456L;
		try {
			iPaymentService.getPaymentById(paymentId); 
		} catch (PaymentException e) {
			assertEquals("Paymantwith id" + paymentId + "is not found", e.getMessage());
		}
	}

	@Test
	@DisplayName("Test for Deleteing Payment by Id")
	public void deletePaymentTest() throws PaymentException {
		long appointmentId = 1L;
		// Mockito.when(paymentRepository.deleteById(appointmentId).thenReturn(payment);
		paymentService.deletePayment(appointmentId);
		Mockito.when(paymentRepository.findById(1L)).thenReturn(java.util.Optional.of(payment));

		Mockito.verify(paymentRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testDeletePaymentThrowsPaymentException() {
		long paymentId = 123456L;
		try {
			iPaymentService.deletePayment(paymentId);
		} catch (PaymentException e) {
			assertEquals("payment with id" + paymentId + "is not found", e.getMessage());
		}
	}

	@Test
	@DisplayName("Test for payment details by id")
	public void getPaymentDetailsTest() throws PaymentException {

		Mockito.when(paymentRepository.findById(100L)).thenReturn(java.util.Optional.ofNullable(payment));
      
	}
	@Test
	public void getPaymentExceptionTest()throws PaymentException{
		String msg=null;
		try {
			iPaymentService.getPaymentById(100);
			
		}catch (Exception e) {
			msg=e.getMessage();
		}
		assertEquals("Payment with id" + 100 + " was not found", msg);
	}

	@Test
	@DisplayName("Test for getting all payments")
	public void getAllPaymentDetails() throws PaymentException {
		Mockito.when(paymentRepository.findAll())
				.thenReturn(Stream.of(new Payment(), new Payment()).collect(Collectors.toList()));

		assertEquals(2, paymentService.getAllPaymentDetails().size());
		verify(paymentRepository, times(1)).findAll();

	}

	@Test
	public void testGetAllPaymentDetailsExceptionThrowsException() {
		try {
			iPaymentService.getAllPaymentDetails();
		} catch (PaymentException e) {
			assertEquals("No Payment details found in database", e.getMessage());
		}
	}

	@Test
	@DisplayName("Test for Updating Payment")
	public void updatePaymentTest() {
		java.util.Optional<Payment> payment = paymentRepository.findById(1L);
		if (payment.isPresent()) {
			payment.get().setType(ModeOfPayment.CARD);
			Mockito.doReturn(paymentRepository.save(payment.get()));

		}
		java.util.Optional<Payment> updatedpayment = paymentRepository.findById(1L);
		if (updatedpayment.isPresent()) {
			assertThat(updatedpayment.get().getType().equals("CARD"));
		}
	}

	@Test
	public void testUpdatePaymentThrowsPaymentException() {
		long paymentId = 123456L;
		Payment payment = new Payment();
		try {
			iPaymentService.updatePayment(paymentId, payment);

		} catch (PaymentException e) {
			assertEquals("Payment with id"+paymentId+"is not found,so cant update", e.getMessage());
		}
	}
	  @Test
	  public void testAddPaymentToAppointment() throws PaymentException {
	    // Arrange
	    long id = 1L;
	    LocalDate date = LocalDate.parse("2023-02-10");
	    Appointment appointment = new Appointment(100, "testlocation", date, null, null, null, null);
	    Payment payment = new Payment(null, null, null);
	    java.util.Optional<Appointment> optionalAppointment = java.util.Optional.of(appointment) ;
	   
	    when(repository.findById(id)).thenReturn(optionalAppointment);
	    when(repository.save(appointment)).thenReturn(appointment);
	   
	    // Act
	    Payment result = paymentService.addPaymentToAppointment(payment, id);
	   
	    // Assert
	    assertNotNull(result);
	    assertSame(payment, result);
	    assertEquals(payment, appointment.getPayment());
	  }
	 
	  @Test
	  public void testAddPaymentToAppointmentWithNonexistentAppointment() throws PaymentException {
	    // Arrange
	    long id = 1L;
	    Payment payment = new Payment(null, null, null);
	    java.util.Optional<Appointment> optionalAppointment = java.util.Optional.empty();
	   
	    when(repository.findById(id)).thenReturn(optionalAppointment);
	   
	    // Act
	    paymentService.addPaymentToAppointment(payment, id);
	   
	    // Assert (exception expected)
	  }
}