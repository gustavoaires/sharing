package br.sharing.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.sharing.model.Disciplina;

public interface IDisciplinaDAO extends JpaRepository<Disciplina, Long> {

	@Query("select d from disciplina d where d.idInstituicao = :id")
	public List<Disciplina> findByIdInstituicao(@Param("id") Long id);
}
