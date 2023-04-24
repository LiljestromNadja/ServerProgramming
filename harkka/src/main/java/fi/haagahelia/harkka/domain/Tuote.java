//Entiteettiluokka 
package fi.haagahelia.harkka.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Tuote {
	
	
	//tuotenimi, sijainti, kuvaus, hinta, tuoteluokka
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tuoteid;
	
	
	@Size(min = 3, max = 30)
	private String tuotenimi, sijainti;
	
	@Size(min = 1, max = 500)
	private String kuvaus;
	
	
	private double hinta;	
	
	@ManyToOne
	@JoinColumn(name = "tuoteluokka") //Foreign key
	private Tuoteluokka tuoteluokka; //<--- pitää olla sama kuin Tuoteluokka.javassa mappedBy = "tuoteluokka"
	
	public Tuote() {} //HUOM! Tarkkana constructoreiden kanssa 
	
	

	public Tuote(@Size(min = 3, max = 30) String tuotenimi, @Size(min = 3, max = 30) String sijainti,
			@Size(min = 1, max = 500) String kuvaus, double hinta) {
		super();
		this.tuotenimi = tuotenimi;
		this.sijainti = sijainti;
		this.kuvaus = kuvaus;
		this.hinta = hinta;
	}



	public Tuote(@Size(min = 3, max = 30) String tuotenimi, @Size(min = 3, max = 30) String sijainti,
			@Size(min = 1, max = 500) String kuvaus, double hinta, Tuoteluokka tuoteluokka) {
		super();
		this.tuotenimi = tuotenimi;
		this.sijainti = sijainti;
		this.kuvaus = kuvaus;
		this.hinta = hinta;
		this.tuoteluokka = tuoteluokka;
	}



	public Long getTuoteid() {
		return tuoteid;
	}

	public void setTuoteid(Long tuoteid) {
		this.tuoteid = tuoteid;
	}

	public String getTuotenimi() {
		return tuotenimi;
	}

	public void setTuotenimi(String tuotenimi) {
		this.tuotenimi = tuotenimi;
	}

	public String getSijainti() {
		return sijainti;
	}

	public void setSijainti(String sijainti) {
		this.sijainti = sijainti;
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public double getHinta() {
		return hinta;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

	public Tuoteluokka getTuoteluokka() {
		return tuoteluokka;
	}

	public void setTuoteluokka(Tuoteluokka tuoteluokka) {
		this.tuoteluokka = tuoteluokka;
	}



	@Override
	public String toString() {
		return "Tuote [tuoteid=" + tuoteid + ", tuotenimi=" + tuotenimi + ", sijainti=" + sijainti + ", kuvaus="
				+ kuvaus + ", hinta=" + hinta + "]";
	}
	
	
	
	/*@Override
	public String toString() {
		return "Tuote [tuoteid=" + tuoteid + ", tuotenimi=" + tuotenimi + ", sijainti=" + sijainti + ", kuvaus="
				+ kuvaus + ", hinta=" + hinta + ", tuoteluokka=" + tuoteluokka + "]";
	}*/
	
	
	

		
	
	
	

}
