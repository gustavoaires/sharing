package br.sharing.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.sharing.dao.IAtendimentoDAO;
import br.sharing.message_atribute.Atributo;
import br.sharing.message_atribute.Mensagem;
import br.sharing.model.Aluno;
import br.sharing.model.Atendimento;

@Transactional
@Controller
@RequestMapping("/atendimento")
public class AtendimentoController {

	private IAtendimentoDAO atendimentoDao;

	@Autowired
	public AtendimentoController(IAtendimentoDAO atendimentoDao) {
		this.atendimentoDao = atendimentoDao;
	}
	
	@RequestMapping("/pedirAjuda")
	public String pedirAjuda() {
		return "/atendimento/pedir_ajuda";
	}
	
	@RequestMapping("/enviarSolicitacao")
	public String enviarSolicitacao(String idAjudante, 
			@DateTimeFormat(pattern="dd/MM/yyyy") Date dia, 
			@DateTimeFormat(pattern="HH:mm") Date hora, 
			String local, Model model, HttpSession sessao) {
		try {
			Atendimento a = new Atendimento();
			a.setIdAjudante(idAjudante);
			a.setPediuAjuda((Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO));
			a.setDataAtendimento(dia);
			a.setHoraAtendimento(hora);
			a.setLocalDeEncontro(local);
			a.setStatus("aberto");
			atendimentoDao.save(a);
			model.addAttribute(Atributo.MENSAGEM, Mensagem.INSERIDO);
		} catch(Exception e) {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.N_INSERIDO);
		}
		return "/mensagem";
	}
	
	@RequestMapping("/confirmarAtendimento")
	public String confirmarAtendimento(Long id, Model model, HttpSession sessao) {
		try {
			Aluno al = (Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO);
			Atendimento at = atendimentoDao.findOne(id);
			if (al.getLogin().equals(at.getIdAjudante()) && !at.getStatus().equals("fechado")) {
				at.setStatus("confirmado");
				atendimentoDao.save(at);
				model.addAttribute(Atributo.MENSAGEM, Mensagem.ATENDIMENTO_CONFIRMADO);
			} else
				throw new Exception("Usuário inválido [" + al.getLogin() + "] tentou confirmar atendimento "
						+ "[" + at.getId() + "]");
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute(Atributo.MENSAGEM, Mensagem.ERRO);
		}
		return "/aluno/home";
	}
	
	@RequestMapping("/negarAtendimento")
	public String negarAtendimento(Long id, Model model, HttpSession sessao) {
		try {
			Aluno al = (Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO);
			Atendimento at = atendimentoDao.findOne(id);
			if (al.getLogin().equals(at.getIdAjudante()) && !at.getStatus().equals("fechado")) {
				at.setStatus("negado");
				atendimentoDao.save(at);
				model.addAttribute(Atributo.MENSAGEM, Mensagem.ATENDIMENTO_NEGADO);
			} else
				throw new Exception("Usuário inválido [" + al.getLogin() + "] tentou negar atendimento "
						+ "[" + at.getId() + "]");
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute(Atributo.MENSAGEM, Mensagem.ERRO);
		}
		return "/aluno/home";
	}
	
	@RequestMapping("/cancelarAtendimento")
	public String cancelarAtendimento(Long id, Model model, HttpSession sessao) {
		try {
			Aluno al = (Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO);
			Atendimento at = atendimentoDao.findOne(id);
			if (al.getLogin().equals(at.getIdPediuAjuda())) {
				at.setStatus("cancelado");
				atendimentoDao.save(at);
				model.addAttribute(Atributo.MENSAGEM, Mensagem.ATENDIMENTO_FECHADO);
			} else
				throw new Exception("Usuário inválido [" + al.getLogin() + "] tentou cancelar atendimento "
						+ "[" + at.getId() + "]");
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute(Atributo.MENSAGEM, Mensagem.ERRO);
		}
		return "/aluno/home";
	}
	
	/**
	 * 
	 * @param id do atendimento
	 * @param nota dada pelo aluno
	 * @param model
	 * @param sessao
	 * @return para a pagina de feedback
	 */
	@RequestMapping("/avaliarAtendimento")
	public String avaliarAtendimento(Long id, Integer nota, Model model, HttpSession sessao) {
		try {
			Aluno al = (Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO);
			Atendimento at = atendimentoDao.findOne(id);
			if (al.getLogin().equals(at.getIdPediuAjuda()) && at.getStatus().equals("confirmado")) {
				at.setNota(nota);
				at.setStatus("avaliado");
				atendimentoDao.save(at);
				model.addAttribute(Atributo.ATENDIMENTO_AVALIADO, Mensagem.ATENDIMENTO_AVALIADO);
			} else
				throw new Exception("Usuário inválido [" + al.getLogin() + "] tentou avaliar atendimento "
						+ "[" + at.getId() + "]");
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute(Atributo.ERRO, Mensagem.ERRO);
		}
		return "/mensagem";
	}

	/**
	 * 
	 * @param status para filtrar os atendimentos, devera ser definido nas paginas
	 * @param sessao
	 * @param model
	 * @return para a pagina de feedback
	 */
	@RequestMapping("/verMeusPedidosAtendimentos")
	public String verMeusPedidosAtendimentos(String status, HttpSession sessao, Model model) {
		try {
			Aluno al = (Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO);
			List<Atendimento> atendimentos = atendimentoDao.findByLoginAndStatus(al.getLogin(), status);
			model.addAttribute(Atributo.MEUS_PEDIDOS_ATENDIMENTOS, atendimentos);
			return "/atendimento/" + status;
		} catch(Exception e) {
			model.addAttribute(Atributo.ERRO, Mensagem.ERRO);
			return "/mensagem";
		}
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
	
	public List<Atendimento> getPedidosAtendimentoPorStatus(String login, String status) {
		List<Atendimento> atendimentos = atendimentoDao.findByLoginAndStatus(login, status);
		return atendimentos; 
	}
}
