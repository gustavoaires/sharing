package br.sharing.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import br.sharing.Criptografia;
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
	public String login(Aluno aluno, HttpSession session) {
		
		Aluno candidato = alunoDao.findByLoginLike(aluno.getLogin());
		if(!(candidato == null)){
			if(Criptografia.criptografar(candidato.getSenha()).equals(aluno.getSenha())){
				session.setAttribute("aluno_logado", candidato);
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
