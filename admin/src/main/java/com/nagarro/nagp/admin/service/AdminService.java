package com.nagarro.nagp.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nagarro.nagp.user.entity.Admin;

@Service
public interface AdminService {
	
	public List<Admin> getAllAdmins();

	public void addAdmin(Admin admin);

	public User getAdminByUsername(String username);

	public boolean updateUser(Admin admin);

}
