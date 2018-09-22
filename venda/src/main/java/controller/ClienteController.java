package controller;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Cliente;
import model.Produto;
import persistence.ClienteRepository;
import persistence.ProdutoRepository;

@Scope(value = "session")
@Component(value = "clienteController")
@ELBeanName(value = "clienteController")
@Join(path = "/cliente", to = "/cliente/cliente-form.jsf")
@RestController
public class ClienteController {
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	private Cliente cliente = new Cliente();
	
	@GetMapping("/cliente/incluir")
	public String save() {
		clienteRepository.save(cliente);
		cliente = new Cliente();
		return "/cliente/cliente-list.xhtml?faces-redirect=true";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<Produto> getProdutos() {
		return produtoRepository.findAll();
	}
}
