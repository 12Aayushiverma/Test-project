package com.example.demo2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo2.entities.CaptchaDtls;

@Repository
public interface CaptchaRepository extends JpaRepository<CaptchaDtls, Integer> {

	public Optional<CaptchaDtls> findByCaptchaCodeAndCaptchaId(String captchaCode, Integer captchaId);
 }
