package br.sharing.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.sharing.model.Disciplina;

public interface IDisciplinaDAO extends JpaRepository<Disciplina, Long> {

}
