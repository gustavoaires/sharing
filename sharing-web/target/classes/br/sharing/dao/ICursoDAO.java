package br.sharing.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.sharing.model.Curso;

public interface ICursoDAO extends JpaRepository<Curso, Long> {

}
