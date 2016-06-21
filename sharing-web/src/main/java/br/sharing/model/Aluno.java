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

@Entity(name="aluno")
public class Aluno {

	@Id
	@Column(name="id_aluno")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String login;
	private String senha;
	private String nome;
	private String email;
	
	@ManyToMany(mappedBy="alunos", fetch=FetchType.LAZY)
	private List<Disciplina> disciplinas;
	
	/*
	 * Relacionando atendimentos dados
	 */
	@Column(name="atendimento_dado")
	@OneToMany(mappedBy="ajudante", targetEntity=Atendimento.class, fetch=FetchType.EAGER)
	private List<Atendimento> atendimentosDados;
	
	/*
	 * Relacionando atendimentos recebidos
	 */
	@Column(name="atendimento_recebido")
	@OneToMany(mappedBy="pediuAjuda", targetEntity=Atendimento.class, fetch=FetchType.EAGER)
	private List<Atendimento> atendimentosRecebidos;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="aluno_disponivel",
		joinColumns=@JoinColumn(name="id_aluno", referencedColumnName="id_aluno"),
		inverseJoinColumns=@JoinColumn(name="id_dia_hora_min", referencedColumnName="id_dia_hora_min"))
	private List<DiaHoraMin> horariosDisponiveis;
	
	public List<DiaHoraMin> getHorariosDisponiveis() {
		return horariosDisponiveis;
	}
	public void setHorariosDisponiveis(List<DiaHoraMin> horariosDisponiveis) {
		this.horariosDisponiveis = horariosDisponiveis;
	}
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public List<Atendimento> getAtendimentosDados() {
		return atendimentosDados;
	}
	public void setAtendimentosDados(List<Atendimento> atendimentosDados) {
		this.atendimentosDados = atendimentosDados;
	}
	public List<Atendimento> getAtendimentosRecebidos() {
		return atendimentosRecebidos;
	}
	public void setAtendimentosRecebidos(List<Atendimento> atendimentosRecebidos) {
		this.atendimentosRecebidos = atendimentosRecebidos;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
