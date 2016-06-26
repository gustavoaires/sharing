package br.sharing.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.sharing.dao.IAlunoDAO;
import br.sharing.encrypt.Criptografia;
import br.sharing.message_atribute.Atributo;
import br.sharing.message_atribute.Mensagem;
import br.sharing.model.Aluno;
import br.sharing.model.Atendimento;
import br.sharing.model.Disciplina;
import br.sharing.model.Instituicao;

@Transactional
@Controller
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	private IAlunoDAO alunoDao;
	
	@Autowired
	private InstituicaoController instituicaoController;
	
	@Autowired
	private AtendimentoController atendimentoController;
	
	@Autowired
	private DisciplinaController disciplinaController;
		
	@RequestMapping("/home")
	public String home(Model model, HttpSession sessao) {
		String login = ((Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO)).getLogin();
		List<Atendimento> atendimentos = atendimentoController.getPedidosAtendimentoEmAberto(login);
		model.addAttribute(Atributo.PEDIDOS_ATENDIMENTO, atendimentos);
		return "/aluno/home";
	}
	
	@RequestMapping("/formAlterarPerfil")
	public String formAlterarPerfil(Model model, HttpSession sessao) {
		Aluno aluno = (Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO);
		if (aluno != null) {
			model.addAttribute(Atributo.ALUNO, aluno);
			return "/aluno/form_alterar_perfil";
		} else {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.SESSAO_EXPIRADA);
			return "/erro";
		}
	}
	
	@RequestMapping("/alterarPerfil")
	public String alterarPerfil(Aluno aluno, HttpSession sessao, Model model) {
		try {
			Aluno alunoSessao = (Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO);
			alunoSessao.setPrimeiroNome(aluno.getPrimeiroNome());
			alunoSessao.setSobrenome(aluno.getSobrenome());
			aluno.setLogin(aluno.getLogin());
			aluno.setDescricao(aluno.getDescricao());
			aluno.setHorariosDisponiveis(aluno.getHorariosDisponiveis());
			alunoDao.save(aluno);
		} catch(Exception e) {
			model.addAttribute(Atributo.N_ALTERAR_PERFIL, Mensagem.N_ALTERAR_PERFIL);
			return "/erro";
		}
		return "/aluno/home";
	}
	
	@RequestMapping("/formAlterarSenha")
	public String formAlterarSenha() {
		return "/aluno/form_alterar_senha";
	}
	
	@RequestMapping("/alterarSenha")
	public String alterarSenha(@RequestParam("senha_atual") String senhaAtual,
			@RequestParam("nova_senha") String novaSenha, HttpSession sessao, Model model) {
		Aluno a = (Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO);
		if (a.getSenha().equals(Criptografia.criptografar(senhaAtual)))
			a.setSenha(Criptografia.criptografar(novaSenha));
		else {
			model.addAttribute(Atributo.N_ALTERAR_SENHA, Mensagem.N_ALTERAR_SENHA);
			/*
			 * Verificar se vai dar certo para esse mapping acessar essa mensagem depois do redirect
			 */
			return "redirect:formAlterarSenha";
		}
		model.addAttribute(Atributo.SENHA_ALTERADA, Mensagem.SENHA_ALTERADA);
		return "/aluno/home";
	}
	
	@RequestMapping("/listarMinhasDisciplinas")
	public String listarMinhasDisciplinas(Model model, HttpSession sessao) {
		Long idInst = ((Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO)).getIdInstituicao();
		List<Disciplina> disciplinas = disciplinaController.getDisciplinasPorInstituicao(idInst);
		model.addAttribute(Atributo.DISCIPLINAS, disciplinas);
		return "/aluno/minhas_disciplinas";
	}
	
	@RequestMapping("/selecionarMinhaDisciplina")
	public void selecionarMinhaDisciplina(Long id, HttpSession sessao, HttpServletResponse resposta) {
		Disciplina disc = disciplinaController.getDisciplinaPorId(id);
		Aluno a = ((Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO));
		if (!a.getDisciplinas().contains(disc))
			a.addDisciplina(disc);
		else
			a.removeDisciplina(disc);
		alunoDao.save(a);
		resposta.setStatus(200);
	}
	
	@RequestMapping("/formCadastrar")
	public String formCadastrar(Model model) {
		List<Instituicao> instituicoes = instituicaoController.getTodasInstituicoes();
		model.addAttribute(Atributo.INSTITUICOES, instituicoes);
		return "/aluno/form_cadastrar";
	}
	
	@RequestMapping("/cadastrar")
	public String cadastrar(Aluno candidato, @RequestParam("instituicao") Long idInstituicao,
			Model model, HttpSession sessao) {
		Aluno aluno = alunoDao.findByLoginLike(candidato.getLogin());
		
		if (aluno == null) {
			candidato.setSenha(Criptografia.criptografar(candidato.getSenha()));
			candidato.setInstituicao(instituicaoController.getInstituicaoPorId(idInstituicao));
			alunoDao.save(candidato);
			sessao.setAttribute(Atributo.ALUNO_LOGADO, candidato);
			return "redirect:/aluno/home";
		} else {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.USUARIO_SENHA);
			return "/aluno/form_cadastrar";
		}
	}
}
