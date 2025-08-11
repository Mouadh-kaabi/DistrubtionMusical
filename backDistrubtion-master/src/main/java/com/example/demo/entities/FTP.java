package com.example.demo.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;



@Entity
public class FTP {

	
	
	public static int BINARY_FILE_TYPE;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date cdate;
	private String haut;
	private String identifiant;
	private String mp;
	private Integer port;

	@OneToOne
	private Users user;

    @Transient
    private Integer idUser = new Integer(0);
    
	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

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

	public String getHaut() {
		return haut;
	}

	public void setHaut(String haut) {
		this.haut = haut;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMp() {
		return mp;
	}

	public void setMp(String mp) {
		this.mp = mp;
	}

	public FTP() {
		super();
	}

	@Override
	public String toString() {
		return "FTP [id=" + id + ", cdate=" + cdate + ", haut=" + haut + ", identifiant=" + identifiant + ", mp=" + mp
				+ ", port=" + port + ", user=" + user + ", idUser=" + idUser + "]";
	}
	
}
