package hello.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



/**
 * @author PC
 * Listy. Klasa zostanie zmapowana do bazy danych. Nic specjalnie trudnego.
 */
@Entity
public class Praca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private String temat;


	@ManyToOne(targetEntity=Recenzent.class)
  	@JoinColumn
	private Recenzent recenzent;
	
	@OneToMany(mappedBy="praca",cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Korespondencja> korespondencja;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getTemat() {
		return temat;
	}

	public void setTemat(String temat) {
		this.temat = temat;
	}

	public Recenzent getRecenzent() {
		return recenzent;
	}

	public void setRecenzent(Recenzent recenzent) {
		this.recenzent = recenzent;
	}

	public List<Korespondencja> getKorespondencja() {
		return korespondencja;
	}

	public void setKorespondencja(List<Korespondencja> korespondencja) {
		this.korespondencja = korespondencja;
	}
	
	public Praca(long id, String temat) {
		this.id = id;
		this.temat = temat;
	}
	
	public Praca(){}
	
	
	
}
