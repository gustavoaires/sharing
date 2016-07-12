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

import br.sharing.dao.IAlunoDAO;
import br.sharing.dao.IAtendimentoDAO;
import br.sharing.dao.IDisciplinaDAO;
import br.sharing.message_atribute.Atributo;
import br.sharing.message_atribute.Mensagem;
import br.sharing.model.Aluno;
import br.sharing.model.Atendimento;
import br.sharing.model.Disciplina;

@Transactional
@Controller
@RequestMapping("/atendimento")
public class AtendimentoController {

	private IAtendimentoDAO atendimentoDao;
	private IAlunoDAO alunoDao;
	private IDisciplinaDAO disciplinaDao;

	@Autowired
	public AtendimentoController(IAtendimentoDAO atendimentoDao, IAlunoDAO alunoDao,
			IDisciplinaDAO disciplinaDao) {
		this.atendimentoDao = atendimentoDao;
		this.alunoDao = alunoDao;
		this.disciplinaDao = disciplinaDao;
	}
	
	@RequestMapping("/cadastrarSolicitacao")
	public String cadastrarSolicitacao(String idAjudante, 
			@DateTimeFormat(pattern="dd/MM/yyyy") Date dia, 
			@DateTimeFormat(pattern="HH:mm") Date hora, Long idDisciplina, 
			String local, Model model, HttpSession sessao) {
		try {
			Atendimento at = new Atendimento();
			Aluno al = alunoDao.findOne(idAjudante);
			Disciplina d = disciplinaDao.findOne(idDisciplina);
			at.setIdAjudante(al.getLogin());
			at.setAjudante(al);
			at.setDisciplina(d);
			at.setIdPediuAjuda(((Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO)).getLogin());
			at.setPediuAjuda((Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO));
			at.setDataAtendimento(dia);
			at.setHoraAtendimento(hora);
			at.setLocalDeEncontro(local);
			atendimentoDao.save(at);
			model.addAttribute(Atributo.MENSAGEM, Mensagem.PEDIDO_ATENDIMENTO_SUCESSO);
		} catch(Exception e) {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.N_INSERIDO);
		}
		return "/aluno/home";
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
			Date agora = new Date();
			if (al.getLogin().equals(at.getIdPediuAjuda()) && at.getStatus().equals("confirmado")
					&& agora.after(at.getDataAtendimento())) {
				at.setNota(nota);
				at.setStatus("avaliado");
				atendimentoDao.save(at);
				return "redirect:/aluno/verMeusPedidosAtendimentosFeitos";
			} 
			else
				throw new Exception("Usuário inválido [" + al.getLogin() + "] tentou avaliar atendimento "
						+ "[" + at.getId() + "]");
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute(Atributo.MENSAGEM, Mensagem.ERRO_AVALIAR_ATENDIMENTO);
			return "/aluno/home";
		}		
	}

	/**
	 * @param status para filtrar os atendimentos, devera ser definido nas paginas
	 * @param sessao
	 * @param model
	 * @return para a pagina de feedback
	 */
	@RequestMapping("/verMeusPedidosAtendimentosFeitos")
	public String verMeusPedidosAtendimentosFeitos(String status, HttpSession sessao, Model model) {
		try {
			Aluno al = (Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO);
			List<Atendimento> atendimentos = atendimentoDao.findByLoginAndStatusFeitos(al.getLogin(), status);
			model.addAttribute(Atributo.PEDIDOS_ATENDIMENTO, atendimentos);
			model.addAttribute(Atributo.MENSAGEM, Mensagem.ATENDIMENTOS_FEITOS_STATUS);
			model.addAttribute(Atributo.STATUS, status);
			return "/atendimento/" + status;
		} catch(Exception e) {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.ERRO);
			return "/mensagem";
		}
	}
	
	@RequestMapping("/verMeusPedidosAtendimentosRecebidos")
	public String verMeusPedidosAtendimentosRecebidos(String status, HttpSession sessao, Model model) {
		try {
			Aluno al = (Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO);
			List<Atendimento> atendimentos = atendimentoDao.findByLoginAndStatusRecebidos(al.getLogin(), status);
			model.addAttribute(Atributo.PEDIDOS_ATENDIMENTO, atendimentos);
			model.addAttribute(Atributo.MENSAGEM, Mensagem.ATENDIMENTOS_RECEBIDOS_STATUS);
			model.addAttribute(Atributo.STATUS, status);
			return "/atendimento/" + status;
		} catch(Exception e) {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.ERRO);
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
	
	public List<Atendimento> getPedidosAtendimentoRecebidosPorStatus(String login, String status) {
		List<Atendimento> atendimentos = atendimentoDao.findByLoginAndStatusRecebidos(login, status);
		return atendimentos; 
	}	
}
