package br.sharing.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.sharing.dao.IAtendimentoDAO;
import br.sharing.message.Mensagem;
import br.sharing.model.Atendimento;

@Transactional
@Controller("/atendimento")
public class AtendimentoController {

	@Autowired
	private IAtendimentoDAO atendimentoDao;
	
	@RequestMapping("/formInserir")
	public String formInserir() {
		return "/atendimento/form_inserir";
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
			List<Atendimento> atendimentos = atendimentoDao.findAll();
			model.addAttribute("atendimentos", atendimentos);
			return "/atendimento/listar";
		} catch(Exception e) {
			model.addAttribute("mensagem", Mensagem.N_LISTAR);
			return "/mensagem";
		}
	}
	
	@RequestMapping("/remover")
	public String remover(Model model, Long id) {
		try {
			atendimentoDao.delete(id);
			model.addAttribute("mensagem", Mensagem.REMOVIDO);
		} catch(Exception e) {
			model.addAttribute("mensagem", Mensagem.N_REMOVIDO);
		}
		return "/mensagem";
	}
}
