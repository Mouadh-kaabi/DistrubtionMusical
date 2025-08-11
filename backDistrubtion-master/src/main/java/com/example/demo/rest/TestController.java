package com.example.demo.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "http://192.168.1.111:4200")

public class TestController {

	
	
	@GetMapping("/all")
	public String AllAcces()
	{
		return "Public content";
	}
	
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('ARTISTE') or hasRole('label') or hasRole('Cp')  or hasRole('ADMIN')")
	public String userAccess()
	{
		return "User Content";
	}
	
	
	@GetMapping("/artist")
	@PreAuthorize("hasRole('ARTISTE')")
	public String artisteAccess()
	{
		return "Artiste Content";
	}
	
	@GetMapping("/label")
	@PreAuthorize("hasRole('label')")
	public String labelAccess()
	{
		return "label Content";
	}
	
	@GetMapping("/Cp")
	@PreAuthorize("hasRole('Cp')")
	public String CpAccess()
	{
		return "Cp Content";
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String ADMINAccess()
	{
		return "ADMIN Content";
	}
	
	
}
