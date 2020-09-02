package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
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

@RestController					/* preparação para recebimento de requisições */
@RequestMapping("/postagens")	/* definição da URL responsável pela localização da pag */
@CrossOrigin("*")				/* Referente ao consumo em diferentes API's */ 
public class PostagemController {

	/* Instanciamento da interface pelo Spring possibilidade de acesso aos serviços da Interface */ 
	@Autowired					
	private PostagemRepository repository;
	
	/* @GetMapping >> sempre quando vir uma requisição de consumo da API através da URL "postagens", o método será utilizado */ 
	@GetMapping			
	
	/* criação do método findAll */ 
	public ResponseEntity<List<Postagem>>GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	/* Método para Capturação da variável (id) recebida 
	 * dentro da requisição na URL de Postagens (ex: localhost:8080/postagens/2), 
	 * para retorno de objetos de postagens 
	 * ou um not found caso existam erros na requisição	*/
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	/* Utilização do Método criado no PostagemRepository,
	 * este trará do BD todos os títulos solicitados na requisição ou títulos semelhantes */
	
	@GetMapping("/titulo/{titulo}") 	
	/* Denomina-se uma subrota com atribuição de um novo parâmetro,
	 * evitando duplicidades quanto ao método anterior na requisição em localhost:8080/postagens/titulo/exemplo */
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo) 
	{
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	/* @RequestBody > Captura o objeto no Body */	
	/* Metódo para inserção de dados no Banco de Dados pela requisição localhost:8080/postagens*/ 
	public ResponseEntity<Postagem> post(@RequestBody Postagem postagem) 
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping 
	/* Método de atualização dos dados já inseridos no BD */
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem) 
	{
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	/* Método para deleção de objetos de Postagem pela requisição 
	 							em localhost:8080/postagens/IdDoObjeto */
	public void delete(@PathVariable long id) 
	{
		repository.deleteById(id);
	}
	
	
	
}
