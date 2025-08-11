package com.example.demo.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;





@Entity
public class Historique {

	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private  Integer id  ;	
		private  Date cdate  ;
		private  Byte[] contrat  ;
		private  String partBen  ;
		private  float montPaye  ;
		private  float montNPaye  ;
		private  float retenu  ;
		private  Date  udate  ;
		
		@ManyToOne
		private Artiste user;
		
		
		@ManyToOne
		private Marketing marketing;
		
		
		@OneToOne
		private Details details; 
		
		
		
}
