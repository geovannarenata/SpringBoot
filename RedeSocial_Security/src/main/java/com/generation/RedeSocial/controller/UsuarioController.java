package com.generation.RedeSocial.controller;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.RedeSocial.model.UserLogin;
import com.generation.RedeSocial.model.Usuario;
import com.generation.RedeSocial.service.UsuarioService;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user)
	{
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario)
	{
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.CadastrarUsuario(usuario));
	}
	
	
	//private UsuarioRepository repository;
		
	
/*	@GetMapping
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
	*/

}
