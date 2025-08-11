package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Operateur;

public interface OperateurRepo extends JpaRepository<Operateur, Integer> {

}
