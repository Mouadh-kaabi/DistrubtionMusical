package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Pays;

public interface PaysRepo  extends JpaRepository<Pays, Integer>{

}
