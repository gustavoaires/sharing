package br.sharing.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.sharing.model.Atendimento;

public interface IAtendimentoDAO extends JpaRepository<Atendimento, Long> {

}
