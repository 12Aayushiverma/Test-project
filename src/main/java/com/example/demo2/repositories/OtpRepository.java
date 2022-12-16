package com.example.demo2.repositories;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo2.entities.OtpDtls;


@Repository
public interface OtpRepository  extends JpaRepository<OtpDtls, Integer>{
	
	
	@Query(value = "SELECT * FROM otp_dtls WHERE mobile_no = :mobileNo AND type = :type", nativeQuery = true)
	public Optional<OtpDtls> check(String mobileNo, String type); 
	

	@Modifying
	@Transactional
	@Query(value = "UPDATE OtpDtls SET otp = :newOtp, otpTxnId = :otpTxnId WHERE id = :otpId")
	public void updateOtpDtls(String newOtp, String otpTxnId,Integer otpId);
	
     @Query(value = "SELECT * FROM otp_dtls WHERE mobile_no= :mobileNo AND type= :type ", nativeQuery = true)
     public Optional<OtpDtls> getOtpDtls(String mobileNo, String type);
     
     @Query(value ="DELETE FROM OtpDtls  WHERE id = :otpId")
      void deleteOtpDtls(Integer otpId);

	
}
