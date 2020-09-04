package com.minhaescola.SecretariaEscolar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.minhaescola.SecretariaEscolar.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
	
	public List<Turma>	findAllByTurmaContainingIgnoreCase(String turma);

	

	
}
