package br.com.caminha.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caminha.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.caminha.mvc.mudi.model.Pedido;
import br.com.caminha.mvc.mudi.model.User;
import br.com.caminha.mvc.mudi.repository.PedidoRepository;
import br.com.caminha.mvc.mudi.repository.UserRepository;

/*
 * Todas as requisições para /pedido vão cair no PedidoController.
 */

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("formulario")
	public String formulario(RequisicaoNovoPedido req) {
		return "pedido/formulario";
	}
	
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoPedido req, BindingResult result) {
		
		if (result.hasErrors()) {
			return "pedido/formulario";
		}
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username);
		
		Pedido pedido = req.toPedido();
		pedido.setUser(user);
		
		pedidoRepository.save(pedido);
		
		return "redirect:/home";
	}
	
}
