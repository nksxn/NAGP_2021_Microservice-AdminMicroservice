package com.nagarro.nagp.admin.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.nagp.admin.dao.AdminDao;
import com.nagarro.nagp.admin.entity.Admin;
import com.nagarro.nagp.admin.service.AdminService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private EurekaClient eurekaClient;

	@Resource(name = "restTemp")
	private RestTemplate restTemplate;

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

	@Override
	public void approveOrder(String code) {
		String url = "/order/status/InProcess";
		HttpEntity<String> request = new HttpEntity<>(code);
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("orders", false);
		restTemplate.postForObject(instance.getHomePageUrl() + url, request, null);
		restTemplate.postForObject(instance.getHomePageUrl() + "/order/notify}", request, null);

	}

	public EurekaClient getEurekaClient() {
		return eurekaClient;
	}

	public void setEurekaClient(EurekaClient eurekaClient) {
		this.eurekaClient = eurekaClient;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

}
