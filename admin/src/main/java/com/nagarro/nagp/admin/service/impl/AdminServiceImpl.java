package com.nagarro.nagp.admin.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.nagp.admin.dao.AdminDao;
import com.nagarro.nagp.admin.entity.Admin;
import com.nagarro.nagp.admin.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	@Override
	public List<Admin> getAllAdmins() {
		return adminDao.getAllAdmins();

	}

	@Override
	public void addAdmin(Admin admin) {
		LocalDateTime now = LocalDateTime.now();
		admin.setCreationTime(dtf.format(now));
		admin.setModifiedTime(dtf.format(now));
		adminDao.addAdmin(admin);
	}

	@Override
	public Admin getAdminByUsername(String username) {
		return adminDao.getAdminByUsername(username);
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		LocalDateTime now = LocalDateTime.now();
		admin.setModifiedTime(dtf.format(now));
		return adminDao.updateAdmin(admin);
	}

}
