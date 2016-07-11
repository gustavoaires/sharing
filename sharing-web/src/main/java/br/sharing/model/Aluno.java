package br.sharing.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity(name="aluno")
public class Aluno {
	@Id
	@Column(name="id_aluno")
	private String login;
	@NotNull
	private String email;
	@NotNull
	private String senha;
	@NotNull
	private String primeiroNome;
	@NotNull
	private String sobrenome;
	private String descricao;
	@NotNull
	private String horariosDisponiveis;
	private Double media;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="aluno_disciplina",
		joinColumns=@JoinColumn(name="id_aluno", referencedColumnName="id_aluno"),
		inverseJoinColumns=@JoinColumn(name="id_disciplina", referencedColumnName="id_disciplina"))
	private List<Disciplina> disciplinas;
	
	/*
	 * Relacionando atendimentos dados
	 */
	@OneToMany(mappedBy="ajudante", targetEntity=Atendimento.class, fetch=FetchType.EAGER)
	private List<Atendimento> atendimentosDados;
	
	/*
	 * Relacionando atendimentos recebidos
	 */
	@OneToMany(mappedBy="pediuAjuda", targetEntity=Atendimento.class, fetch=FetchType.EAGER)
	private List<Atendimento> atendimentosRecebidos;
	
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
	public String getHorariosDisponiveis() {
		return horariosDisponiveis;
	}
	public void setHorariosDisponiveis(String horariosDisponiveis) {
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
	public Double getMedia() {
		return media;
	}
	public void setMedia(Double media) {
		this.media = media;
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
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void addDisciplina(Disciplina disciplina) {
		if (!temDisciplina(disciplina))
			disciplinas.add(disciplina);
	}
	public void removeDisciplina(Disciplina disc) {
		List<Disciplina> discs = new ArrayList<>();
		for (Disciplina d : disciplinas)
			if (d.equals(disc))
				discs.add(d);
		for (Disciplina d : discs)
			disciplinas.remove(d);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean temDisciplina(Disciplina disc) {
		for (Disciplina d : disciplinas)
			if (d.equals(disc))
				return true;
		return false;
	}
	@Override
	public boolean equals(Object obj) {
		Aluno other;
		if (obj instanceof Aluno)
			other = (Aluno)obj;
		else return false;
		if (other.getLogin().equals(this.login))
			return true;
		else return false;
	}
}
