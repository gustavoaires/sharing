package br.sharing.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="instituicao")
public class Instituicao {

	@Id
	@Column(name="id_instituicao")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nome;

	/*
	 * Relacionando a instituicao com seus cursos
	 */
	@OneToMany(mappedBy="instituicao", targetEntity=Disciplina.class, fetch=FetchType.EAGER)
	private List<Disciplina> disciplinas;
	
	/*
	 * Relacionando os alunos
	 */
	@OneToMany(mappedBy="instituicao", targetEntity=Aluno.class, fetch=FetchType.EAGER)
	private List<Aluno> alunos;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
