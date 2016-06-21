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

	/*
	 * Esta faltando o date para a data real do atendimento
	 * Duvida sobre o name e o referencedColumnName no JoinColumn
	 */
	
	@Id
	@Column(name="id_atendimento")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Double nota;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataAtendimendo; 
	
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
	private Long idAjudante;
	@ManyToOne(optional=false)
	@JoinColumn(name="id_ajudante", referencedColumnName="id_aluno")
	private Aluno ajudante;
	
	/*
	 * Relacionando aluno que pediu ajuda
	 */
	@Column(name="id_pediu_ajuda", insertable=false, updatable=false, nullable=false)
	private Long idPediuAjuda;
	@ManyToOne(optional=false)
	@JoinColumn(name="id_pediu_ajuda", referencedColumnName="id_aluno")
	private Aluno pediuAjuda;
	
	/*
	 * Referenciando o dia horario e minuto do atendimento. Se dara de acordo com o aluno
	 * 
	@Column(name="id_dia_hora_min", insertable=false, updatable=false, nullable=false)
	private Long idDiaHoraMin;
	@ManyToOne(optional=false)
	@JoinColumn(name="id_dia_hora_min", referencedColumnName="id_dia_hora_min")
	private DiaHoraMin diaHoraMin;
	*/
	
	public Long getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	/*
	public Long getIdDiaHoraMin() {
		return idDiaHoraMin;
	}
	public void setIdDiaHoraMin(Long idDiaHoraMin) {
		this.idDiaHoraMin = idDiaHoraMin;
	}
	public DiaHoraMin getDiaHoraMin() {
		return diaHoraMin;
	}
	public void setDiaHoraMin(DiaHoraMin diaHoraMin) {
		this.diaHoraMin = diaHoraMin;
	}
	public DiaHoraMin getDiaHora() {
		return diaHoraMin;
	}
	public void setDiaHora(DiaHoraMin diaHoraMin) {
		this.diaHoraMin = diaHoraMin;
	}
	*/
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
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	public Long getIdAjudante() {
		return idAjudante;
	}
	public void setIdAjudante(Long idAjudante) {
		this.idAjudante = idAjudante;
	}
	public Long getIdPediuAjuda() {
		return idPediuAjuda;
	}
	public void setIdPediuAjuda(Long idPediuAjuda) {
		this.idPediuAjuda = idPediuAjuda;
	}
}