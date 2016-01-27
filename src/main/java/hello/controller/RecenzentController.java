package hello.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hello.model.Korespondencja;
import hello.model.NowaKorespondencja;
import hello.model.StatusKorespondencja;
import hello.repository.KorespondencjaRepository;
import hello.repository.RecenzentRepository;


/**
 * @author PC
 * Controller obsługujący akcje recenzenta.
 */
@Controller
public class RecenzentController {
	@Autowired
	RecenzentRepository recenzentRepo;
	
	@Autowired
	KorespondencjaRepository korespondencjaRepo;
	
	/**
	 * @author PC
	 * Listuje wszystkie wiadomości (odpowiednio posortowane).
	 */
	@RequestMapping("/recenzent/korespondencja/lista/{id}")
	public String recenzentKorespondencjaLista(@PathVariable Long id, Map<String, Object> map) {
		List<Korespondencja> listy = korespondencjaRepo.findByRecenzentId(id);
		
		List<Korespondencja> niePrzeczytane = new ArrayList<Korespondencja>();
		List<Korespondencja> przeczytane = new ArrayList<Korespondencja>();
		
		for(Korespondencja list : listy) {
			if(list.getStatus().equals(StatusKorespondencja.OCZEKUJACE)) {
				niePrzeczytane.add(list);
			} else {
				przeczytane.add(list);
			}
		}
		
		map.put("niePrzeczytane", niePrzeczytane);
		map.put("przeczytane", przeczytane);
		map.put("recenzent", recenzentRepo.findOne(id));
		
		return "recenzentKorespondencjaLista";
	}
	
	/**
	 * @author PC
	 * Obsługuje wysłanie odpowiedzi przez recenzenta.
	 */
	@RequestMapping(value="/recenzent/korespondencja/detale/{id}/post", method = RequestMethod.POST,consumes="application/json")
	public void recenzentKorespondencjaDetalePost(@RequestBody boolean czyAkceptuje, @PathVariable Long id, Map<String, Object> map) {
		Korespondencja list = korespondencjaRepo.findOne(id);
		if(czyAkceptuje) {
			list.setStatus(StatusKorespondencja.ZAAKCEPTOWANE);
			if(list.getPraca() != null) {
				list.getPraca().setRecenzent(list.getRecenzent());
			}
		} else {
			list.setStatus(StatusKorespondencja.ODRZUCONE);
		}
		korespondencjaRepo.save(list);
	}
	
	/**
	 * @author PC
	 * Obsługuje ekran wysłania odpowiedzi.
	 */
	@RequestMapping("/recenzent/korespondencja/detale/{id}")
	public String recenzentKorespondencjaDetale(@PathVariable Long id, Map<String, Object> map) {
		map.put("korespondencja", korespondencjaRepo.findOne(id));
		return "recenzentKorespondencjaDetale";
	}
	
	
}
