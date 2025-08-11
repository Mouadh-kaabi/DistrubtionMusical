package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class RbtOrange {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer id;
	
	private String album_nam_engl ;
	
	private String album_nam_arb ;
	
	private String label ; 
	
	private Date orgDate ;
	
	private String Language ;
	
	private String explicit_lycris;
	
	private String version ;
	
	private String cp_categ ;
	
	private String  cp_subcat ; 
	
	private Number rating ;
	
	private String name_artist ;
	
	
	private String role_1 ;
	
	private String isprimary ;
	
	public String name;
	protected String type;
	@Lob
	protected byte[] picByte;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAlbum_nam_engl() {
		return album_nam_engl;
	}

	public void setAlbum_nam_engl(String album_nam_engl) {
		this.album_nam_engl = album_nam_engl;
	}

	public String getAlbum_nam_arb() {
		return album_nam_arb;
	}

	public void setAlbum_nam_arb(String album_nam_arb) {
		this.album_nam_arb = album_nam_arb;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Date getOrgDate() {
		return orgDate;
	}

	public void setOrgDate(Date orgDate) {
		this.orgDate = orgDate;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public String getExplicit_lycris() {
		return explicit_lycris;
	}

	public void setExplicit_lycris(String explicit_lycris) {
		this.explicit_lycris = explicit_lycris;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCp_categ() {
		return cp_categ;
	}

	public void setCp_categ(String cp_categ) {
		this.cp_categ = cp_categ;
	}

	public String getCp_subcat() {
		return cp_subcat;
	}

	public void setCp_subcat(String cp_subcat) {
		this.cp_subcat = cp_subcat;
	}

	public Number getRating() {
		return rating;
	}

	public void setRating(Number rating) {
		this.rating = rating;
	}

	public String getName_artist() {
		return name_artist;
	}

	public void setName_artist(String name_artist) {
		this.name_artist = name_artist;
	}

	public String getRole_1() {
		return role_1;
	}

	public void setRole_1(String role_1) {
		this.role_1 = role_1;
	}

	public String getIsprimary() {
		return isprimary;
	}

	public void setIsprimary(String isprimary) {
		this.isprimary = isprimary;
	}

	public RbtOrange() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RbtOrange(Integer id, String album_nam_engl, String album_nam_arb, String label, Date orgDate,
			String language, String explicit_lycris, String version, String cp_categ, String cp_subcat, Number rating,
			String name_artist, String role_1, String isprimary) {
		super();
		this.id = id;
		this.album_nam_engl = album_nam_engl;
		this.album_nam_arb = album_nam_arb;
		this.label = label;
		this.orgDate = orgDate;
		Language = language;
		this.explicit_lycris = explicit_lycris;
		this.version = version;
		this.cp_categ = cp_categ;
		this.cp_subcat = cp_subcat;
		this.rating = rating;
		this.name_artist = name_artist;
		this.role_1 = role_1;
		this.isprimary = isprimary;
	}

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

	
	
	public RbtOrange(String name, String type, byte[] picByte) {
		super();
		this.name = name;
		this.type = type;
		this.picByte = picByte;
	}
	
	
}

