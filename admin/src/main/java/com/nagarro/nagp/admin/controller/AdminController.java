package com.nagarro.nagp.admin.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.nagp.admin.entity.Admin;
import com.nagarro.nagp.admin.facade.AdminFacade;

@RestController
@RequestMapping(value = "/admins")
public class AdminController {

	private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

	@Value("${server.port}")
	private int port;

	@Autowired
	AdminFacade adminFacade;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllAdmins() {

		LOG.info("Working from port " + port + " of Admins microservice");

		Map<String, Object> responseData = adminFacade.getAllAdmins();

		return new ResponseEntity<Map<String, Object>>(responseData, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Void> addAdmin(@RequestBody Admin admin) {
		LOG.info("Working from port " + port + " of Admins microservice");
		try {
			adminFacade.addAdmin(admin);
			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@GetMapping("/{username}")
	public ResponseEntity<Map<String, Object>> getAdminByUsername(@PathVariable("username") String username) {
		LOG.info("Working from port " + port + " of Admins microservice");
		Map<String, Object> responseData = adminFacade.getAdminByUsername(username);
		return new ResponseEntity<Map<String, Object>>(responseData, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<Void> editAdmin(@RequestBody Admin admin) {
		LOG.info("Working from port " + port + " of Admins microservice");
		HttpHeaders headers = new HttpHeaders();
		if (adminFacade.updateAdmin(admin)) {
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/approveOrder/{code}")
	public ResponseEntity<Void> approveOrder(@PathVariable("code") String code) {
		adminFacade.approveOrder(code);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
}
