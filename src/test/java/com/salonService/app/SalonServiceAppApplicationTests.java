package com.salonService.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.salonService.app.entity.SalonService;
import com.salonService.app.exception.SalonServiceNotFoundException;
import com.salonService.app.exception.ServiceAlreadyExistsException;
import com.salonService.app.repository.ISalonRepository;
import com.salonService.app.services.ISalonService;
import com.salonService.app.services.SalonServiceImpl;

@SpringBootTest
class SalonServiceAppApplicationTests {
	@Mock
	ISalonRepository salonRepository;
	@Autowired
	SalonServiceImpl salonserviceimp;

	@InjectMocks
	ISalonService salon = new SalonServiceImpl();
	SalonService s1, s2, s3;

	public static SalonService demo() {
		SalonService s = new SalonService();
		s.setServiceId((long) 1);
		s.setSeviceName("Haircut");
		s.setServicePrice("200");
		s.setServiceDuration("60");
		return s;
	}

	@BeforeEach
	public void init() {
		s1 = new SalonService((long) 1, " Haircut", "200", "60");
		s2 = new SalonService((long) 2, " Hairspa", "200", "60");
		s3 = new SalonService((long) 3, " Haircut", "200", "60");

	}

	SalonService salonService = SalonServiceAppApplicationTests.demo();

	@Test
	void validSalonServiceAddition() throws ServiceAlreadyExistsException {
		Mockito.when(salonRepository.findById(salonService.getServiceId())).thenReturn(Optional.empty());
		Assertions.assertNotNull(salonService);
	}

	@Test
	void invalidServiceAddition() throws ServiceAlreadyExistsException {
		Mockito.when(salonRepository.findById(salonService.getServiceId())).thenReturn(Optional.of(salonService));
		ServiceAlreadyExistsException ex = Assertions.assertThrows(ServiceAlreadyExistsException.class,
				() -> salon.addService(salonService));
		Assertions.assertEquals("SERVICE ALREADY EXISTS", ex.getMessage());
	}

	@Test
	void validRemoveService() throws ServiceAlreadyExistsException {
		Mockito.when(salonRepository.findById(salonService.getServiceId())).thenReturn(Optional.of(salonService));
		Assertions.assertDoesNotThrow(() -> salon.removeService(salonService.getServiceId()));
	}

	@Test
	void invalidRemoveService() throws SalonServiceNotFoundException {
		Mockito.when(salonRepository.findById(salonService.getServiceId())).thenReturn(Optional.empty());
		SalonServiceNotFoundException ex = Assertions.assertThrows(SalonServiceNotFoundException.class,
				() -> salon.removeService(salonService.getServiceId()));
		Assertions.assertEquals(ex.getMessage(), "Salon Service NOT FOUND with id " + salonService.getServiceId());
	}

	@Test
	void validGetService() throws SalonServiceNotFoundException {
		Mockito.when(salonRepository.findById(salonService.getServiceId())).thenReturn(Optional.of(salonService));
		Assertions.assertEquals(salon.getService(salonService.getServiceId()), salonService);
	}

//	 @Test
//	    public void validServiceUpdate() throws SalonServiceNotFoundException {
//		 Mockito.when(salonRepository.findById(salonService.getServiceId())).thenReturn(Optional.empty());
//		   SalonService s2 = new SalonService((long) 1, "hair_cut","50","6");
//		  Assertions.assertEquals(s2.getServicePrice(),salon.updateService(s2.getServiceId(),s2).getServicePrice());
////		// Mockito.when(salonRepository.findById(s1.getServiceId())).thenReturn(Optional.ofNullable(s1));
////		 //Mockito.when(salonRepository.existsById(s1.getServiceId())).thenReturn(true);
////		 
////		 //Mockito.when(salonRepository.save(s1)).thenReturn(s1);
////		// Assertions.assertEquals(s1.getServicePrice(),salonserviceimp.updateService(s1.getServiceId(),s1).getServicePrice());
////		 
//	 }

	@Test
	void invalidServiceUpdate() throws SalonServiceNotFoundException {
		Mockito.when(salonRepository.findById(salonService.getServiceId())).thenReturn(Optional.empty());
		SalonServiceNotFoundException ex = Assertions.assertThrows(SalonServiceNotFoundException.class,
				() -> salon.updateService((salonService.getServiceId()), salonService));
		Assertions.assertEquals("Salon service not found", ex.getMessage());
	}

	@Test
	void invalidGetService() throws SalonServiceNotFoundException {
		Mockito.when(salonRepository.findById(salonService.getServiceId())).thenReturn(Optional.empty());
		SalonServiceNotFoundException ex = Assertions.assertThrows(SalonServiceNotFoundException.class,
				() -> salon.getService(salonService.getServiceId()));
		Assertions.assertEquals("Salon Service NOT FOUND", ex.getMessage());
	}

	@Test
	void validGetAllService() throws SalonServiceNotFoundException {
		List<SalonService> list = new ArrayList<>();
		list.add(salonService);
		Mockito.when(salonRepository.findAll()).thenReturn(list);
		Assertions.assertEquals(salon.getAllServices(), list);
	}
	@Test
	void inValidGetAllService() throws SalonServiceNotFoundException {
		//List<SalonService> list = new ArrayList<>();
		//Mockito.when(salonRepository.findAll()).thenReturn(Optional.ofNullab));
		SalonServiceNotFoundException ex = Assertions.assertThrows(SalonServiceNotFoundException.class,
				() -> salon.getAllServices());
		Assertions.assertEquals("Salon Service NOT FOUND", ex.getMessage());
	}

	@Test
	void validgetServiceByName() throws SalonServiceNotFoundException {

		List<SalonService> list = new ArrayList<>();
		list.add(salonService);
		Mockito.when(salonRepository.findBySeviceName(salonService.getSeviceName())).thenReturn(list);
		Assertions.assertEquals(salon.getSeviceByName(salonService.getSeviceName()), list);
	}

	@Test
	void invalidgetServiceByName() throws SalonServiceNotFoundException {

		Mockito.when(salonRepository.findBySeviceName(salonService.getSeviceName())).thenReturn(new ArrayList<>());
		SalonServiceNotFoundException ex = Assertions.assertThrows(SalonServiceNotFoundException.class,
				() -> salon.getSeviceByName(salonService.getSeviceName()));
		Assertions.assertEquals(ex.getMessage(), "Salon Service NOT FOUND with name " + salonService.getSeviceName());
	}

	@Test
	void validgetServiceByPrice() throws SalonServiceNotFoundException {
		List<SalonService> list = new ArrayList<>();
		list.add(salonService);
		Mockito.when(salonRepository.findByServicePrice(salonService.getServicePrice())).thenReturn(list);
		Assertions.assertEquals(salon.getServiceByPrice(salonService.getServicePrice()), list);
	}

	@Test
	void invalidgetServiceByPrice() throws SalonServiceNotFoundException {
		Mockito.when(salonRepository.findByServicePrice(salonService.getServicePrice())).thenReturn(new ArrayList<>());
		SalonServiceNotFoundException ex = Assertions.assertThrows(SalonServiceNotFoundException.class,
				() -> salon.getServiceByPrice(salonService.getServicePrice()));
		Assertions.assertEquals(ex.getMessage(),
				"Salon Service NOT FOUND with price " + salonService.getServicePrice());
	}

	@Test
	void validgetServiceByDuration() throws SalonServiceNotFoundException {

		List<SalonService> list = new ArrayList<>();
		list.add(salonService);
		Mockito.when(salonRepository.findByServiceDuration(salonService.getServiceDuration())).thenReturn(list);
		Assertions.assertEquals(salon.getServicesByDuration(salonService.getServiceDuration()), list);
	}

	@Test
	void invalidgetServiceByDuration() throws SalonServiceNotFoundException {
		Mockito.when(salonRepository.findByServiceDuration(salonService.getServiceDuration()))
				.thenReturn(new ArrayList<>());
		SalonServiceNotFoundException ex = Assertions.assertThrows(SalonServiceNotFoundException.class,
				() -> salon.getServicesByDuration(salonService.getServiceDuration()));
		Assertions.assertEquals(ex.getMessage(),
				"Salon Service NOT FOUND with duration " + salonService.getServiceDuration());
	}

}
