package br.sharing.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name="dia_hora_min")
public class DiaHoraMin {

	public enum Dia {SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA};
	
	@Id
	@Column(name="id_dia_hora_min")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String dia;
	private Integer hora;
	private Integer min;
	
	/*
	 * Relacionando o atendimento com o dia, hora, min
	@OneToMany(mappedBy="diaHoraMin", targetEntity=Atendimento.class, fetch=FetchType.EAGER)
	private List<Atendimento> atendimentos;
	*/
	
	/*
	 * Relacionando os alunos para este dia, horario, min
	 */
	@ManyToMany(mappedBy="horariosDisponiveis", fetch=FetchType.LAZY)
	private List<Aluno> alunos;
	
	/*
	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}
	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}
	*/
	public List<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public Integer getHora() {
		return hora;
	}
	public void setHora(Integer hora) {
		this.hora = hora;
	}
	public Integer getMin() {
		return min;
	}
	public void setMin(Integer min) {
		this.min = min;
	}
}
