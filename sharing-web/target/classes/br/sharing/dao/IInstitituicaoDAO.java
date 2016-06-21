package br.sharing.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.sharing.model.Instituicao;

public interface IInstitituicaoDAO extends JpaRepository<Instituicao, Long> {

}
