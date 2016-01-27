package hello.model;

import java.util.List;

/**
 * @author PC
 * Klasa obsługująca POST przesyłany ze strony. Stworzona ze względu na to jak działa mapowanie JSON->Object.
 */
public class NowaKorespondencja {
	
	NowaKorespondencja() {
		
	}
	
	private String[] recenzenci;
	
	private String tytul;
	
	private String tresc;
	
	public String getTresc() {
		return tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}
	
	
	

	public String[] getRecenzenci() {
		return recenzenci;
	}

	public void setRecenzenci(String[] recenzenci) {
		this.recenzenci = recenzenci;
	}

	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}
	
}
