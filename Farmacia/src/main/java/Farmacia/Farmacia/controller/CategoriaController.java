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

import Farmacia.Farmacia.model.Categoria;
import Farmacia.Farmacia.repository.CategoriaRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Categoria> GetById(@PathVariable long id)
	{
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/categoria/{categoria}")
	public ResponseEntity<List<Categoria>> GetByCategoria(@PathVariable String descricao)
	{
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> postCategoria (@RequestBody Categoria descricao)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(descricao));
	}
	
	@PutMapping 
	public ResponseEntity<Categoria> putCategoria (@RequestBody Categoria descricao)
	{
		return ResponseEntity.ok(repository.save(descricao));
	}

	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable long id)
	{
		repository.deleteById(id);
	}
}
