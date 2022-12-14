package com.example.demo2.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_mst")
public class UserMst {
    
	
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
	private int id;
    
    private String name;

	@Column(nullable  = false)
    private String firstName;
    
    @Column(nullable  = false)
    private String lastName;
    
    @Column(unique = true)
    private String mobileNumber;
    
    @Column(unique = true)
    private String email;
    
    private double height;
    
    private double weight;
    
    private char gender;
    
    
    private int age;
    
    private String password;
    
    private String role;
    
    public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public double getHeight() {
		return height;
	}


	public void setHeight(double height) {
		this.height = height;
	}


	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}


	public char getGender() {
		return gender;
	}


	public void setGender(char gender) {
		this.gender = gender;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}



	public UserMst() {
		
		this.id = id;
		this.name= name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.height = height;
		this.weight = weight;
		this.gender = gender;
		this.age = age;
	}


	public UserMst(int id, String name, String firstName, String lastName, String mobileNumber, String email,
			double height, double weight, char gender, int age, String password, String role) {
		super();
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.height = height;
		this.weight = weight;
		this.gender = gender;
		this.age = age;
		this.password = password;
		this.role = role;
	}





	
    
}
