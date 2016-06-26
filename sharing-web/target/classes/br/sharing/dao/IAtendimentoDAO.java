package br.sharing.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.sharing.model.Atendimento;

public interface IAtendimentoDAO extends JpaRepository<Atendimento, Long> {

	@Query("select a from atendimento a where a.id_ajudante = :login and a.status = :status")
	public List<Atendimento> findByLoginAndStatus(@Param("login") String login, @Param("status") String status);

	@Query("select avg(a.nota) from atendimento a "
			+ "where a.id_disciplina = :id_disc and a.id_ajudante = :login")
	public Double findMediaByIdAluno(@Param("id_disc") Long idDisciplina, @Param("login") String login);
}
