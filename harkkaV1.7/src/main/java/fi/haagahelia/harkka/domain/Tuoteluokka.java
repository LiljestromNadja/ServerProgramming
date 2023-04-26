package fi.haagahelia.harkka.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
public class Tuoteluokka {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tuoteluokkaid; 
	
	@Size(min = 3, max = 30)
	private String tuoteluokkanimi;	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tuoteluokka") //mappedByn pitää olla sama kuin Tuote.javassa private Tuoteluokka tuoteluokka;
	@JsonIgnore
	private List<Tuote> tuotteet;
	
	public Tuoteluokka() {} //Muista olla tarkkana constructoreiden kanssa

	public Tuoteluokka(@Size(min = 3, max = 30) String tuoteluokkanimi) {
		super();
		this.tuoteluokkanimi = tuoteluokkanimi;
	}

	public Long getTuoteluokkaid() {
		return tuoteluokkaid;
	}

	public void setTuoteluokkaid(Long tuoteluokkaid) {
		this.tuoteluokkaid = tuoteluokkaid;
	}

	public String getTuoteluokkanimi() {
		return tuoteluokkanimi;
	}

	public void setTuoteluokkanimi(String tuoteluokkanimi) {
		this.tuoteluokkanimi = tuoteluokkanimi;
	}

	public List<Tuote> getTuotteet() {
		return tuotteet;
	}

	public void setTuotteet(List<Tuote> tuotteet) {
		this.tuotteet = tuotteet;
	}

	@Override
	public String toString() {
		return "Tuoteluokka [tuoteluokkaid=" + tuoteluokkaid + ", tuoteluokkanimi=" + tuoteluokkanimi + "]";
	}
	
	/*
	 * @Override
	public String toString() {
		return "Tuoteluokka [tuoteluokkaid=" + tuoteluokkaid + ", tuoteluokkanimi=" + tuoteluokkanimi + ", tuotteet="
				+ tuotteet + "]";
	}
	 */
	
	

}
