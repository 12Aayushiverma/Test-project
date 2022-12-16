package com.example.demo2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
public class Role {

	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Id
	private Integer id;
	
	private String name;
	
	
}
