package entity;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="users")

public class Users {

	@Id
	private Integer user_id;
	
	@Column(name="user_fullname")
	private String user_fullname;
	
	@Column(name="role")
	private String role;
	
	@Column(name="password")
	private String password;
	
	@Column(name="login")
	private String login;
	
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Collection<Job> job;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_fullname() {
		return user_fullname;
	}

	public void setUser_fullname(String user_fullname) {
		this.user_fullname = user_fullname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
	
	@Override
	public String toString() {
		return user_id + " " + user_fullname + " " + role + " " + login + " " + password;
	}
}