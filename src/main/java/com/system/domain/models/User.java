package com.system.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;

@Entity
@Table(name="\"user\"")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String username;
	@Column
	@JsonIgnore
	private String password;

	public User() {

	}

	public User(String name, String password) {
		this.username = name;
		this.password = password;
	}

	public String getUsername() {
        return username;
    }

	public void setUsername(String name) {
		this.username = name;
	}
	public String getPassword() {
        return password;
    }

	public void setPassword(String password) {
		this.password = password;
	}

}