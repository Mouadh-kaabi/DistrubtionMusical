package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Historique;

public interface HistoriqueRepo  extends JpaRepository<Historique, Integer>{

}
