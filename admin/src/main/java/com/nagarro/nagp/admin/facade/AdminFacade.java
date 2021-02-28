package com.nagarro.nagp.admin.facade;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.nagarro.nagp.admin.entity.Admin;

@Component
public interface AdminFacade {
	
	public Map<String, Object> getAllAdmins();

	public void addAdmin(Admin admin);

	public Map<String, Object> getAdminByUsername(String username);

	public boolean updateAdmin(Admin admin);

	public void approveOrder(String code);

}
