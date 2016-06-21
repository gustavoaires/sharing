package br.sharing.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name="disciplina")
public class Disciplina {

	@Id
	@Column(name="id_disciplina")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nome;

	/*
	 * Relacionando a disciplina com seus cursos
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="curso_disciplina", 
		joinColumns=@JoinColumn(name="id_disciplina", referencedColumnName="id_disciplina"),
		inverseJoinColumns=@JoinColumn(name="id_curso", referencedColumnName="id_curso"))
	private List<Curso> cursos;
	
	/*
	 * Relacionando os atendimentos desta disciplina
	 */
	@OneToMany(mappedBy="disciplina", targetEntity=Atendimento.class, fetch=FetchType.EAGER)
	private List<Atendimento> atendimentos;
	
	/*
	 * Relacionando essa disciplina com seus alunos
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="aluno_disciplina",
		joinColumns=@JoinColumn(name="id_disciplina", referencedColumnName="id_disciplina"),
		inverseJoinColumns=@JoinColumn(name="id_aluno", referencedColumnName="id_aluno"))
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
	public List<Curso> getCurso() {
		return cursos;
	}
	public void setCursos(List<Curso> curso) {
		this.cursos = curso;
	}
	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}
	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}
	public List<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	public List<Curso> getCursos() {
		return cursos;
	}
}
