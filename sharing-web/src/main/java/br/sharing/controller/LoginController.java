package br.sharing.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import br.sharing.encrypt.Criptografia;
import br.sharing.message_atribute.Atributo;
import br.sharing.dao.IAlunoDAO;
import br.sharing.model.Aluno;

@Transactional
@Controller("/login")
public class LoginController {

	@Autowired
	private IAlunoDAO alunoDao;
	
	@RequestMapping("/form")
	public String formLogin(){
		return "/login/form_login";
	}
	
	@RequestMapping("/loginAssert")
	public String login(Aluno aluno, HttpSession sessao) {
		
		Aluno candidato = alunoDao.findByLoginLike(aluno.getLogin());
		
		if (!(candidato == null)) {
			if (Criptografia.criptografar(candidato.getSenha()).equals(aluno.getSenha())) {
				sessao.setAttribute(Atributo.ALUNO_LOGADO, candidato);
				return "redirect:/aluno/home";
			}
		}
		
		return "redirect:/form";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate(); 
		return "redirect:loginFormulario";
	}
}
