package br.sharing.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.sharing.model.Aluno;

public interface IAlunoDAO extends JpaRepository<Aluno, String> {

	public Aluno findByLoginLike(String login);
	
	@Query("select a from aluno a, aluno_disciplina a_d "
			+ "where a_d.id_disciplina = :param_id and a_d.id_aluno = a.id_aluno")
	public List<Aluno> findByIdDisciplina(@Param("param_id") Long id);
}
