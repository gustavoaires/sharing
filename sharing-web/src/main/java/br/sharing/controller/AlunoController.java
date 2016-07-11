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
		List<Atendimento> atendimentos = atendimentoController.getPedidosAtendimentoRecebidosPorStatus(login, "aberto");
		if (atendimentos.isEmpty())
			model.addAttribute(Atributo.MENSAGEM, Mensagem.N_PEDIDOS_ATENDIMENTOS);
		model.addAttribute(Atributo.PEDIDOS_ATENDIMENTO, atendimentos);
		return "/aluno/home";
	}
	
	@RequestMapping("/formAlterarPerfil")
	public String formAlterarPerfil(Model model, HttpSession sessao) {
		Aluno aluno = (Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO);
		if (aluno != null) {
			model.addAttribute(Atributo.ALUNO, aluno);
			model.addAttribute(Atributo.INSTITUICOES, instituicaoController.getTodasInstituicoes());
			return "/aluno/form_alterar_perfil";
		} else {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.SESSAO_EXPIRADA);
			return "/mensagem";
		}
	}
	
	@RequestMapping("/alterarPerfil")
	public String alterarPerfil(Aluno aluno, HttpSession sessao, Model model) {
		try {
			Aluno alunoSessao = (Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO);
			//caso o aluno troque de instituicao, reset as disciplinas
			if (aluno.getIdInstituicao().equals(alunoSessao.getIdInstituicao())) {
				alunoSessao.setIdInstituicao(aluno.getIdInstituicao());
				aluno.setDisciplinas(null);
			}
			alunoSessao.setPrimeiroNome(aluno.getPrimeiroNome());
			alunoSessao.setSobrenome(aluno.getSobrenome());
			alunoSessao.setDescricao(aluno.getDescricao());
			alunoSessao.setHorariosDisponiveis(aluno.getHorariosDisponiveis());
			alunoSessao.setEmail(aluno.getEmail());
			alunoDao.save(alunoSessao);
			model.addAttribute(Atributo.MENSAGEM, Mensagem.ALTERADO);
			
		} catch(Exception e) {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.N_ALTERAR_PERFIL);
		}
		return "/aluno/form_alterar_perfil";
	}
	
	@RequestMapping("/pedirAjuda")
	public String pedirAjuda(String idAjudante, Model model) {
		Aluno aluno = alunoDao.findOne(idAjudante);
		model.addAttribute(Atributo.ALUNO, aluno);
		return "/aluno/pedir_ajuda";
	}
	
	@RequestMapping("/formAlterarSenha")
	public String formAlterarSenha() {
		return "/aluno/form_alterar_senha";
	}
	
	@RequestMapping("/alterarSenha")
	public String alterarSenha(@RequestParam("senhaAtual") String senhaAtual,
			@RequestParam("novaSenha") String novaSenha, HttpSession sessao, Model model) {
		Aluno a = (Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO);
		if (a.getSenha().equals(Criptografia.criptografar(senhaAtual))) {
			a.setSenha(Criptografia.criptografar(novaSenha));
			alunoDao.save(a);
			model.addAttribute(Atributo.MENSAGEM, Mensagem.SENHA_ALTERADA);
		}
		else {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.N_ALTERAR_SENHA);
		}
		return "/aluno/form_alterar_senha";
	}
	
	@RequestMapping("/listarMinhasDisciplinas")
	public String listarMinhasDisciplinas(Model model, HttpSession sessao) {
		Aluno aluno = (Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO);
		Long idInst = aluno.getIdInstituicao();
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
	@RequestMapping("/adicionarMinhaDisciplina")
	public String adicionarMinhaDisciplina(Long id, HttpSession sessao, HttpServletResponse resposta) {
		Disciplina disc = disciplinaController.getDisciplinaPorId(id);
		Aluno a = ((Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO));
		if (!a.temDisciplina(disc))
			a.addDisciplina(disc);
		else
			a.removeDisciplina(disc);
		alunoDao.removerRepeticao(disc.getId(), a.getLogin());
		for (Disciplina d : a.getDisciplinas())
			alunoDao.removerRepeticao(d.getId(), a.getLogin());
		alunoDao.save(a);
		return "redirect:/aluno/listarMinhasDisciplinas";
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
	
	@RequestMapping("/pageAlterarFoto")
	public String pageAlterarFoto() {
		return "aluno/page_alterar_foto";
	}
	
	@RequestMapping("/alterarFoto")
	public String alterarFoto(@RequestParam(value="image", required=false) MultipartFile image,
			HttpSession sessao, Model model) {
		try {
			if (image != null && !image.isEmpty()) {
				Aluno a = (Aluno)sessao.getAttribute(Atributo.ALUNO_LOGADO);
				String pathName = servletContext.getRealPath("/") + "images/" + a.getLogin() + ".png";
				FileUtil.saveImage(pathName, image);
				model.addAttribute(Atributo.MENSAGEM, Mensagem.FOTO_ALTERADA);
			}
		} catch(Exception e) {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.FOTO_N_ALTERADA);
		}
		return "/aluno/page_alterar_foto";
	}
	
	@RequestMapping("/perfil")
	public String perfil(String login, Model model) {
		try {
			List<Atendimento> confirmados = 
					atendimentoController.getPedidosAtendimentoRecebidosPorStatus(login, "confirmado");
			model.addAttribute(Atributo.ATENDIMENTOS_CONFIRMADOS, confirmados);
			List<Atendimento> avaliados = 
					atendimentoController.getPedidosAtendimentoRecebidosPorStatus(login, "avaliado");
			model.addAttribute(Atributo.ATENDIMENTOS_AVALIADOS, avaliados);
			List<Atendimento> negados =
					atendimentoController.getPedidosAtendimentoRecebidosPorStatus(login, "negado");
			model.addAttribute(Atributo.ATENDIMENTOS_NEGADOS, negados);
			List<Disciplina> disciplinas =
					disciplinaController.getDisciplinaPorAluno(login);
			model.addAttribute(Atributo.DISCIPLINAS, disciplinas);
			Aluno a = alunoDao.findOne(login);
			model.addAttribute(Atributo.ALUNO, a);
		} catch(Exception e) {
			model.addAttribute(Atributo.MENSAGEM, Mensagem.ERRO);
		}
		return "/aluno/perfil";
	}
}