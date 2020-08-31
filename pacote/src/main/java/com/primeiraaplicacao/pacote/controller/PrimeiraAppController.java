package com.primeiraaplicacao.pacote.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/pessoas")
public class PrimeiraAppController {

	@GetMapping
	public String getEu() {
		return "Por mais que eu seja uma pessoa que goste de ficar sozinha, refletindo sobre milhares de coisas, não posso negar a influência que as demais pessoas têm em minha particular existência! ";
	}
	
	@GetMapping("/notas")
	public String getPessoas() {
		return	"Alguns grupos são como estímulos reforçadores que nos fornecem o contexto necessário para permanecermos firmes diante de situações adversas, são eles como amigos, professores, instrutores... Proporcionam as melhores ferramentas capazes de ampliar nossa perspectiva e possibilitar novas ideias ou até reformulações de ideias antigas.";
	}
	
	@GetMapping("/momentos")
	public String getMomentos() {
		return	"Lembro-me de episódios que foram cruciais em minha vida, nos quais pude compartilhar sentimentos e momentos dos mais variados com diversas pessoas e pessoas diversas..."
			+ "\nOs lugares em que decorreram esses momentos reformulam as lembranças destes, citando alguns deles: \n1) Impacta \n2) Sercom \n3) Etec \n4) Faculdade \n5) Em casas \n6) Entre outros...  ";	
	}
	
	
}
