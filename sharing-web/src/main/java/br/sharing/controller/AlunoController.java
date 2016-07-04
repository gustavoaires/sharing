package br.sharing.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.sharing.dao.IAlunoDAO;
import br.sharing.encrypt.Criptografia;
import br.sharing.message_atribute.Atributo;
import br.sharing.message_atribute.Mensagem;
import br.sharing.model.Aluno;
import br.sharing.model.Atendimento;
import br.sharing.model.Disciplina;
import br.sharing.model.Instituicao;
import br.sharing.util.FileUtil;

@Transactional
@Controller
@RequestMapping("/aluno")
public class AlunoController {
	
	private IAlunoDAO alunoDao;
	private InstituicaoController instituicaoController;
	private AtendimentoController atendimentoController;
	private DisciplinaController disciplinaController;
	private ServletContext servletContext;
	
	@Autowired
	public AlunoController(IAlunoDAO alunoDao, InstituicaoController instituicaoController,
			AtendimentoController atendimentoController, DisciplinaController disciplinaController,
			ServletContext servletContext) {
		this.alunoDao = alunoDao;
		this.atendimentoController = atendimentoController;
		this.disciplinaController = disciplinaController;
		this.instituicaoController = instituicaoController;
		this.servletContext = servletContext;
	}
		
	@RequestMapping("/home")
	public String home(Model model, HttpSession sessao) {
		String login = ((Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO)).getLogin();
		List<Atendimento> atendimentos = atendimentoController.getPedidosAtendimentoPorStatus(login, "aberto");
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
	
	/**
	 * Request para a função ajax de atualização de disciplina
	 * @param id
	 * @param sessao
	 * @param resposta
	 */
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
		Aluno aluno = alunoDao.findByLogin(candidato.getLogin());
		
		if (aluno == null) {
			candidato.setSenha(Criptografia.criptografar(candidato.getSenha()));
			candidato.setInstituicao(instituicaoController.getInstituicaoPorId(idInstituicao));
			alunoDao.save(candidato);
			sessao.setAttribute(Atributo.ALUNO_LOGADO, candidato);
			return "redirect:/aluno/home";
		} else {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.USUARIO_SENHA);
			model.addAttribute(Atributo.ALUNO, candidato);
			return "/aluno/form_cadastrar";
		}
	}
	
	@RequestMapping("/fotoPerfil")
	public String fotoPerfil(@RequestParam(value="image", required=false) MultipartFile image, HttpSession sessao) {
		if (image != null && !image.isEmpty()) {
			Aluno a = (Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO);
			String pathName = servletContext.getRealPath("/") + "images/" + a.getLogin() + ".png";
			FileUtil.saveImage(pathName, image);
		}
		return "/mensagem";
	}
	
	@RequestMapping("/perfil")
	public String perfil(String login, Model model) {
		try {
			List<Atendimento> confirmados = 
					atendimentoController.getPedidosAtendimentoPorStatus(login, "confirmados");
			model.addAttribute(Atributo.ATENDIMENTOS_CONFIRMADOS, confirmados);
			List<Atendimento> negados =
					atendimentoController.getPedidosAtendimentoPorStatus(login, "negados");
			model.addAttribute(Atributo.ATENDIMENTOS_NEGADOS, negados);
			List<Disciplina> disciplinas =
					disciplinaController.getDisciplinaPorAluno(login);
			model.addAttribute(Atributo.DISCIPLINAS, disciplinas);
			return "aluno/perfil";
		} catch(Exception e) {
			model.addAttribute(Atributo.ERRO, Mensagem.ERRO);
			return "/mensagem";
		}
	}
}
