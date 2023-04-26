package fi.haagahelia.harkka.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "application_user")
public class ApplicationUser {
	
	//ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;  
    
    @Column(name="firstname")
    private String firstname;
    
    @Column(name="lastname")
    private String lastname;
    
    
    //Uniikki käyttäjätunnus
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    //Salasana
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    
    //Käyttäjän rooli
    @Column(name = "role", nullable = false)
    private String role;
    
    //Constructors  
	public ApplicationUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplicationUser(String firstname, String lastname, String username, String passwordHash, String role) {
		super();
		//v1.7
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}
	
	public ApplicationUser(String username,  String passwordHash, String role) {
		super();
		this.username = username;		
		this.passwordHash = passwordHash;
		this.role = role;
	}

	

	//get&set
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	//toString
	@Override
	public String toString() {
		return "ApplicationUser [id=" + id + ", username=" + username + ", firstname=" + firstname + ",lastname=" + lastname + ", passwordHash=" + passwordHash + ", role="
				+ role + "]";
	} 
    
}
