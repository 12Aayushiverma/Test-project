package com.example.demo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo2.entities.ProfileImageDtls;

@Repository
public interface ProfileImgRepository extends JpaRepository<ProfileImageDtls, Integer> {


}
