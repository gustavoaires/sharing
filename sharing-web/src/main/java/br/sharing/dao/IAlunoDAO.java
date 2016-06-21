package br.sharing.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.sharing.model.Aluno;

public interface IAlunoDAO extends JpaRepository<Aluno, Long> {

	public Aluno findByLoginLike(String login);
}
