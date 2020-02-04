package formation.sopra.tpSpringBoot01.services.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import formation.sopra.tpSpringBoot01.model.Login;
import formation.sopra.tpSpringBoot01.model.UserRole;
import formation.sopra.tpSpringBoot01.repositories.LoginRepository;

public class CustomUserDetails implements UserDetails {



	private Login login;

	public CustomUserDetails(Login login) {
		this.login = login;
	}

	@Override
	//permet gérer les roles d'utilisateur
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorities =new HashSet<>();
		Set<UserRole> roles =  login.getRoles();
		for(UserRole userRole:roles) {
			authorities.add(new SimpleGrantedAuthority(userRole.getRole().toString()));
		}


		return authorities;
	}

	@Override
	public String getPassword() {
		return login.getPassword();
	}

	@Override
	public String getUsername() {
		return login.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		return login.isEnable();
	}

}
