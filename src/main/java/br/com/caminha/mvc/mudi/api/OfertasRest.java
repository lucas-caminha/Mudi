package br.com.caminha.mvc.mudi.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import br.com.caminha.mvc.mudi.dto.RequisicaoNovaOferta;
import br.com.caminha.mvc.mudi.model.Oferta;
import br.com.caminha.mvc.mudi.model.Pedido;
import br.com.caminha.mvc.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@PostMapping
	public Oferta criaOferta(@Valid @RequestBody RequisicaoNovaOferta novaOferta) {
		Optional<Pedido> pedidoBuscado;
		try {
			pedidoBuscado = pedidoRepository.findById(novaOferta.getPedidoId());
		} catch (Exception e){
			e.printStackTrace();
			pedidoBuscado = null;
		}
		
		if(!pedidoBuscado.isPresent()) {
			return null;
		}
		
		Pedido pedido = pedidoBuscado.get();	
			
		Oferta oferta = novaOferta.toOferta();
		oferta.setPedido(pedido);
		
		pedido.getOfertas().add(oferta);
		
		pedidoRepository.save(pedido);
		
		
		return oferta;
	}
	
}
