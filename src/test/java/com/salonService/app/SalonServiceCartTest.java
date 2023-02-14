package com.salonService.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.salonService.app.entity.SalonService;
import com.salonService.app.entity.ServiceCart;
import com.salonService.app.exception.CartNotFoundException;
import com.salonService.app.exception.SalonServiceNotFoundException;
import com.salonService.app.repository.ISalonRepository;
import com.salonService.app.repository.IServiceCartRepository;
import com.salonService.app.services.IServiceCartServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class SalonServiceCartTest {
	@Mock
	private IServiceCartRepository serviceRepo;

	@Mock
	private com.salonService.app.services.ICustomerService iCustomerService;

	@Mock
	private com.salonService.app.services.ISalonService iSalonService;

	@Mock
	private ISalonRepository salonRepository;
	
	

	@InjectMocks
	private IServiceCartServiceImpl service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void addServiceToCartTest() throws CartNotFoundException, SalonServiceNotFoundException {
		// Arrange
		Long cartId = 1L;
		SalonService serviceToAdd = new SalonService(1L, "Service 1", "Description 1", "10.00");
		ServiceCart existingCart = new ServiceCart(cartId, 5.00, Collections.emptyList());
		doReturn(Optional.of(existingCart)).when(serviceRepo).findById(cartId);
		doReturn(Arrays.asList(serviceToAdd)).when(iSalonService).getAllServices();

		// Act
		ServiceCart result = service.addServiceToCart(serviceToAdd, cartId);
		ServiceCart cart = new ServiceCart(cartId, 15.00, Arrays.asList(serviceToAdd));
		// Assert
		assertEquals(result, cart);
	}

	@Test
	void addServiceToCart_givenNonExistingCart_shouldThrowCartNotFoundException() {
		// Arrange
		Long cartId = 1L;
		SalonService serviceToAdd = new SalonService(1L, "Service 1", "1500", "10.00");
		String msg = null;
		when(serviceRepo.findById(cartId)).thenReturn(Optional.empty());
		try {
			service.addServiceToCart(serviceToAdd, cartId);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		assertEquals("Cart with customer " + cartId + "not found to add service", msg);
	}

	@Test
	void addServiceToCartServiceException() {
		Long cartId = 1L;
		SalonService serviceToAdd = new SalonService();
		serviceToAdd.setServiceId(cartId);
		serviceToAdd.setServiceDuration("10.0");
		serviceToAdd.setServicePrice("100.0");
		serviceToAdd.setSeviceName("haircut");
		List<SalonService> serviceList = new ArrayList<>();
		serviceList.add(serviceToAdd);
		ServiceCart existingCart = new ServiceCart();
		existingCart.setAmount(100.0);
		existingCart.setId(cartId);
		existingCart.setServiceList(serviceList);
		String msg = null;
		try {
			when(serviceRepo.findById(cartId)).thenReturn(Optional.of(existingCart));
		} catch (Exception e) {
			msg = e.getMessage();
		}
		String mg = "Service not available right now to add to the cart";
		assertEquals("Service not available right now to add to the cart", mg);
	}

	@Test
	void addServiceToCart_givenNonExistingSalonService_shouldThrowSalonServiceNotFoundException()
			throws SalonServiceNotFoundException {
		// Arrange
		Long cartId = 1L;
		SalonService serviceToAdd = new SalonService(1L, "Service 1", "Description 1", "10.00");
		doReturn(Optional.of(new ServiceCart())).when(serviceRepo).findById(cartId);
		doReturn(Collections.emptyList()).when(iSalonService).getAllServices();

		// Act & Assert
		assertThrows(SalonServiceNotFoundException.class, () -> service.addServiceToCart(serviceToAdd, cartId));
	}

	@Test
	public void testDeleteServiceById_Success() {
		// Arrange
		Long cartId = 1L;
		Double amount = 100.0;
		SalonService serviceToRemove = new SalonService(cartId, "Service 1", " 100", "10");
		ServiceCart cart = new ServiceCart(cartId, amount, Collections.singletonList(serviceToRemove));
		Mockito.when(serviceRepo.findById(cartId)).thenReturn(Optional.of(cart));

		// Act
		SalonService result = service.deleteServiceById(serviceToRemove.getServiceId(), cartId);

		// Assert 
		assertEquals(serviceToRemove, result);
		assertEquals(cartId, cart.getId());
		assertEquals(amount - 30.0, cart.getAmount(), 0.0);
		assertTrue(cart.getServiceList().isEmpty());
	}

	@Test
	public void testGetAllServicesInCart() throws SalonServiceNotFoundException, CartNotFoundException {
		// Arrange
		Long cartId = 1L;
		List<ServiceCart> expectedServices = new ArrayList<>();
		expectedServices.add(new ServiceCart(cartId, 5.00, Collections.emptyList()));
		expectedServices.add(new ServiceCart(cartId, 5.00, Collections.emptyList()));
		when(serviceRepo.findAll()).thenReturn(expectedServices);

		// Act
		List<ServiceCart> actualServices = service.getAllServicesInCart();

		// Assert
		assertThat(actualServices).isEqualTo(expectedServices);
	}

	@Test
	    public void testGetAllServicesInCart_EmptyCart() throws SalonServiceNotFoundException, CartNotFoundException {
	        // Arrange
	        when(serviceRepo.findAll()).thenReturn(new ArrayList<ServiceCart>());
	       
	        // Act and Assert
	        assertThrows(CartNotFoundException.class, () -> {
	        	service.getAllServicesInCart();
	        });
	    }

	@Test
	    public void testGetServiceCartByid() throws CartNotFoundException {
	        // Arrange
	        ServiceCart expectedService = new ServiceCart(1L, 1000.0, Collections.emptyList());
	        when(serviceRepo.findById(1L)).thenReturn(Optional.of(expectedService));
	       
	        // Act
	        ServiceCart actualService = service.getServiceCartByid(1L);
	       
	        // Assert
	        assertThat(actualService).isEqualTo(expectedService);
	    }

	@Test
	    public void testGetServiceCartByid_CartNotFound() throws CartNotFoundException {
	        // Arrange
	        when(serviceRepo.findById(1L)).thenReturn(Optional.empty());
	       
	        // Act and Assert
	        assertThrows(CartNotFoundException.class, () -> {
	        	service.getServiceCartByid(1L);
	        });
	    }

}