package br.sharing.controller;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Transactional
@Controller("/aluno")
public class AlunoController {

	@RequestMapping("/alterarHorariosDisponiveis")
	public String alterarHorariosDisponiveis() {
		return "aluno/perfil/alterar_horarios_disponiveis";
	}
	
	@RequestMapping("/alterarDisciplinas")
	public String alterarDisciplinas() {
		return "aluno/perfil/alterar_disciplinas";
	}
}
