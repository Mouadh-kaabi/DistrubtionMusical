package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Marketing;

public interface MarketingRepo  extends JpaRepository<Marketing, Integer>{

}
