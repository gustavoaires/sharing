package br.sharing.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.sharing.model.Atendimento;

public interface IAtendimentoDAO extends JpaRepository<Atendimento, Long> {

	@Query("select a from atendimento a where a.id_ajudante = :login and a.status = :status")
	public List<Atendimento> findByLoginAndStatus(String login, String status);
}
