package it.uniroma3.siw.spring.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String name;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate releaseDate;
	
	@OneToOne
	private Hall hall;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Director director;
	
	@ManyToOne
	private MovieCatalog catalog;
	
	public Movie() {
		
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public LocalDate getReleaseDate() {
		return this.releaseDate;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Hall getHall() {
		return this.hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Director getDirector() {
		return this.director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}
	
	public MovieCatalog getCatalog() {
		return this.catalog;
	}

	public void setCatalog(MovieCatalog catalog) {
		this.catalog = catalog;
	}
	
	@Override
	public int hashCode() {
		return this.getName().hashCode()+this.getReleaseDate().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Movie otherMovie = (Movie)obj;
		return this.getName().equals(otherMovie.getName()) && this.getReleaseDate().equals(otherMovie.getReleaseDate());
	}
	
}
