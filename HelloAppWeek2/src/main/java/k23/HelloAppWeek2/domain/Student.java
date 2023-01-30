package k23.HelloAppWeek2.domain;

//tehtava 2
public class Student {
	
	private String nimi, osoite;
	
	

	public Student(String nimi, String osoite) {
		super();
		this.nimi = nimi;
		this.osoite = osoite;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getOsoite() {
		return osoite;
	}

	public void setOsoite(String osoite) {
		this.osoite = osoite;
	}

	@Override
	public String toString() {
		return "Student [nimi=" + nimi + ", osoite=" + osoite + "]";
	}
	
	

}
