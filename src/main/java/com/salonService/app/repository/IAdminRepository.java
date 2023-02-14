package com.salonService.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salonService.app.entity.Admin;

public interface IAdminRepository extends JpaRepository<Admin, Long>{

}
