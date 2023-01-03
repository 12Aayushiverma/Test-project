package com.example.demo2.model.payload;


public class UserPayload {
        
	  
	    private String name;
	    
	    private String firstName;
	    
	    private String lastName;
	    
	    private String mobileNumber;
	    
	    private String email;
	    
	    private double height;
	    
	    private double weight;
	    
	    private char gender;    
	    
	    private int age;
	
        private int id;
        
        private String role;
        

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public UserPayload(String name, String firstName, String lastName, String mobileNumber, String email,
				double height, double weight, char gender, int age, int id) {
			super();
			this.name = name;
			this.firstName = firstName;
			this.lastName = lastName;
			this.mobileNumber = mobileNumber;
			this.email = email;
			this.height = height;
			this.weight = weight;
			this.gender = gender;
			this.age = age;
			this.id = id;
		}

		public UserPayload() {
			// TODO Auto-generated constructor stub
		}
        

}
