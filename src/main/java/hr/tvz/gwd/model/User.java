package hr.tvz.gwd.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = -8162650940129072706L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String username;
	private String email;
	private String token;
	private String password;
	private boolean enabled;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Role> roleList;
	
	private Blob network;

	public User() {
		super();
		roleList = new ArrayList<>();
	}

	public User(String username, boolean enabled) {
		super();
		this.username = username;
		this.enabled = enabled;
		roleList = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password != null)
			this.password = new BCryptPasswordEncoder().encode(password);
		else
			this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setRoleList(String... roles) {
		for (String role : roles) {
			Role newRole = new Role();
			newRole.setUser(this);
			newRole.setRole(role);
			roleList.add(newRole);
		}
	}

	public Blob getNetwork() {
		return network;
	}

	public void setNetwork(Blob network) {
		this.network = network;
	}

}
