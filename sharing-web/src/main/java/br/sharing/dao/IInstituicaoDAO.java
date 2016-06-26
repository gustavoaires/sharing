package br.sharing.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.sharing.model.Instituicao;

public interface IInstituicaoDAO extends JpaRepository<Instituicao, Long> {

}
