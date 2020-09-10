package com.generation.RedeSocial.controller;

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

import com.generation.RedeSocial.model.Postagem;
import com.generation.RedeSocial.repository.PostagemRepository;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/postagem")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@GetMapping
	public ResponseEntity <List<Postagem>> findAllPostagem()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Postagem> findByIdPostagem(@PathVariable long id)
	{
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/postagem/{postagem}")
	public ResponseEntity <List<Postagem>> findAllPostagem(@PathVariable String descricao)
	{
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping
	public ResponseEntity <Postagem> postPostagem (@RequestBody Postagem descricao)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(descricao));
	}
	
	@PutMapping
	public ResponseEntity <Postagem> putPostagem (@RequestBody Postagem descricao)
	{
		return ResponseEntity.ok(repository.save(descricao));
	}
	
	@DeleteMapping("/{id}")
	public void deletePostagem (@PathVariable long id)
	{
		repository.deleteById(id);
	}
	
	
}
