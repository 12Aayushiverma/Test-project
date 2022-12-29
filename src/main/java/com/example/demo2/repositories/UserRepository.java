package com.example.demo2.repositories;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo2.entities.UserMst;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserMst, Long>{

	public List<UserMst> findByName(String name);
	
	@Query(value = " UPDATE user_mst SET name = :name WHERE id = :userId", nativeQuery = true)
	public void updateName(int userId, String name);
	
	@Query(value = "SELECT * FROM user_mst WHERE id = :userId", nativeQuery = true)
	public Optional<UserMst> fetchUserDtls(Integer userId);
	
	
	@Query(value = "SELECT um FROM UserMst um WHERE um.firstName LIKE UPPER('%' || :search || '%') ", nativeQuery = true)
	public List<UserMst> searchUser(String search );
	
	@Query(value = "SELECT * FROM user_mst WHERE mobile_number = :mobileNumber OR email= :email", nativeQuery = true)
	public Optional<UserMst>  fatchUser(String mobileNumber, String email) ;

	@Query(value = "SELECT * FROM user_mst WHERE mobile_number = :mobileNo OR email= :email ", nativeQuery = true)
	public Optional<UserMst> findByEmailOrMobileNumber( String email, String mobileNo);
	
}
