package hello.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;




/**
 * @author PC
 * Listy. Klasa zostanie zmapowana do bazy danych. Nic specjalnie trudnego.
 */
@Entity
public class Korespondencja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private String tytul;
	
	@Column(columnDefinition="TEXT")
	private String tresc;
	
	@Column
	private StatusKorespondencja status;
	
	@ManyToOne(targetEntity=Recenzent.class)
  	@JoinColumn
	private Recenzent recenzent;
	
	@ManyToOne(targetEntity=Praca.class)
  	@JoinColumn
	private Praca praca;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTresc() {
		return tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public StatusKorespondencja getStatus() {
		return status;
	}

	public void setStatus(StatusKorespondencja status) {
		this.status = status;
	}

	public Recenzent getRecenzent() {
		return recenzent;
	}

	public void setRecenzent(Recenzent recenzent) {
		this.recenzent = recenzent;
	}

	
	
	public Praca getPraca() {
		return praca;
	}

	public void setPraca(Praca praca) {
		this.praca = praca;
	}

	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public Korespondencja(long id, String tresc, StatusKorespondencja status, Recenzent recenzent, Praca praca) {
		this.id = id;
		this.tresc = tresc;
		this.status = status;
		this.recenzent = recenzent;
		this.praca = praca;
	}
	public Korespondencja(){}
	
}
