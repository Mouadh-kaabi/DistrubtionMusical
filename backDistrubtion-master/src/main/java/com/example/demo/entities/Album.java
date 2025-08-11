package com.example.demo.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Album {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date cdate;
	private String titre;
	private String isrc;
	private Integer nbrTrack;
	private Date dateA;
	private Date udate;
	
	
	public String name;
	protected String type;
	@Lob
	protected byte[] picByte;
	
	
	
	
	
	
	@OneToMany(mappedBy = "alb", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties("album")
    private List<Chanson> chansons = new ArrayList<Chanson>();
	
	
	
	
	@ManyToOne
   	@JoinColumn (name="idartisteAlbum", referencedColumnName = "id")
   	private Artiste art;
	
	

	@ManyToOne
		private Details details;
	
	 
	
	
	
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public byte[] getPicByte() {
		return picByte;
	}
	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
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
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Date getDateA() {
		return dateA;
	}
	public void setDateA(Date dateA) {
		this.dateA = dateA;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	public Integer getNbrTrack() {
		return nbrTrack;
	}
	public void setNbrTrack(Integer nbrTrack) {
		this.nbrTrack = nbrTrack;
	}
	public String getIsrc() {
		return isrc;
	}
	public void setIsrc(String isrc) {
		this.isrc = isrc;
	}
	public List<Chanson> getChansons() {
		return chansons;
	}
	public void setChansons(List<Chanson> chansons) {
		this.chansons = chansons;
	}
	public Artiste getArt() {
		return art;
	}
	public void setArt(Artiste art) {
		this.art = art;
	}
	public Details getDetails() {
		return details;
	}
	public void setDetails(Details details) {
		this.details = details;
	}
	public Album(String name, String type, byte[] picByte) {
		super();
		this.name = name;
		this.type = type;
		this.picByte = picByte;
	}
	public Album() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	
	
	
}
