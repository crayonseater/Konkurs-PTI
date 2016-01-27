package hello.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;

import hello.controller.OperatorListController;
import hello.model.Korespondencja;
import hello.model.Praca;
import hello.model.Recenzent;
import hello.repository.KorespondencjaRepository;
import hello.repository.PracaRepository;
import hello.repository.RecenzentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class WyslijListTestSuite {
	@Autowired
    private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Autowired
	OperatorListController opListCont;
	
	@Autowired
	PracaRepository pracaRep;
	
	@Autowired
	KorespondencjaRepository korespondencjaRepo;
	
	@Autowired
	RecenzentRepository recenzentRepo;
	
	@Before
    public void initMocks(){
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
	
	@After 
	public void reset_mocks() {
	    Mockito.reset(pracaRep);
	    Mockito.reset(korespondencjaRepo);
	    Mockito.reset(recenzentRepo);
	}
	
	@Test
	public void operatorListTest() throws Exception {
		Mockito.when(pracaRep.findByRecenzentIsNull())
			.thenReturn(new ArrayList<Praca>());
		Mockito.when(pracaRep.findByRecenzentIsNotNull())
			.thenReturn(new ArrayList<Praca>());
		Mockito.when(korespondencjaRepo.findByPracaIsNull())
			.thenReturn(new ArrayList<Korespondencja>());
		
		mockMvc.perform(get("/operator/lista"))
			.andExpect(status().isOk())
			.andExpect(view().name("operatorLista"))
			.andExpect(model().attribute("listyInne", Arrays.asList()))
			.andExpect(model().attribute("praceZRecenzentem", Arrays.asList()))
			.andExpect(model().attribute("praceBezRecenzenta", Arrays.asList()));
		
		Mockito.verify(pracaRep, Mockito.times(1)).findByRecenzentIsNull();
		Mockito.verify(pracaRep, Mockito.times(1)).findByRecenzentIsNotNull();
		Mockito.verify(korespondencjaRepo, Mockito.times(1)).findByPracaIsNull();
	}
	
	@Test
	public void operatorDetaleTest() throws Exception {
		
		long id = 1;
		Korespondencja kor = new Korespondencja();
		kor.setId(id);
		
		Praca prac = new Praca();
		prac.setId(id);
		
		Mockito.when(recenzentRepo.findAll())
			.thenReturn(new ArrayList<Recenzent>());
		Mockito.when(korespondencjaRepo.findByPracaId(id))
			.thenReturn(Arrays.asList(kor));
		Mockito.when(pracaRep.findOne(id))
			.thenReturn(prac);
		
		mockMvc.perform(get("/operator/detale/" + id))
			.andExpect(status().isOk())
			.andExpect(view().name("operatorDetale"))
			.andExpect(model().attribute("praca", prac))
			.andExpect(model().attribute("recenzenci", Arrays.asList()))
			.andExpect(model().attribute("korespondencja", Arrays.asList(kor)));
		
		Mockito.verify(recenzentRepo, Mockito.times(1)).findAll();
		Mockito.verify(korespondencjaRepo, Mockito.times(1)).findByPracaId(id);
		Mockito.verify(pracaRep, Mockito.times(1)).findOne(id);
	}
	
	@Test
	public void korespondencjaNapiszRecenzentPracaTest() throws Exception {
		
		long id = 1;
		Korespondencja kor = new Korespondencja();
		kor.setId(id);
		
		Praca prac = new Praca();
		prac.setId(id);
		
		Mockito.when(recenzentRepo.findAll())
			.thenReturn(new ArrayList<Recenzent>());
		Mockito.when(pracaRep.findOne(id))
			.thenReturn(prac);
		
		mockMvc.perform(get("/operator/napisz/recenzent/praca/" + id + "/0"))
			.andExpect(status().isOk())
			.andExpect(view().name("operatorNapiszRecenzentPraca"))
			.andExpect(model().attribute("praca", prac))
			.andExpect(model().attribute("recenzenci", Arrays.asList()))
			.andExpect(model().attribute("letterId", (long) 0));
		
		Mockito.verify(recenzentRepo, Mockito.times(1)).findAll();
		Mockito.verify(pracaRep, Mockito.times(1)).findOne(id);
	}
	
	@Test
	public void korespondencjaNapiszRecenzentTest() throws Exception {
		
		long id = 1;
		Korespondencja kor = new Korespondencja();
		kor.setId(id);
		
		Praca prac = new Praca();
		prac.setId(id);
		
		Mockito.when(recenzentRepo.findAll())
			.thenReturn(new ArrayList<Recenzent>());
		
		mockMvc.perform(get("/operator/napisz/recenzent/0"))
			.andExpect(status().isOk())
			.andExpect(view().name("operatorNapiszRecenzent"))
			.andExpect(model().attribute("recenzenci", Arrays.asList()))
			.andExpect(model().attribute("letterId", (long) 0));
		
		Mockito.verify(recenzentRepo, Mockito.times(1)).findAll();
	}
	
	
	
	
	
	
	
	
	
	
}