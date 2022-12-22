package com.example.demo2.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "Profile_Img_Dtls")
public class ProfileImageDtls {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String path;
	
	@OneToOne
	@JoinColumn(name  ="user_id")
	private UserMst userMst;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProfileImageDtls(int id, String path, UserMst userMst) {
		super();
		this.id = id;
		this.path = path;
		this.userMst = userMst;
	}

	public ProfileImageDtls() {
		// TODO Auto-generated constructor stub
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public UserMst getUserMst() {
		return userMst;
	}

	public void setUserMst(UserMst userMst) {
		this.userMst = userMst;
	}
	
	
	
	
}
