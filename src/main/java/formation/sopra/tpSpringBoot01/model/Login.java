package formation.sopra.tpSpringBoot01.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Login {
	@Id
	@Column(name = "login",length=50)
	private String login;
	@Column(name = "password", length=500)
	private String password;
	private boolean enable; 
	@OneToMany(mappedBy = "login")
	private Set<UserRole> roles;
	
	
	public Login() {
		
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnable() {
		return enable;
	}


	public void setEnable(boolean enable) {
		this.enable = enable;
	}


	public Set<UserRole> getRoles() {
		return roles;
	}


	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	
	
}
