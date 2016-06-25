package br.sharing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.sharing.dao.IDisciplinaDAO;
import br.sharing.messageAtribute.Atributo;
import br.sharing.messageAtribute.Mensagem;
import br.sharing.model.Disciplina;

@Transactional
@Controller
@RequestMapping("/disciplina")
public class DisciplinaController {

	@Autowired
	private IDisciplinaDAO disciplinaDao;
	
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
	public String listar(Model model) {
		try {
			List<Disciplina> disciplinas = disciplinaDao.findAll();
			model.addAttribute(Atributo.DISCIPLINAS, disciplinas);
			return "/disciplina/listar";
		} catch(Exception e) {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.N_LISTAR);
			return "/mensagem";
		}
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
		return disciplinaDao.findAll();
	}

	public Disciplina getDisciplinaPorId(Long id) {
		return disciplinaDao.findOne(id);
	}
}
