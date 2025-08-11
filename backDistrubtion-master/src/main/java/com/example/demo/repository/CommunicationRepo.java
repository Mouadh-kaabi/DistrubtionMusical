package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Communication;

public interface CommunicationRepo extends JpaRepository<Communication, Integer> {

}
