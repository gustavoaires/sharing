package br.sharing.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.sharing.dao.IAtendimentoDAO;
import br.sharing.model.Atendimento;

@Service
@Component
public class SmtpMailSenderService {
	
	private static final String ASSUNTO = "Sharing: Você tem um atendimento amanhã";
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private IAtendimentoDAO atendimentoDAO;
	
	public void configurarEEnviarEmails() {
		List<Atendimento> atendimentos = atendimentoDAO.findByStatus("confirmado");
		if (!atendimentos.isEmpty()) {
			for (Atendimento a : atendimentos) {	
				if (ehAmanha(a.getDataAtendimento())) {
					String emailPediuAjuda = a.getPediuAjuda().getEmail();
					String emailAjudante = a.getAjudante().getEmail();
					
					String pediuAjuda = a.getPediuAjuda().getPrimeiroNome() + " " + a.getPediuAjuda().getSobrenome();
					String ajudante = a.getAjudante().getPrimeiroNome() + " " + a.getAjudante().getSobrenome();
					String conteudo;
					try {
						// Mensagem para quem pediu atendimento
						conteudo = conteudo(a.getDataAtendimento(), a.getHoraAtendimento(),
								ajudante, a.getLocalDeEncontro(), a.getDisciplina().getNome());
						this.enviar(emailPediuAjuda, ASSUNTO, conteudo);
						
						// Mensagem para quem vai dar atendimento
						conteudo = conteudo(a.getDataAtendimento(), a.getHoraAtendimento(),
								pediuAjuda, a.getLocalDeEncontro(), a.getDisciplina().getNome());
						this.enviar(emailAjudante, ASSUNTO, conteudo);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}	
			}
		}
	}
	
	public void enviar(String para, String assunto, String conteudo) throws MessagingException {
		MimeMessage mensagem = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		helper = new MimeMessageHelper(mensagem, true); 
		helper.setSubject(assunto);
		helper.setTo(para);
		helper.setText(conteudo, true);  
		
		javaMailSender.send(mensagem);
	}
	
	@SuppressWarnings("static-access")
	public boolean ehAmanha(Date atendimento) {
		if (atendimento != null) {
			Calendar agora = Calendar.getInstance();
			Calendar data = Calendar.getInstance();
			data.setTime(atendimento);
			if (agora.YEAR == data.YEAR && agora.DAY_OF_YEAR+1 == data.DAY_OF_YEAR)
				return true;
		}
		return false;
	}

	public String conteudo(Date data, Date hora, String local, String nome, String disciplina) {
		SimpleDateFormat dFormt = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat hFormt = new SimpleDateFormat("HH:mm");
		String d = dFormt.format(data);
		String h = hFormt.format(hora);
		String mensagem = "Olá! Você tem um atendimento da disciplina de " + disciplina 
				+ " marcado para amanhã " + d + ", " + h + " com o "
				+ nome + ". Local marcado: " + local;
		return mensagem;
	}
}
