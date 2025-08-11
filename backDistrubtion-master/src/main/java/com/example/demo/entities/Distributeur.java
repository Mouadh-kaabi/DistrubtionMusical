package com.example.demo.entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity

public class Distributeur {

	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private  Integer id  ;	
		private  Date cdate  ;
		private  String nomd  ;
		private  Date udate  ;
		
		@ManyToOne
		private Platforme plateforme;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Date getCdate() {
			return cdate;
		}

		public void setCdate(Date cdate) {
			this.cdate = cdate;
		}

		public String getNomd() {
			return nomd;
		}

		public void setNomd(String nomd) {
			this.nomd = nomd;
		}

		public Date getUdate() {
			return udate;
		}

		public void setUdate(Date udate) {
			this.udate = udate;
		}

		public Platforme getPlateforme() {
			return plateforme;
		}

		public void setPlateforme(Platforme plateforme) {
			this.plateforme = plateforme;
		}
		
		
		
		@ManyToMany(mappedBy="distributeurs", cascade = CascadeType.ALL)
		private Set<Platforme> platormes;

		 
		
}
