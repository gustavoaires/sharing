package br.sharing.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.sharing.dao.IDisciplinaDAO;
import br.sharing.message_atribute.Atributo;
import br.sharing.message_atribute.Mensagem;
import br.sharing.model.Aluno;
import br.sharing.model.Disciplina;
import br.sharing.service.VerDisciplinaService;

@Transactional
@Controller
@RequestMapping("/disciplina")
public class DisciplinaController {

	private IDisciplinaDAO disciplinaDao;
	private VerDisciplinaService verDisciplina;
	
	@Autowired
	public DisciplinaController(IDisciplinaDAO disciplinaDao, VerDisciplinaService verDisciplina) {
		this.disciplinaDao = disciplinaDao;
		this.verDisciplina = verDisciplina;
	}
	
	@RequestMapping("/formInserir")
	public String formInserir() {
		return "/disciplina/form_inserir";
	}
	
	@RequestMapping("/inserir")
	public String inserir(Model model) {
		try {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.INSERIDO);
		} catch(Exception e) {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.N_INSERIDO);
		}
		return "/mensagem";
	}

	@RequestMapping("/listar")
	public String listar(Model model, HttpSession sessao) {
		try {
			Aluno a = (Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO);
			List<Disciplina> disciplinas = disciplinaDao.findByIdInstituicao(a.getIdInstituicao());
			model.addAttribute(Atributo.DISCIPLINAS, disciplinas);
			model.addAttribute(Atributo.MENSAGEM, Mensagem.LISTAR_DISCIPLINAS);
			return "/disciplina/listar";
		} catch(Exception e) {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.N_LISTAR);
			return "/disciplina/listar";
		}
	}
	
	@RequestMapping("/verDisciplina")
	public String verDisciplina(Long id, Model model) {
		List<Aluno> alunos = verDisciplina.getAlunosDisciplina(id);
		Disciplina disciplina = disciplinaDao.findOne(id);
		if (!alunos.isEmpty()) {
			for (Aluno a : alunos)
				a.setMedia(verDisciplina.getMediaAluno(id, a.getLogin()));
			model.addAttribute(Atributo.ALUNOS, alunos);
		} else
			model.addAttribute(Atributo.MENSAGEM, Mensagem.N_ALUNOS);
		model.addAttribute(Atributo.DISCIPLINA, disciplina);
		return "/disciplina/ver_disciplina";
	}
	
	@RequestMapping("/formAlterar")
	public String formAlterar(Model model, Long id) {
		try {
			Disciplina disciplina = disciplinaDao.findOne(id);
			model.addAttribute(Atributo.DISCIPLINA, disciplina);
			return "/disciplina/form_alterar";
		} catch(Exception e) {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.BUSCAR_ALTERAR);
			return "/mensagem";
		}
		
	}
	
	@RequestMapping("/alterar")
	public String alterar(Model model) {
		try {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.ALTERADO);
		} catch(Exception e) {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.N_ALTERADO);
		}
		return "/mensagem";
	}
	
	@RequestMapping("/remover")
	public String remover(Model model, Long id) {
		try {
			disciplinaDao.delete(id);
			model.addAttribute(Atributo.MENSAGEM, Mensagem.REMOVIDO);
		} catch(Exception e) {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.N_REMOVIDO);
		}
		return "/mensagem";
	}
	
	public List<Disciplina> getDisciplinasPorInstituicao(Long id) {
		return disciplinaDao.findByIdInstituicao(id);
	}

	public Disciplina getDisciplinaPorId(Long id) {
		return disciplinaDao.findOne(id);
	}
	
	public List<Disciplina> getDisciplinaPorAluno(String login) {
		return disciplinaDao.findByLogin(login);
	}
}
