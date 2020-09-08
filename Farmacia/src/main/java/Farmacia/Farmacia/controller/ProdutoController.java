package Farmacia.Farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Farmacia.Farmacia.model.Produto;
import Farmacia.Farmacia.repository.ProdutoRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping 
	public ResponseEntity <List<Produto>> getAll()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Produto> GetById(@PathVariable long id)
	{
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/produto/{produto}") 
	public ResponseEntity <List<Produto>> GetByProduto(@PathVariable String nome)
	{
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity <Produto> postProduto(@RequestBody Produto produto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}
	
	@PutMapping 
	public ResponseEntity <Produto> putProduto (@RequestBody Produto produto)
	{
		return ResponseEntity.ok(repository.save(produto));
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduto (@PathVariable long id)
	{
		repository.deleteById(id);
	}

}
