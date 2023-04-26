package fi.haagahelia.harkka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import fi.haagahelia.harkka.web.UserDetailServiceImpl;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    @Autowired
    private UserDetailServiceImpl userDetailsService;	
	
    @Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeHttpRequests().requestMatchers("/css/**").permitAll() //permitAll() = these endpoints dont need authentication, they are available for everyone || voi lisätä useita endpointeja pilkulla erotettuna ("/css/**", "/index") jne
		.and()
		.authorizeHttpRequests().anyRequest().authenticated() //kaikki muut paitsi yllä määritellyt  tarvitsevat autentikaation
		// h2 console
		.and()
		.headers().frameOptions().disable()			
		.and()
		.formLogin().defaultSuccessUrl("/index", true) //minne mennää onnistuneen kirjautumisen jälkeen
		.and()
		.logout().permitAll();
		
		http.cors().and().csrf().disable();
		
		
		//.addFilterAfter(new CsrfLoggerFilter(), CsrfFilter.class); 
		
		return http.build();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder()); //spring security automaattisesti kryptaa salasanat
    }
}

