package com.swiftwin.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.swiftwin.app.entity.UserDtls;
import com.swiftwin.app.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository repo;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	//mapping with registration form
	@PostMapping("/register")
	
	public String register (@ModelAttribute UserDtls u,HttpSession session) {
		//ModelAtribute is annotation of spring mvc which gives the daata stored in object
		//u object contains whole data coming from registration form and if entity members name and form field name are not same then it will not work
		
		System.out.println(u);
		
		//here we saving data into db by calling the method of userrepository interface which extends JpaRepository
		repo.save(u);
		
		// here we showing the msg on front end
		session.setAttribute("message", "User Registered Succesfully....");
		return "redirect:/";
		
		
	}
}
