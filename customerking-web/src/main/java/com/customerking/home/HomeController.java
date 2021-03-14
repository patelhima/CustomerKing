package com.customerking.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class HomeController {

	@GetMapping("/")
	public ModelAndView dashboardPage(ModelAndView model) {
		model.setViewName("dashboard");
		return model;
	}
	
}
