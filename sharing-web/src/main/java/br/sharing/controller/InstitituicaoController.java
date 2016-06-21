package br.sharing.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.sharing.dao.IInstitituicaoDAO;
import br.sharing.message.Mensagem;
import br.sharing.model.Instituicao;

@Transactional
@Controller("/instituicao")
public class InstitituicaoController {

	@Autowired
	private IInstitituicaoDAO instituicaoDao;
	
	@RequestMapping("/formInserir")
	public String formInserir() {
		return "/instituicao/form_inserir";
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
			List<Instituicao> instituicoes = instituicaoDao.findAll();
			model.addAttribute("instituicoes", instituicoes);
			return "/instituicao/listar";
		} catch(Exception e) {
			model.addAttribute("mensagem", Mensagem.N_LISTAR);
			return "/mensagem";
		}
	}
	
	@RequestMapping("/formAlterar")
	public String formAlterar(Model model, Long id) {
		try {
			Instituicao instituicao = instituicaoDao.findOne(id);
			model.addAttribute("instituicao", instituicao);
			return "/instituicao/form_alterar";
		} catch(Exception e) {
			model.addAttribute("mensagem", Mensagem.BUSCAR_ALTERAR);
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
			instituicaoDao.delete(id);
			model.addAttribute("mensagem", Mensagem.REMOVIDO);
		} catch(Exception e) {
			model.addAttribute("mensagem", Mensagem.N_REMOVIDO);
		}
		return "/mensagem";
	}
}
