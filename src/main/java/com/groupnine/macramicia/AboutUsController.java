package com.groupnine.macramicia;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class AboutUsController {
	@GetMapping("/AboutUs")
	public String date(Model model) {
		model.addAttribute("date", new Date(System.currentTimeMillis()));
		return "aboutUs";
	}
}
