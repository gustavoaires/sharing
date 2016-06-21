package br.sharing.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.sharing.dao.ICursoDAO;
import br.sharing.message.Mensagem;
import br.sharing.model.Curso;

@Transactional
@Controller("/curso")
public class CursoController {

	@Autowired
	private ICursoDAO cursoDao;
	
	@RequestMapping("/formInserir")
	public String formInserir() {
		return "/curso/form_inserir";
	}
	
	@RequestMapping("/inserir")
	public String inserir(Model model) {
		try {
			model.addAttribute("mensagem", Mensagem.INSERIDO);
		} catch(Exception e) {
			model.addAttribute("mensagem", Mensagem.N_INSERIDO);
		}
		return "/mensagem";
	}

	@RequestMapping("/listar")
	public String listar(Model model) {
		try {
			List<Curso> cursos = cursoDao.findAll();
			model.addAttribute("cursos", cursos);
			return "/curso/listar";
		} catch(Exception e) {
			model.addAttribute("mensagem", Mensagem.N_LISTAR);
			return "/mensagem";
		}
	}
	
	@RequestMapping("/formAlterar")
	public String formAlterar(Model model, Long id) {
		try {
			Curso curso = cursoDao.findOne(id);
			model.addAttribute("curso", curso);
			return "/curso/form_alterar";
		} catch(Exception e) {
			model.addAttribute("mensagem",  Mensagem.BUSCAR_ALTERAR);
			return "/mensagem";
		}
		
	}
	
	@RequestMapping("/alterar")
	public String alterar(Model model) {
		try {
			model.addAttribute("mensagem", Mensagem.ALTERADO);
		} catch(Exception e) {
			model.addAttribute("mensagem", Mensagem.N_ALTERADO);
		}
		return "/mensagem";
	}
	
	@RequestMapping("/remover")
	public String remover(Model model, Long id) {
		try {
			cursoDao.delete(id);
			model.addAttribute("mensagem", Mensagem.REMOVIDO);
		} catch(Exception e) {
			model.addAttribute("mensagem", Mensagem.N_REMOVIDO);
		}
		return "/mensagem";
	}
}
