package com.salonService.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salonService.app.entity.User;
@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{

	@Query("select user from Users user where userName = :userName and password=:password")
	public User getUserByUsernameAndPassword(@Param("userName") String userName, @Param("password") String password);

	public User findByUserName(String userName);

}
