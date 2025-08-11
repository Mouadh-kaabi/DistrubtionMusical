package com.example.demo.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;




@Entity
public class Fournisseur  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date cdate;
	private String nom; /*
						 * private Date dateF; private float part; private float montant; private String
						 * proposition; private Date udate;
						 */
	@ManyToOne
	private Users user;

	@Transient
	private Integer idUser = new Integer(0);

	@ManyToOne
	private Chanson chanson;

	@Transient
	private Integer idChanson = new Integer(0);

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

	public Fournisseur(Integer id, Date cdate, String nom, Users user, Integer idUser, Chanson chanson,
			Integer idChanson) {
		super();
		this.id = id;
		this.cdate = cdate;
		this.nom = nom;
		this.user = user;
		this.idUser = idUser;
		this.chanson = chanson;
		this.idChanson = idChanson;
	}

	public Fournisseur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Chanson getChanson() {
		return chanson;
	}

	public void setChanson(Chanson chanson) {
		this.chanson = chanson;
	}

	public Integer getIdChanson() {
		return idChanson;
	}

	public void setIdChanson(Integer idChanson) {
		this.idChanson = idChanson;
	}
	
	
	
	
}
