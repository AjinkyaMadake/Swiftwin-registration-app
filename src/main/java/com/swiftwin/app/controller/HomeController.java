package com.swiftwin.app.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swiftwin.app.entity.UserDtls;
import com.swiftwin.app.repository.UserRepository;


@Controller
@RequestMapping("/user")
public class HomeController {

	  // this is to checck that if user is logged in thenit can access the home page
	
	
	@Autowired
	private UserRepository repo;
	
	// transfers to home page
	@GetMapping("/home")  
	public String home(Principal p,Model m) {  // to show logged in user into home page
		String em=p.getName();
		UserDtls u=repo.findByEmail(em); 
		m.addAttribute("user",u);
		// passing logged in information of the user to the home page
		m.addAttribute("fullName", u.getFullname());
		m.addAttribute("address", u.getAddress());
		m.addAttribute("em", u.getEmail());
		return "home";
	}

}

