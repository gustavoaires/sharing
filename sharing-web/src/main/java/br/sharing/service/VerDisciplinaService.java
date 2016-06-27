package br.sharing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sharing.dao.IAlunoDAO;
import br.sharing.dao.IAtendimentoDAO;
import br.sharing.model.Aluno;

@Service
public class VerDisciplinaService {

	private IAlunoDAO alunoDao;
	private IAtendimentoDAO atendimentoDao;
	
	@Autowired
	public VerDisciplinaService(IAlunoDAO alunoDAO, IAtendimentoDAO atendimentoDao) {
		this.alunoDao = alunoDAO;
		this.atendimentoDao = atendimentoDao;
	}
	
	/*
	 * Qualquer aluno que tenha escolhido a disciplina
	 */
	public List<Aluno> getAlunosDisciplina(Long id) {
		List<Aluno> alunos = null;
		alunos = alunoDao.findByIdDisciplina(id);
		return alunos;
	}
	
	public Double getMediaAluno(Long idDisciplina, String login) {
		return atendimentoDao.findMediaByIdAluno(idDisciplina, login);
	}
}