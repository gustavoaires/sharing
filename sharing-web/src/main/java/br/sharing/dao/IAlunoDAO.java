package br.sharing.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.sharing.model.Aluno;

public interface IAlunoDAO extends JpaRepository<Aluno, String> {

	public Aluno findByLogin(String login);
	
	/*
	 * precisa testar
	 */
	@Query(value="SELECT * FROM aluno AS a JOIN aluno_disciplina AS a_d "
			+ "ON a_d.id_aluno = a.id_aluno AND a_d.id_disciplina = ?1", nativeQuery=true)
	public List<Aluno> findByIdDisciplina(Long id);
}
