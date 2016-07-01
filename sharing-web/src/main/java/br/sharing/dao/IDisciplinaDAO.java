package br.sharing.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.sharing.model.Disciplina;

public interface IDisciplinaDAO extends JpaRepository<Disciplina, Long> {

	@Query("select d from disciplina d where d.idInstituicao = :id")
	public List<Disciplina> findByIdInstituicao(@Param("id") Long id);
	
	/*
	 * precisa testar
	 */
	@Query(value = "SELECT * FROM disciplina AS d LEFT JOIN aluno_disciplina AS a_d "
			+ "ON a_d.id_disciplina = d.disciplina AND WHERE a_d.id_aluno = ?0", nativeQuery=true)
	public List<Disciplina> findByLogin(String login);
}
