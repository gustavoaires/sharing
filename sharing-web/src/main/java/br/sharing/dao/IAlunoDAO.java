package br.sharing.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.sharing.model.Aluno;

public interface IAlunoDAO extends JpaRepository<Aluno, String> {

	public Aluno findByLoginLike(String login);
	
	/*
	 * corrigir
	 */
	@Query(value="SELECT * from aluno AS a, aluno_disciplina AS a_d "
			+ "WHERE a_d.id_disciplina = ?0 and a_d.id_aluno = a.id_aluno",
			nativeQuery=true)
	public List<Aluno> findByIdDisciplina(Long id);
}
