package controller;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Produto;
import persistence.ProdutoRepository;

@Scope(value = "session")
@Component(value = "listaClientes")
@ELBeanName(value = "listaClientes")
@Join(path = "/", to = "/cliente/cliente-list.jsf")
@RestController
public class ListaClientesController {
	@Autowired
	private ProdutoRepository produtoRepository;

	private List<Produto> produtos;

	@Deferred
	@RequestAction
	@IgnorePostback
	@GetMapping("/cliente/listar")
	public void loadData() {
		produtos = produtoRepository.findAll();
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public String apagar(Produto produto) {
		produtoRepository.deleteById(produto.getId());
		loadData();
		return null;
	}
}
