package com.sunil.securerestapi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
class showCommandLineRunner implements CommandLineRunner{

    @Override
	public void run(String... arg0) throws Exception {
		SecurityUtils.runAs("system", "system", "ROLE_ADMIN");
		for(Show s:this.showRepository.findAll()){
			System.out.println(s.toString());
		}
		SecurityContextHolder.clearContext();
	}	

    @Autowired
	ShowRepository showRepository;
}

/*interface ShowRepository extends JpaRepository<Show, Long>{
	Collection<Show> findByNameLike(String showName);
}*/

/*
@RestController
class ShowController {
	@RequestMapping("/shows")
	Collection<Show> listShows(){
		return this.showRepository.findAll();
	}
	@Autowired
	ShowRepository showRepository;
}
*/
@Entity
@Table(name="SHOW")
public class Show {
	@Id
	@Column(name = "SHOW_ID")
	@GeneratedValue
	private long id;
	@Column(name = "SHOW_NAME")
	private String name;
	@Column(name = "TOTAL_SEAT")
	private int totalSeat;
	@Column(name = "BOOKED_SEAT")
	private int bookedSeat;
	@Column(name = "DATE_TIME")
	private String date;
	@Column(name = "BOOKING_ENABLED")
	private boolean openForBook;
	
	public Show(){}
	public Show(String name, int totalSeat, int bookedSeat, String date, boolean openForBook) {
		super();
		this.name = name;
		this.totalSeat = totalSeat;
		this.bookedSeat = bookedSeat;
		this.date = date;
		this.openForBook=openForBook;
	}
	public Show(Show show){
		super();
		this.id = show.getId();
		this.name = show.getName();
		this.totalSeat = show.getTotalSeat();
		this.bookedSeat = show.getBookedSeat();
		this.date = show.getDate();
		this.openForBook=show.openForBook;
	}
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getTotalSeat() {
		return totalSeat;
	}
	public int getBookedSeat() {
		return bookedSeat;
	}
	public String getDate() {
		return date;
	}
	public boolean getOpenForBook() {
		return openForBook;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}
	public void setBookedSeat(int bookedSeat) {
		this.bookedSeat = bookedSeat;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setForBook(boolean openForBook) {
		this.openForBook = openForBook;
	}
	
	@Override
	public String toString() {
		return "Show [ id=" + id + ", title=" + name + ", total seat=" + totalSeat + ", booked seat="+ bookedSeat+" date-time="+ date+ " openForBook= "+openForBook+" ]";
	}
}
