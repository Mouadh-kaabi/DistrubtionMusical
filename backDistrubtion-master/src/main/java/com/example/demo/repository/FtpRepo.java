package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.FTP;

public interface FtpRepo extends JpaRepository<FTP, Integer> {

}
