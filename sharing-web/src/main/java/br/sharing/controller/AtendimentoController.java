package br.sharing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.sharing.dao.IAtendimentoDAO;
import br.sharing.message_atribute.Atributo;
import br.sharing.message_atribute.Mensagem;
import br.sharing.model.Atendimento;

@Transactional
@Controller
@RequestMapping("/atendimento")
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
			model.addAttribute(Atributo.MENSAGEM, Mensagem.INSERIDO);
		} catch(Exception e) {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.N_INSERIDO);
		}
		return "/mensagem";
	}

	@RequestMapping("/listar")
	public String listar(Model model) {
		try {
			List<Atendimento> atendimentos = atendimentoDao.findAll();
			model.addAttribute(Atributo.ATENDIMENTOS, atendimentos);
			return "/atendimento/listar";
		} catch(Exception e) {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.N_LISTAR);
			return "/mensagem";
		}
	}
	
	@RequestMapping("/remover")
	public String remover(Model model, Long id) {
		try {
			atendimentoDao.delete(id);
			model.addAttribute(Atributo.MENSAGEM, Mensagem.REMOVIDO);
		} catch(Exception e) {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.N_REMOVIDO);
		}
		return "/mensagem";
	}
	
	public List<Atendimento> getPedidosAtendimentoEmAberto(String login) {
		List<Atendimento> atendimentos = atendimentoDao.findByLoginAndStatus(login, "aberto");
		return atendimentos; 
	}
}
