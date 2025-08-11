package com.example.demo.entities;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="content_provider")
public class ContentProvider extends Users {

	
	
	
	

	public ContentProvider(Integer integer, String string, List<Artiste> artistes) {
		super();
		this.artistes = artistes;
	}


	/*@OneToMany(cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("content_provider")
    private List<Artiste> artistes = new ArrayList<Artiste>();*/	
	@OneToMany(cascade = CascadeType.MERGE)
    
	private List<Artiste> artistes = new ArrayList<Artiste>();
	
	
	
	@OneToMany(cascade = CascadeType.MERGE)
    
	private List<Chanson> chansons = new ArrayList<Chanson>();
	
	
	@OneToMany(cascade = CascadeType.MERGE)
    
	private List<RbtTT> rbts = new ArrayList<RbtTT>();
	
	
	private Double part_cp;
	
	
	
	public ContentProvider(String name, String type, byte[] picByte) {

		this.name = name;
		this.type = type;
		this.picByte = picByte;

	}
	
	
		
	
	public List<Artiste> getArtistes() {
		return artistes;
	}

	public void setArtistes(List<Artiste> artistes) {
		this.artistes = artistes;
	}

	public Double getPart_cp() {
		return part_cp;
	}

	public void setPart_cp(Double part_cp) {
		this.part_cp = part_cp;
	}

	public ContentProvider(String email,String nomContent	,String password ) {
		super();
		this.email = email;
		
		
		this.nomContent=nomContent;
		this.password = password;
	}


	@ManyToOne
   //	@JoinColumn (name="idChasnonalbum", referencedColumnName = "id")
   	private TunisieTelecom tunisietelecom;

	public TunisieTelecom getTunisietelecom() {
		return tunisietelecom;
	}

	public void setTunisietelecom(TunisieTelecom tunisietelecom) {
		this.tunisietelecom = tunisietelecom;
	}

	public String getNomContent() {
		return nomContent;
	}

	public void setNomContent(String nomContent) {
		this.nomContent = nomContent;
	}

	public ContentProvider() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContentProvider(Integer id, Date cdate, Date udate, String nom, String prenom, byte[] image, String picture,
			String name, String type, byte[] picByte, File[] files, Boolean pro, String nArtistique, String phone,
			String email, String password, Date date, String nationnalite, String cin, Date datecin, byte[] contrat,
			String pictureContrat, double part, double retenu, String proposition, String con, String url,
			String nameContrat, String typeContrat, String contratpdf, Set<Role> roles,
			List<Communication> communications) {
		super(id, cdate, udate, nom, prenom, image, picture, name, type, picByte, files, pro, nArtistique, phone, email,
				password, date, nationnalite, cin, datecin, contrat, pictureContrat, part, retenu, proposition, con, url,
				nameContrat, typeContrat, contratpdf, roles, communications);
		// TODO Auto-generated constructor stub
	}

	public ContentProvider(Integer id, String nom) {
		super(id, nom);
		// TODO Auto-generated constructor stub
	}

	public ContentProvider(String nom, String prenom, String phone, String email, String password) {
		super(nom, prenom, phone, email, password);
		// TODO Auto-generated constructor stub
	}

	public ContentProvider(String nom, String prenom, String email, String password) {
		super(nom, prenom, email, password);
		// TODO Auto-generated constructor stub
	}

	public ContentProvider(String email, String password) {
		super(email, password);
		// TODO Auto-generated constructor stub
	}




	public List<Chanson> getChansons() {
		return chansons;
	}




	public void setChansons(List<Chanson> chansons) {
		this.chansons = chansons;
	}
	
	
	
}
