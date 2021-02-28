package com.nagarro.nagp.admin.dao.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.nagarro.nagp.admin.dao.AdminDao;
import com.nagarro.nagp.admin.entity.Admin;

@Repository
public class AdminDaoImpl implements AdminDao {

	private static final Logger LOG = LoggerFactory.getLogger(AdminDaoImpl.class);

	private static List<Admin> admins = new ArrayList<Admin>();
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	@PostConstruct
	public void init() {
		LOG.info("Creting Dummy data for Admins.");
		LocalDateTime now = LocalDateTime.now();
		Admin admin1 = new Admin();
		admin1.setUid("1");
		admin1.setFirstName("Kanak");
		admin1.setLastName("Dhaka");
		admin1.setEmail("kanak.dhaka@gmail.com");
		admin1.setPhone("9853472311");
		admin1.setUsername("kanakDhaka");
		admin1.setPassword("qwerty@123");
		admin1.setCreationTime(dtf.format(now));
		admin1.setModifiedTime(dtf.format(now));
		Admin admin2 = new Admin();
		admin2.setUid("2");
		admin2.setFirstName("Nimish");
		admin2.setLastName("Saxena");
		admin2.setEmail("vibhu.saxena31@gmail.com");
		admin2.setPhone("9873640321");
		admin2.setUsername("nksxn");
		admin2.setPassword("qwerty@123");
		admin2.setCreationTime(dtf.format(now));
		admin2.setModifiedTime(dtf.format(now));
		admins.add(admin1);
		admins.add(admin2);
	}

	@Override
	public List<Admin> getAllAdmins() {
		LOG.info("Mocking the DB call. Fetching data from the list");
		return admins;
	}

	@Override
	public void addAdmin(Admin admin) {
		LOG.info("Mocking the DB call. Adding data to the list");
		List<Admin> result = admins.stream().filter(c -> c.getUsername().equalsIgnoreCase(admin.getUsername()))
				.collect(Collectors.toList());
		if (result.isEmpty()) {
			admins.add(admin);
		} else {

			LOG.error("Username already exists in the database.");
		}

	}

	@Override
	public Admin getAdminByUsername(String username) {
		LOG.info("Mocking the DB call. Getting admin from the list based on username");
		List<Admin> result = admins.stream().filter(c -> c.getUsername().equalsIgnoreCase(username))
				.collect(Collectors.toList());
		return result.get(0);
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		LOG.info("Mocking the DB call. Updating admin in the list.");
		List<Admin> result = admins.stream().filter(c -> c.getUsername().equalsIgnoreCase(admin.getUsername()))
				.collect(Collectors.toList());
		if (result.isEmpty()) {
			LOG.error("There is no such admin with username " + admin.getUsername());
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

}
