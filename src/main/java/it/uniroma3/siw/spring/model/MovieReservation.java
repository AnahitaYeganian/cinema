package it.uniroma3.siw.spring.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class MovieReservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Movie movie;
	
	@ManyToOne
	private Hall hall;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime startTime;
	
	public MovieReservation() {
		
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	public LocalDateTime getStartTime() {
		return this.startTime;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Hall getHall() {
		return this.hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}
	
	@Override
	public int hashCode() {
		return this.getUser().hashCode()+this.getMovie().hashCode()+this.getStartTime().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		MovieReservation otherReservation = (MovieReservation)obj;
		return this.getUser().equals(otherReservation.getUser()) &&
				this.getMovie().equals(otherReservation.getMovie()) &&
				this.getStartTime().equals(otherReservation.getStartTime());
	}

}