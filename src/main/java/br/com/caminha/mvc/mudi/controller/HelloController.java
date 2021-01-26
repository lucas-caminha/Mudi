package br.com.caminha.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author lucas.caminha
 * Cada método é uma ACTION.
 * São os métodos que atendem uma requisição HTTP, usando por exemplo a anotação @GetMapping.
 */

@Controller
public class HelloController {
	
	@GetMapping("/hello")
	public String hello(Model model) {
		//request.setAttribute("nome", "Spring Boot!");
		model.addAttribute("nome", "Spring Boot!")
;		return "hello";
	}

}
