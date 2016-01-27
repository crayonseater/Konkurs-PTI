package hello.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hello.repository.PracaRepository;
import hello.repository.KorespondencjaRepository;
import hello.repository.RecenzentRepository;




/**
 * @author PC
 * Controller do listów. Obsługuje jedynie pokazywanie listu.
 * Reszta funkcjonalności (jak wysyłanie listu) przeniesione do OperatorController i RecenzentController
 * ze względu na różne funkcjonalności.
 */
@Controller
public class KorespondencjaController {
	
	@Autowired
	KorespondencjaRepository korespondencjaRepo;
	
	@Autowired
	PracaRepository autorRepo;

	@Autowired
	RecenzentRepository recenzentRepo;
	
	
	@RequestMapping("/korespondencja/detale/{id}")
	public String korespondencjaDetale(@PathVariable Long id, Map<String, Object> map) {
		map.put("korespondencja", korespondencjaRepo.findOne(id));
		return "korespondencjaDetale";
	}
	
	
	
	
}
