package br.sharing.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import br.sharing.dao.IAlunoDAO;
import br.sharing.encrypt.Criptografia;
import br.sharing.message_atribute.Atributo;
import br.sharing.model.Aluno;

@Transactional
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private IAlunoDAO alunoDao;
	
	@RequestMapping("/loginAssert")
	public String login(String login, String senha, HttpSession sessao) {
		Aluno candidato = alunoDao.findByLogin(login);
		
		if (candidato != null) {
			if (Criptografia.criptografar(senha).equals(candidato.getSenha())) {
				sessao.setAttribute(Atributo.ALUNO_LOGADO, candidato);
				return "redirect:/aluno/home";
			}
		}
		return "redirect:/index";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate(); 
		return "redirect:/index";
	}
	
	@RequestMapping("/loginAdm")
	public String loginAdm(String login, String senha) {
		return "redirect:/adm/home";
	}
}
