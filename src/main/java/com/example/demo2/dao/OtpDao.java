package com.example.demo2.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo2.entities.OtpDtls;


@Repository
public interface OtpDao  extends JpaRepository<OtpDtls, Integer>{
	
	
	@Query(value = "SELECT * FROM otp_dtls WHERE mobile_no = :mobileNo AND type = :type", nativeQuery = true)
	public Optional<OtpDtls> check(String mobileNo, String type); 
	
	/*
	 * @Transactional
	 * 
	 * @Query(value =
	 * "UPDATE otp_dtls SET otp = :newOtp, otp_txn_id = :otpTxnId WHERE id = :otpId"
	 * , nativeQuery = true) public void updateOtpDtls(String newOtp, String
	 * otpTxnId,Integer otpId);
	 */

	@Transactional
	@Query(value = "UPDATE OtpDtls SET otp = :newOtp, otpTxnId = :otpTxnId WHERE id = :otpId")
	public void updateOtpDtls(String newOtp, String otpTxnId,Integer otpId);
	

	
}
