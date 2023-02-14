package com.salonService.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonService.app.entity.Admin;
import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.SalonService;
import com.salonService.app.exception.SalonServiceNotFoundException;
import com.salonService.app.exception.ServiceAlreadyExistsException;
import com.salonService.app.exception.UserAlreadyExists;
import com.salonService.app.exception.UserNotExistsException;
import com.salonService.app.repository.IAdminRepository;
import com.salonService.app.repository.IAppointmentRepository;
import com.salonService.app.repository.ISalonRepository;

@Service
public class IAdminServiceImpl implements IAdminService {
//	@Autowired
//	private ISalonRepository salonRepository;
//	@Autowired
//	private IAppointmentRepository iappointmentRepo;
	@Autowired
	private IAdminRepository iadminRepo;

//	@Override
//	public SalonService addService(SalonService salonService) throws ServiceAlreadyExistsException {
//		Optional<SalonService> salon = salonRepository.findById(salonService.getServiceId());
//    	if(salon.isPresent()) {
//    		throw new ServiceAlreadyExistsException("SERVICE ALREADY EXISTS");
//    	}
//		salonRepository.save(salonService);
//		return salonService;
//	}
//
//	@Override
//	public void removeService(Long serviceId) throws SalonServiceNotFoundException {
//		Optional<SalonService> salon = salonRepository.findById(serviceId);
//		salon.orElseThrow(() -> new SalonServiceNotFoundException("Salon Service NOT FOUND with id "+serviceId));
//		salonRepository.deleteById(serviceId);
//		
//	}
//
//	@Override
//	public SalonService updateService(Long serviceId, SalonService salonService) throws SalonServiceNotFoundException {
//		Optional<SalonService> salon = salonRepository.findById(serviceId);
//		SalonService service=salon.get();
//		service.setSeviceName(salonService.getSeviceName());
//		service.setServicePrice(salonService.getServicePrice());
//		service.setServiceDuration(salonService.getServiceDuration());
//		return salonRepository.save(service);
//
//	}

//	@Override
//	public Appointment getAppointmentById(Long id) {
//		Optional<Appointment> foundAppointment= iappointmentRepo.findById(id);
//		if(!foundAppointment.isPresent()) {
//			//Exception
//		}
//		return foundAppointment.get();
//	}
//
//	@Override
//	public List<Appointment> getAllAppointments() {
//		// TODO Auto-generated method stub
//		return iappointmentRepo.findAll();
//	}

	@Override
	public Admin signUp(Admin newAdmin) throws UserNotExistsException, UserAlreadyExists {
		Optional<Admin> repUser = iadminRepo.findById(newAdmin.getId());
		if (repUser.isPresent())
			throw new UserAlreadyExists("Account with given id existed");
		else
			iadminRepo.save(newAdmin);
		return newAdmin;
	}
	@Override
	public Admin signIn(Admin user) throws UserNotExistsException {
		Optional<Admin> repUser = iadminRepo.findById(user.getId());
		Admin u = repUser.orElseThrow(() -> new UserNotExistsException("User not found with id " + user.getId()));
		if (!u.getPassword().equals(user.getPassword())) {
			throw new UserNotExistsException("User.Wrong_Password");
		}
		return user;
	}

	@Override
	public String changePassword(Admin admin) throws UserNotExistsException {
		Optional<Admin> repUser = iadminRepo.findById(admin.getId());
		Admin u = repUser.orElseThrow(() -> new UserNotExistsException("User not found with id " + admin.getId()));
		u.setPassword(admin.getPassword());
		iadminRepo.save(u);
		return "Password changed successfully";
	}

}
