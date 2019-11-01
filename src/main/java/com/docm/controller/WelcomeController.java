package com.docm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class WelcomeController {
	@RequestMapping({ "/" })
	public String home(HttpServletRequest request) {
		request.getSession().setAttribute("ACCOUNT", "admin");
		return "redirect:/home.jsp";
	}

}
