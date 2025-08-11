package com.example.demo.entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Marketing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date cdate ;
	private String nom;
	private Date datemark;
	private String type;
	private float montant;
	private Date udate ;
	private String devise;
	
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="marketing")
	private Set<Historique> historiques;



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



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public Date getDatemark() {
		return datemark;
	}



	public void setDatemark(Date datemark) {
		this.datemark = datemark;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public float getMontant() {
		return montant;
	}



	public void setMontant(float montant) {
		this.montant = montant;
	}



	public Date getUdate() {
		return udate;
	}



	public void setUdate(Date udate) {
		this.udate = udate;
	}



	public String getDevise() {
		return devise;
	}



	public void setDevise(String devise) {
		this.devise = devise;
	}



	public Set<Historique> getHistoriques() {
		return historiques;
	}



	public void setHistoriques(Set<Historique> historiques) {
		this.historiques = historiques;
	}



	public Marketing(Integer id, Date cdate, String nom, Date datemark, String type, float montant, Date udate,
			String devise, Set<Historique> historiques) {
		super();
		this.id = id;
		this.cdate = cdate;
		this.nom = nom;
		this.datemark = datemark;
		this.type = type;
		this.montant = montant;
		this.udate = udate;
		this.devise = devise;
		this.historiques = historiques;
	}



	public Marketing() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
