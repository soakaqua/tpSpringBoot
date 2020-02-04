package formation.sopra.tpSpringBoot01.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import formation.sopra.tpSpringBoot01.services.security.CustomUserDetails;

@Configuration
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter{
	
	public CustomWebSecurityConfigurerAdapter() {
		System.out.println("test");
	}
	
	//2eres méthodes
//	@Autowired
//	DataSource dataSource;
	
	
	//3e méthode
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		//authentification avec webservices (attention, on ne peut pas utiliser de formulaires avec webservices)
		http.csrf().disable();
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic(); //syst d'authentification le plus facile => Dans le header de la requete, on envoie le mdp et l'id du compte en cripté.
		//dans postman : authorization => basic auth => username+pwd
		// avec curl : curl --user Identifiant:Mdp http://localhost:8080/demo/rest/salle/
		
		
		//partie web services (spring rest) => On laisse tout ouvert pour commencer la partie spring rest
//		http.csrf().disable();
//		http.authorizeRequests().anyRequest().permitAll();
		
		//partie sécurité 
//		//gestion identification admin | ATTENTION pas mettre ROLE_ADMIN
//		http.authorizeRequests().antMatchers("/auth/admin/**").hasAnyRole("ADMIN").and().formLogin().loginPage("/login")
//		//interdire les requetes vers personne et list (dans /auth) sauf si authentifié et avec un formLogin (la page de co)
//		.and().authorizeRequests().antMatchers("/auth/**").authenticated().and().formLogin().loginPage("/login").permitAll().and().authorizeRequests()
//		//gestion déconnection
//		.and().logout().logoutSuccessUrl("/");
//		//autorise les autres pages (index) => quand auth: s'arrete à la ligne précédente.
//		http.authorizeRequests().antMatchers("/**").permitAll();
	}
	
	//méthode qui permet de parametrer l'identification
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
	
			//infos stockées ici
			//auth.inMemoryAuthentication().withUser("titi").password("{noop}mdp").roles("ADMIN");
			
			// récup par une requete sql
			//dataSource : façon de gérer les bdd par spring
			// usersByUsernameQuery : requete sql pour récupérer infos
//			auth.jdbcAuthentication().dataSource(dataSource)
//			.usersByUsernameQuery("select username, password, enable from login where username=?")
//			.authoritiesByUsernameQuery("select username, role from roles where username=?");
			
			//3e methode : userdetailsService : géré par spring
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		}	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
}
