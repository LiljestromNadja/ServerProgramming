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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import fi.haagahelia.harkka.service.UserDetailServiceImpl;



@Configuration
//@EnableWebSecurity Spring Boot 2.7
@EnableMethodSecurity(securedEnabled = true) //Spring Boot 3.0->
public class WebSecurityConfig {
    @Autowired
    private UserDetailServiceImpl userDetailsService;	
    
    private static final AntPathRequestMatcher[] WHITE_LIST_URLS = {
            new AntPathRequestMatcher("/api/**"),
            new AntPathRequestMatcher("/h2-console/**"),
            new AntPathRequestMatcher("/css/**"),
            new AntPathRequestMatcher("/tuoteluokatrestjson"), //rest, esim. postman
            new AntPathRequestMatcher("/tuotteetrestjson/**"), // perään /** jos aiotaan sallia muokkaus/poisto
            new AntPathRequestMatcher("/uusituoterestjson"),
    };//päivitetty v1.5
    
    
    /*private static final AntPathRequestMatcher[] ADMIN_LIST_URLS = {
            new AntPathRequestMatcher("/admin/**"),
            
    };*/
	
    @Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
    	//http.authorizeHttpRequests().requestMatchers(ADMIN_LIST_URLS).hasAuthority("ADMIN")
		//.and()
		http
		.authorizeHttpRequests().requestMatchers(WHITE_LIST_URLS).permitAll() //permitAll() = these endpoints dont need authentication, they are available for everyone || voi lisätä useita endpointeja pilkulla erotettuna ("/css/**", "/index") jne
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

