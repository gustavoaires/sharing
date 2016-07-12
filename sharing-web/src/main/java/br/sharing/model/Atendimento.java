package br.sharing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name="atendimento")
public class Atendimento {
	
	@Id
	@Column(name="id_atendimento")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Integer nota;
	private String status;
	private String localDeEncontro;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataAtendimento; 
	
	@DateTimeFormat(pattern = "HH:mm")
	private Date horaAtendimento;
	
	public Atendimento() {
		this.nota = 0;
		this.status = "aberto";
	}
	
	/*
	 * Relacionando disciplina do atendimento
	 */
	@Column(name="id_disciplina", insertable=false, updatable=false, nullable=false)
	private Long idDisciplina;
	@ManyToOne(optional=false)
	@JoinColumn(name="id_disciplina", referencedColumnName="id_disciplina")
	private Disciplina disciplina;
		
	/*
	 * Relacionando aluno que ajudou
	 */
	@Column(name="id_ajudante", insertable=false, updatable=false, nullable=false)
	private String idAjudante;
	@ManyToOne(optional=false)
	@JoinColumn(name="id_ajudante", referencedColumnName="id_aluno")
	private Aluno ajudante;
	
	/*
	 * Relacionando aluno que pediu ajuda
	 */
	@Column(name="id_pediu_ajuda", insertable=false, updatable=false, nullable=false)
	private String idPediuAjuda;
	@ManyToOne(optional=false)
	@JoinColumn(name="id_pediu_ajuda", referencedColumnName="id_aluno")
	private Aluno pediuAjuda;
	
	public Long getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public Aluno getAjudante() {
		return ajudante;
	}
	public void setAjudante(Aluno ajudante) {
		this.ajudante = ajudante;
	}
	public Aluno getPediuAjuda() {
		return pediuAjuda;
	}
	public void setPediuAjuda(Aluno pediuAjuda) {
		this.pediuAjuda = pediuAjuda;
	}
	public Integer getNota() {
		return nota;
	}
	public void setNota(Integer nota) {
		this.nota = nota;
	}
	public String getIdAjudante() {
		return idAjudante;
	}
	public void setIdAjudante(String idAjudante) {
		this.idAjudante = idAjudante;
	}
	public String getIdPediuAjuda() {
		return idPediuAjuda;
	}
	public void setIdPediuAjuda(String idPediuAjuda) {
		this.idPediuAjuda = idPediuAjuda;
	}
	public Date getDataAtendimento() {
		return dataAtendimento;
	}
	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}
	public Date getHoraAtendimento() {
		return horaAtendimento;
	}
	public void setHoraAtendimento(Date horaAtendimento) {
		this.horaAtendimento = horaAtendimento;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLocalDeEncontro() {
		return localDeEncontro;
	}
	public void setLocalDeEncontro(String localDeEncontro) {
		this.localDeEncontro = localDeEncontro;
	}
}