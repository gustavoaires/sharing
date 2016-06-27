package br.sharing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import br.sharing.interceptor.Interceptador;

@Configuration
public class SharingConfig extends WebMvcAutoConfigurationAdapter {

	@Autowired
	private Interceptador autorizadorInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(autorizadorInterceptor);
		super.addInterceptors(registry);
	}
}
