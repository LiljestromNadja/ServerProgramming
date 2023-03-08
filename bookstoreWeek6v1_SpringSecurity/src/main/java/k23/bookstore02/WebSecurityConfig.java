package k23.bookstore02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import k23.bookstore02.web.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserDetailServiceImpl userDetailsService;	
	
    @Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests().requestMatchers("/css/**").permitAll()
		.and()
		.authorizeHttpRequests().anyRequest().authenticated()
		//for h2 console
		.and()
		.headers().frameOptions().disable()			
		.and()
		.formLogin().defaultSuccessUrl("/booklist", true)
		.and()
		.logout().permitAll();
		
		http.cors().and().csrf().disable();
		
		
		//.addFilterAfter(new CsrfLoggerFilter(), CsrfFilter.class); 
		
		return http.build();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}

