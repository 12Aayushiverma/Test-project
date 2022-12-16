package com.example.demo2.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ACCESS_MANAGEMENT")
public class UserAccessManagement {

	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Id
	 private int id;
	 
	 @Column(name= "username")
	 private String username;
	 
	 @Column(name= "password")
	 private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserAccessManagement(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserAccessManagement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
