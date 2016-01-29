package com.sunil.securerestapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	//static Set<User> users=new HashSet();
    public SecurityConfiguration() {}
	
	 @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth ) throws Exception {
		 	
		 	//auth.inMemoryAuthentication().withUser("sunil").password("poudel").roles("ADMIN","USER");
	        //auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		 	auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN","USER");
	        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
	        auth.inMemoryAuthentication().withUser("shova").password("shova").roles("USER");
	        auth.inMemoryAuthentication().withUser("sunil").password("sunil").roles("USER");
	        auth.inMemoryAuthentication().withUser("anup").password("anup").roles("USER");
	        
	        /*for(User user:users){
	        	auth.inMemoryAuthentication().withUser(user.getUsername()).password(user.getPassword()).roles("USER");
	        }*/
	    }


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and().authorizeRequests().//
				antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN").
				antMatchers(HttpMethod.GET, "/users/").hasRole("ADMIN").
				
				antMatchers(HttpMethod.GET, "/users/*").hasRole("USER").
				
				antMatchers(HttpMethod.DELETE, "/users/**").hasRole("USER").
				antMatchers(HttpMethod.PUT, "/users/**").hasRole("USER").
			 	antMatchers(HttpMethod.PATCH, "/users/**").hasRole("USER").
			 	
//			 	antMatchers("/users/*/shows").permitAll().
			 	
				antMatchers(HttpMethod.GET, "/users/*/shows").hasRole("USER").
				antMatchers(HttpMethod.GET, "/users/*/shows/*").hasRole("USER").
			 	
				antMatchers(HttpMethod.POST, "/users/*/shows").hasRole("USER").
			 	antMatchers(HttpMethod.DELETE, "/users/*/shows/**").hasRole("USER").
			 	antMatchers(HttpMethod.PATCH, "/users/*/shows/**").hasRole("USER").
			 	antMatchers(HttpMethod.PUT, "/users/*/shows/**").hasRole("USER").

			 	antMatchers(HttpMethod.GET, "/shows").hasRole("ADMIN").
			 	antMatchers(HttpMethod.GET, "/shows/").hasRole("ADMIN").
			 	
				antMatchers(HttpMethod.POST, "/shows").hasRole("ADMIN").
				antMatchers(HttpMethod.POST, "/shows/").hasRole("ADMIN").
			 	antMatchers(HttpMethod.PUT, "/shows/**").hasRole("ADMIN").
			 	antMatchers(HttpMethod.PATCH, "/shows/**").hasRole("ADMIN").
			 	antMatchers(HttpMethod.DELETE, "/shows/**").hasRole("ADMIN").and().
			 	
				csrf().disable();
	}
}
