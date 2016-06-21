package br.sharing.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name="curso")
public class Curso {

	@Id
	@Column(name="id_curso")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nome;
	
	/*
	 * Relacionando curso com suas disciplinas
	 */
	@ManyToMany(mappedBy="cursos", fetch=FetchType.LAZY)
	private List<Disciplina> disciplinas;
	
	/*
	 * Relacionando o curso com sua instituicao
	 */
	@Column(name="id_instituicao", insertable=false, nullable=false, updatable=false)
	private Long idInstituicao;
	@ManyToOne(optional=false)
	@JoinColumn(name="id_instituicao", referencedColumnName="id_instituicao")
	private Instituicao instituicao;
	
	public Instituicao getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
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
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
}
