package hello.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * @author PC
 * Listy. Klasa zostanie zmapowana do bazy danych. Nic specjalnie trudnego.
 */
@Entity
public class Recenzent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private String imie;
	
	@Column
	private String nazwisko;
	
	@ElementCollection
	private List<String> tytuly;
	
	@OneToMany(mappedBy="recenzent",cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Korespondencja> korespondencja;
	
	@OneToMany(mappedBy="recenzent",cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Praca> prace;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public List<String> getTytuly() {
		return tytuly;
	}

	public void setTytuly(List<String> tytuly) {
		this.tytuly = tytuly;
	}

	public Recenzent(long id, String imie, String nazwisko, List<String> tytuly) {
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.tytuly = tytuly;
	}

	public List<Korespondencja> getKorespondencja() {
		return korespondencja;
	}

	public void setKorespondencja(List<Korespondencja> korespondencja) {
		this.korespondencja = korespondencja;
	}

	
	
	public List<Praca> getPrace() {
		return prace;
	}

	public void setPrace(List<Praca> prace) {
		this.prace = prace;
	}

	public Recenzent(){}
	
}
