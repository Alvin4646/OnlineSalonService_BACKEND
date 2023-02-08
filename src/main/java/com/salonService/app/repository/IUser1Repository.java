package com.salonService.app.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface IUser1Repository {

	//Query to select the User by using userName
//	@Query("Select U from User1 U where U.userName = ?1")
//	Optional<User> finduserIdByUserName(String userName);
//	Optional<User> findByUserName(String userName);

}