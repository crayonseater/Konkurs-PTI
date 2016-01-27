package hello.tests;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.controller.OperatorListController;
import hello.repository.KorespondencjaRepository;
import hello.repository.PracaRepository;
import hello.repository.RecenzentRepository;

@Configuration
public class TestContext {
	
	@Bean
	OperatorListController operatorListController() {
		return new OperatorListController();
	}
	
	@Bean
	PracaRepository pracaRepository() {
		return Mockito.mock(PracaRepository.class);
	}
	
	@Bean
	KorespondencjaRepository korespondencjaRepository() {
		return Mockito.mock(KorespondencjaRepository.class);
	}
	
	@Bean
	RecenzentRepository recenzentRepository() {
		return Mockito.mock(RecenzentRepository.class);
	}
	
}
