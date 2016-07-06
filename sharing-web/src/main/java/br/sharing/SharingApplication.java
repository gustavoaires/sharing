package br.sharing;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import br.sharing.service.SmtpMailSenderService;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class SharingApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private SmtpMailSenderService mailSender;

	public static void main(String[] args) {
		SpringApplication.run(SharingApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SharingApplication.class);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Thread sendEmail = new Thread(new Runnable() {
			@SuppressWarnings({ "deprecation" })
			@Override
			public void run() {
				Date atual;
				Date meiaNoite = new Date(1, 1, 1, 0, 0, 0);
				while (true) {
					atual = new Date();
					if (atual.getHours() == meiaNoite.getHours() 
							&& atual.getMinutes() == meiaNoite.getMinutes()
							&& atual.getSeconds() == meiaNoite.getSeconds())
						mailSender.configurarEEnviarEmails();
				}
			}
		});
		sendEmail.start();
	}
}
