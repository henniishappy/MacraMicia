package com.macramicia.about;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class AboutUsController {

	@GetMapping("/aboutUs")
	public String date(Model model) {
		model.addAttribute("date", new Date(System.currentTimeMillis()));
		return "aboutUs";
	}
}
