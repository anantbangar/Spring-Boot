package com.spb02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test {
	
	@RequestMapping("/home")
	public String home()
	{
		return "home";
	}

}
