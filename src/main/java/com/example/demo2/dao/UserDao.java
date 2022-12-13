package com.example.demo2.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo2.entities.UserMst;

@Repository
public interface UserDao extends JpaRepository<UserMst, Long>{

	public List<UserMst> findByName(String name);
	
	@Query(value = " UPDATE user_mst SET name = :name WHERE id = :userId", nativeQuery = true)
	public void updateName(int userId, String name);
	
	
	@Query(value = "SELECT * FROM user_mst WHERE name LIKE UPPER('%:search%') ", nativeQuery = true)
	public List<UserMst> searchUser(String search );
	
	@Query(value = "SELECT * FROM user_mst WHERE mobile_number = :mobileNumber OR email= :email", nativeQuery = true)
	public Optional<UserMst>  fatchUser(String mobileNumber, String email) ;

	
	
}
