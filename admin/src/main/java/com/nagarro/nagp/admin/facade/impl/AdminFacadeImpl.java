package com.nagarro.nagp.admin.facade.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.nagp.admin.entity.Admin;
import com.nagarro.nagp.admin.facade.AdminFacade;
import com.nagarro.nagp.admin.service.AdminService;

@Component
public class AdminFacadeImpl implements AdminFacade {

	@Autowired
	AdminService adminService;

	@Override
	public Map<String, Object> getAllAdmins() {

		List<Admin> result = adminService.getAllAdmins();
		Map<String, Object> responseData = new HashMap<String, Object>();
		responseData.put("admins", result);
		return responseData;

	}

	@Override
	public void addAdmin(Admin admin) {
		adminService.addAdmin(admin);
	}

	@Override
	public Map<String, Object> getAdminByUsername(String username) {
		Admin admin = adminService.getAdminByUsername(username);
		Map<String, Object> responseData = new HashMap<String, Object>();
		responseData.put("admin", admin);
		return responseData;
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		return adminService.updateAdmin(admin);
	}

	@Override
	public void approveOrder(String code) {
		adminService.approveOrder(code);
	}

}
