package hello.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hello.model.Praca;
import hello.model.Korespondencja;
import hello.model.NowaKorespondencja;
import hello.model.StatusKorespondencja;
import hello.repository.PracaRepository;
import hello.repository.KorespondencjaRepository;
import hello.repository.RecenzentRepository;


/**
 * @author PC
 * Controller obsługujący akcje operatora.
 */
@Controller
public class OperatorListController {
	
	
	@Autowired
	PracaRepository pracaRepo;

	@Autowired
	RecenzentRepository recenzentRepo;
	
	@Autowired
	KorespondencjaRepository korespondencjaRepo;
	
	
	
	/**
	 * @author PC
	 * Listuje wszystkich prac.
	 */
	@RequestMapping("/operator/lista")
	public String operatorList(Map<String, Object> map) {
		map.put("praceBezRecenzenta", pracaRepo.findByRecenzentIsNull());
		map.put("praceZRecenzentem", pracaRepo.findByRecenzentIsNotNull());
		map.put("listyInne", korespondencjaRepo.findByPracaIsNull());
		return "operatorLista";
	}
	
	/**
	 * @author PC
	 * Pokazuje informacje o konkretnej pracy.
	 */
	@RequestMapping("/operator/detale/{id}")
	public String operatorDetale(@PathVariable Long id, Map<String, Object> map) {
		
		map.put("praca", pracaRepo.findOne(id));
		map.put("recenzenci", recenzentRepo.findAll());
		map.put("korespondencja", korespondencjaRepo.findByPracaId(id));
		return "operatorDetale";
	}
	
	/**
	 * @author PC
	 * Metoda obsługująca pisanie listów.
	 */
	@RequestMapping("/operator/napisz/recenzent/praca/{idA}/{letterId}")
	public String korespondencjaNapiszRecenzentPraca(@PathVariable Long idA, @PathVariable Long letterId, Map<String, Object> map) {
		String tekst1Tytul = "Prośba o recenzję";
		String tekst1Tekst = "Szanowny Panie @tytul1,"
				+ "chcielibyśmy poprosić Pana o zostanie recenzentem w Konkursie PTI."
				+ "Komitet Organizacyjny PTI";
		
		String tekst2Tytul = "Konkurs PTI - ponaglenie";
		String tekst2Tekst = "Szanowny Panie @tytul1,"
				+ "chcielibyśmy poprosić Pana o jak najszybsze przesłanie recenzji."
				+ "Komitet Organizacyjny PTI";
		
		String tekst3Tytul = "Podziękowanie";
		String tekst3Tekst = "Szanowny Panie @tytul1,"
				+ "dziękujemy za wykonanie recenzji."
				+ "Komitet Organizacyjny PTI";
		
		String tekst4Tytul = "Zaproszenie na rozstrzygnięcie konkursu PTI";
		String tekst4Tekst = "Szanowny Panie @tytul1,"
				+ "mamy zaszczyt zaprosić Pana na uroczystość wręczenia nagród dn. 23.07.2016 o godz. 16:00."
				+ "Komitet Organizacyjny PTI";
		
		map.put("tytulyListow", Arrays.asList("", tekst1Tytul, tekst2Tytul, tekst3Tytul, tekst4Tytul));
		map.put("tresciListow", Arrays.asList("", tekst1Tekst, tekst2Tekst, tekst3Tekst, tekst3Tekst));
		map.put("letterId", letterId);
		map.put("praca", pracaRepo.findOne(idA));
		map.put("recenzenci", recenzentRepo.findAll());
		return "operatorNapiszRecenzentPraca";
	}
	
	/**
	 * @author PC
	 * Metoda przyjmująca napisany list. Podmienia tagi na odpowiednie dane
	 * i zapisuje list.
	 */
	@RequestMapping(value="/operator/napisz/recenzent/praca/{idA}/post", method = RequestMethod.POST,consumes="application/json")
	public String korespondencjaNapiszRecenzentPracaPost
		(@RequestBody NowaKorespondencja list, @PathVariable Long idA) {
		
		
		for(String id : list.getRecenzenci()) {
			Korespondencja korespondencja = new Korespondencja();
			korespondencja.setTytul(list.getTytul());
			
			korespondencja.setRecenzent(recenzentRepo.findOne(Long.valueOf(id)));
			
			String tekst = list.getTresc();
			
			tekst = tekst.replaceAll("@imie", korespondencja.getRecenzent().getImie());
			tekst = tekst.replaceAll("@nazwisko", korespondencja.getRecenzent().getNazwisko());
			switch(korespondencja.getRecenzent().getTytuly().size()) {
			case 4:
				tekst = tekst.replaceAll("@tytul3", korespondencja.getRecenzent().getTytuly().get(3));
			case 3:
				tekst = tekst.replaceAll("@tytul2", korespondencja.getRecenzent().getTytuly().get(2));
			case 2: 
				tekst = tekst.replaceAll("@tytul1", korespondencja.getRecenzent().getTytuly().get(1));
			case 1:
				tekst = tekst.replaceAll("@tytul", korespondencja.getRecenzent().getTytuly().get(0));
			default:
				break;
			}
			tekst = tekst.replaceAll("\n",System.getProperty("line.separator"));
			
			korespondencja.setTresc(tekst);
			korespondencja.setPraca(pracaRepo.findOne(idA));
			korespondencja.setStatus(StatusKorespondencja.OCZEKUJACE);
			korespondencjaRepo.save(korespondencja);
		}
		
		
		return "Success";
	}
	
	/**
	 * @author PC
	 * Metoda obsługująca pisanie listów.
	 */
	@RequestMapping("/operator/napisz/recenzent/{letterId}")
	public String korespondencjaNapiszRecenzent(@PathVariable Long letterId, Map<String, Object> map) {
		String tekst1Tytul = "Prośba o recenzję";
		String tekst1Tekst = "Szanowny Panie @tytul1,"
				+ "chcielibyśmy poprosić Pana o zostanie recenzentem w Konkursie PTI."
				+ "Komitet Organizacyjny PTI";
		
		String tekst2Tytul = "Konkurs PTI - ponaglenie";
		String tekst2Tekst = "Szanowny Panie @tytul1,"
				+ "chcielibyśmy poprosić Pana o jak najszybsze przesłanie recenzji."
				+ "Komitet Organizacyjny PTI";
		
		String tekst3Tytul = "Podziękowanie";
		String tekst3Tekst = "Szanowny Panie @tytul1,"
				+ "dziękujemy za wykonanie recenzji."
				+ "Komitet Organizacyjny PTI";
		
		String tekst4Tytul = "Zaproszenie na rozstrzygnięcie konkursu PTI";
		String tekst4Tekst = "Szanowny Panie @tytul1,"
				+ "mamy zaszczyt zaprosić Pana na uroczystość wręczenia nagród dn. 23.07.2016 o godz. 16:00."
				+ "Komitet Organizacyjny PTI";
		
		map.put("tytulyListow", Arrays.asList("", tekst1Tytul, tekst2Tytul, tekst3Tytul, tekst4Tytul));
		map.put("tresciListow", Arrays.asList("", tekst1Tekst, tekst2Tekst, tekst3Tekst, tekst4Tekst));
		map.put("letterId", letterId);
		map.put("recenzenci", recenzentRepo.findAll());
		return "operatorNapiszRecenzent";
	}
	
	/**
	 * @author PC
	 * Metoda przyjmująca napisany list. Podmienia tagi na odpowiednie dane
	 * i zapisuje list.
	 */
	@RequestMapping(value="/operator/napisz/recenzent/post", method = RequestMethod.POST,consumes="application/json")
	public String korespondencjaNapiszRecenzentPost
		(@RequestBody NowaKorespondencja list) {
		
		
		for(String id : list.getRecenzenci()) {
			Korespondencja korespondencja = new Korespondencja();
			korespondencja.setTytul(list.getTytul());
			
			korespondencja.setRecenzent(recenzentRepo.findOne(Long.valueOf(id)));
			
			String tekst = list.getTresc();
			
			tekst = tekst.replaceAll("@imie", korespondencja.getRecenzent().getImie());
			tekst = tekst.replaceAll("@nazwisko", korespondencja.getRecenzent().getNazwisko());
			switch(korespondencja.getRecenzent().getTytuly().size()) {
			case 4:
				tekst = tekst.replaceAll("@tytul3", korespondencja.getRecenzent().getTytuly().get(3));
			case 3:
				tekst = tekst.replaceAll("@tytul2", korespondencja.getRecenzent().getTytuly().get(2));
			case 2: 
				tekst = tekst.replaceAll("@tytul1", korespondencja.getRecenzent().getTytuly().get(1));
			case 1:
				tekst = tekst.replaceAll("@tytul", korespondencja.getRecenzent().getTytuly().get(0));
			default:
				break;
			}
			tekst = tekst.replaceAll("\n",System.getProperty("line.separator"));
			
			korespondencja.setTresc(tekst);
			korespondencja.setPraca(null);
			korespondencja.setStatus(StatusKorespondencja.OCZEKUJACE);
			korespondencjaRepo.save(korespondencja);
		}
		
		
		return "Success";
	}
	
}
