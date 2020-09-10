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

import com.generation.RedeSocial.model.Tema;
import com.generation.RedeSocial.repository.TemaRepository;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/tema")
public class TemaController {

	@Autowired
	private TemaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Tema>> getAll()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> findIdByTema(@PathVariable long id)
	{
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/tema/{tema}")
	public ResponseEntity<List<Tema>> findAllTema(@PathVariable String descricao)
	{
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping
	public ResponseEntity<Tema> postTema (@RequestBody Tema descricao)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(descricao));
	}
	
	@PutMapping
	public ResponseEntity<Tema> putTema(@RequestBody Tema descricao)
	{
		return ResponseEntity.ok(repository.save(descricao));
	}
	
	@DeleteMapping("/{id}")
	public void deleteTema (@PathVariable long id)
	{
		repository.deleteById(id);
	}
	
}
