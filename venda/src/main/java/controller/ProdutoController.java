package controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Produto;
import persistence.ProdutoRepository;

@Scope(value = "session")
@Component(value = "produtoController")
@ELBeanName(value = "produtoController")
@Join(path = "/produto", to = "/produto/produto-form.jsf")
@RestController
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;

	private Produto produto = new Produto();

	@GetMapping("/produtos/incluir")
	public String save() {
		produtoRepository.save(produto);
		produto = new Produto();
		return "/produto/produto-list.xhtml?faces-redirect=true";
	}

	public Produto getProduto() {
		return produto;
	}
}
