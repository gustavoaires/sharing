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
	@OneToMany(mappedBy="instituicao", targetEntity=Curso.class, fetch=FetchType.EAGER)
	private List<Curso> cursos;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Curso> getCursos() {
		return cursos;
	}
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
