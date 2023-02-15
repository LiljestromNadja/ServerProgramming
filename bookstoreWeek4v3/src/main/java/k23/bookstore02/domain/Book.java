package k23.bookstore02.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.*;
import jakarta.validation.constraints.Size;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Size(min = 1, max = 30)
	private String title;
	private String author, isbn;
	private int publicationYear;
	private double price;
	
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;	 //<-----------Tämän pitää olla sama kuin Category.javassa mappedBy = "category"
	
	public Book() {} // HUOM, tarkkana constructoreiden kanssa!
	

	public Book(String title, String author, String isbn, int publicationYear, double price) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publicationYear = publicationYear;
		this.price = price;
	}
	
	public Book(String title, String author, String isbn, int publicationYear, double price, Category category) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publicationYear = publicationYear;
		this.price = price;
		this.category = category;
	}


	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", publicationYear="
				+ publicationYear + ", price=" + price + "]";
	}
	
	
	//kysy opettajalta miksi näin ei toimi
	/*@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", publicationYear="
				+ publicationYear + ", price=" + price + ", category=" + category + "]";
	}
	
	Konsolissa: 
	
	2023-02-13T23:32:08.813+02:00  INFO 17588 --- [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2023-02-13T23:32:08.814+02:00  INFO 17588 --- [  restartedMain] .SchemaDropperImpl$DelayedDropActionImpl : HHH000477: Starting delayed evictData of schema as part of SessionFactory shut-down'
Hibernate: drop table if exists book cascade 
Hibernate: drop table if exists category cascade 
Hibernate: drop sequence if exists book_seq
Hibernate: drop sequence if exists category_seq
2023-02-13T23:32:09.028+02:00  WARN 17588 --- [  restartedMain] o.s.b.f.support.DisposableBeanAdapter    : Invocation of destroy method failed on bean with name 'inMemoryDatabaseShutdownExecutor': org.h2.jdbc.JdbcSQLNonTransientConnectionException: Database is already closed (to disable automatic closing at VM shutdown, add ";DB_CLOSE_ON_EXIT=FALSE" to the db URL) [90121-214]
2023-02-13T23:32:09.029+02:00  INFO 17588 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-9 - Shutdown initiated...
2023-02-13T23:32:09.033+02:00  INFO 17588 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-9 - Shutdown completed
		*/
	
	
	
	

}
