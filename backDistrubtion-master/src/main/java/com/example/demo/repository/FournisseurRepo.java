package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Fournisseur;

public interface FournisseurRepo extends JpaRepository<Fournisseur, Integer> {

}
