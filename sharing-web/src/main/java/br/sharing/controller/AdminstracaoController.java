package br.sharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adm")
public class AdminstracaoController {

	@RequestMapping("/home")
	public String home() {
		return "/adm/home";
	}
}
