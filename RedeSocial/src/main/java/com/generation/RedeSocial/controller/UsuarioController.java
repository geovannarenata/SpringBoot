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

import com.generation.RedeSocial.model.Usuario;
import com.generation.RedeSocial.repository.UsuarioRepository;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Usuario> findByIDUsuario(@PathVariable long id)
	{
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/usuario/{usuario}")
	public ResponseEntity <List<Usuario>> findAllUsuario(String nome)
	{
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping 
	public ResponseEntity <Usuario> postUsuario (@RequestBody Usuario nome)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(nome));
	}
	
	@PutMapping
	public ResponseEntity <Usuario> putUsuario (@RequestBody Usuario nome)
	{
		return ResponseEntity.ok(repository.save(nome));
	}
	
	@DeleteMapping("/{id}")
	public void deleteUsuario(@PathVariable long id)
	{
		repository.deleteById(id);
	}
	

}
