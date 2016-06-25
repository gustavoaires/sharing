package br.sharing.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.sharing.model.Disciplina;

public interface IDisciplinaDAO extends JpaRepository<Disciplina, Long> {

	@Query("select d from disciplina d where d.id_instituicao = :id")
	public List<Disciplina> findByIdInstituicao(Long id);
}
