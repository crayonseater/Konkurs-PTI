package hello.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hello.model.Praca;
import hello.model.Recenzent;
import hello.repository.PracaRepository;
import hello.repository.RecenzentRepository;



/**
 * @author PC
 * Controller obsługujący stronę index. Dodatkowo wprowadza trochę startowych danych
 * w celach testowych przy wejściu na ścieżkę "/fill"
 */
@Controller
public class IndexController {

	@Autowired
	PracaRepository pracaRepo;
	@Autowired
	RecenzentRepository recenzentRepo;
	
	
    @RequestMapping("/")
    public String index(Map<String, Object> map) {
    	map.put("recenzenci", recenzentRepo.findAll());
        return "index";
    }
    
    @RequestMapping("/fill")
    public void fillDB() {
    	pracaRepo.save(new Praca(1, "Badanie przebiegu funkcji w czasie."));
    	pracaRepo.save(new Praca(2, "Badanie wpłynu majonezu na życie."));
    	pracaRepo.save(new Praca(3, "Czym jest model? Czyli dyskryminacja modelek na przestrzeni lat."));
    	
    	recenzentRepo.save(new Recenzent(1, "Zbigniew", "Beton", Arrays.asList("Profesor", "Profesorze")));
    	recenzentRepo.save(new Recenzent(2, "Henryk", "Wodecki",  Arrays.asList("Doktor", "Doktorze")));
    	recenzentRepo.save(new Recenzent(3, "Lech", "Piast",  Arrays.asList("Magister", "Magistrze")));
    }

}
