package com.sunil.securerestapi;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.context.SecurityContextHolder;



@Component
class userCommandLineRunner implements CommandLineRunner{
	@Override
	public void run(String... arg0) throws Exception {
		SecurityUtils.runAs("system", "system", "ROLE_ADMIN");
		for(User u:this.userRepository.findAll()){
			System.out.println(u.toString());
		}
		//SecurityConfiguration.users=(Set<User>) this.userRepository.findAll();
		SecurityContextHolder.clearContext();
	}

    
	@Autowired
	UserRepository userRepository;
}


/*interface UserRepository extends JpaRepository<User, Long>{
	Collection<User> findByUsernameLike(String userName);
}
*/
/*@RestController
class UserController {
	@RequestMapping("/users")
	Collection<User> listUsers(){
		return this.userRepository.findAll();
	}
	@Autowired
	UserRepository userRepository;
}
*/

@Entity
@Table(name = "USER")
public class User {
    
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue
	private long id;
    
	@Column(name = "USERNAME" , unique = true)//, nullable = false)
	private String username;
	@JsonIgnore
	@Column(name = "PASSWORD")//, nullable = false)
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USER_SHOW", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "SHOW_ID") })
	private Set<Show> shows=new HashSet<>();

	User(){}
	public User(String userName, String password) {
		super();
		this.username = userName;
		this.password = password;
	}
	
	public long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}

	public Set<Show> getShows() {
		return shows;
	}
	/// book show (by adding in to shows list of particula user)
	public void bookShow(Show show){
		shows.add(show);
	}
	public void cancleBooking(Show show){
		shows.remove(show);
	}

	/*public void setId(long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	 */
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}	
}

