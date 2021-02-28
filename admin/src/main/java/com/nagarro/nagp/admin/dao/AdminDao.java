package com.nagarro.nagp.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nagarro.nagp.admin.entity.Admin;

@Repository
public interface AdminDao {
	
	public List<Admin> getAllAdmins();

	public void addAdmin(Admin admin);

	public Admin getAdminByUsername(String username);

	public boolean updateAdmin(Admin admin);

}
