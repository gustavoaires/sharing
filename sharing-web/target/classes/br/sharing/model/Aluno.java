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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="aluno")
public class Aluno {
	@Id
	@Column(name="id_aluno")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String login;
	private String senha;
	private String primeiroNome;
	private String segundoNome;
	
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
	
	
	/*
	 * Relacionando horarios disponiveis
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="aluno_disponivel",
		joinColumns=@JoinColumn(name="id_aluno", referencedColumnName="id_aluno"),
		inverseJoinColumns=@JoinColumn(name="id_dia_hora_min", referencedColumnName="id_dia_hora_min"))
	private List<DiaHoraMin> horariosDisponiveis;
	
	/*
	 * Relacionando a instituicao
	 */
	@Column(name="id_instituicao", insertable=false, nullable=false, updatable=false)
	private Long idInstituicao;
	@ManyToOne(optional=false)
	@JoinColumn(name="id_instituicao", referencedColumnName="id_instituicao")
	private Instituicao instituicao;
	
	public Long getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public Instituicao getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPrimeiroNome() {
		return primeiroNome;
	}
	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}
	public String getSegundoNome() {
		return segundoNome;
	}
	public void setSegundoNome(String segundoNome) {
		this.segundoNome = segundoNome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
