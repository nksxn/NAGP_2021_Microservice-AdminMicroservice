package com.nagarro.nagp.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nagarro.nagp.admin.entity.Admin;

@Service
public interface AdminService {

	public List<Admin> getAllAdmins();

	public void addAdmin(Admin admin);

	public Admin getAdminByUsername(String username);

	public boolean updateAdmin(Admin admin);

	public void approveOrder(String code);

}
