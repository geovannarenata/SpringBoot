package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.RepositoryType;
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

@RestController 									/* especificação dd Controller com EndPoints */ 
@CrossOrigin(origins = "*", allowedHeaders = "*")	/* Habilitar p/ utilização independete da origem */
@RequestMapping("/tema")							/* Localização de acesso dos EndPoits */ 
public class TemaController {
	
	@Autowired										/* Inserir os serviços */ 
	private TemaRepository repository;
	
	/* Método que busca a lista de temas */ 
	@GetMapping
	public ResponseEntity<List<Tema>> getAll()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	/* Método que busca os temas pelo Id */ 
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable long id)
	{
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	/* Método que busca temas pelo nome */ 
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> getByName(@PathVariable String nome) 
	{
			return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(nome));
	}
	
	/* Inserção de Temas */ 
	@PostMapping
	public ResponseEntity<Tema> post (@RequestBody Tema tema) 
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}
	
	/* Atualização de Temas */ 
	@PutMapping
	public ResponseEntity<Tema> put (@RequestBody Tema tema)
	{
		return ResponseEntity.ok(repository.save(tema));
	}
	
	/* Deleção de Temas */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id)
	{
		repository.deleteById(id);
	}

}
