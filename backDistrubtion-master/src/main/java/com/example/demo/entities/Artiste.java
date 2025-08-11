package com.example.demo.entities;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="artiste")


public class Artiste extends Users {

	
	
	 /*	public ContentProvider getContentProvider() {
		return contentProvider;
	}

	public void setContentProvider(ContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}*/

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public List<Chanson> getChansons() {
		return chansons;
	}

	public void setChansons(List<Chanson> chansons) {
		this.chansons = chansons;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	public Set<Historique> getHistoriques() {
		return historiques;
	}

	public void setHistoriques(Set<Historique> historiques) {
		this.historiques = historiques;
	}
	
	@ManyToOne()
	
	private ContentProvider contentProvider;
	
	
	
	@OneToMany(cascade = CascadeType.MERGE)
	private List<RbtTT> rbts = new ArrayList<RbtTT>();
	
	//@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(name = "Cp_id", referencedColumnName = "cpId")
   // @JsonIgnoreProperties("storyList")
	 /*@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinColumn(name = "cpId", referencedColumnName = "Id")
	    @JsonIgnoreProperties("artisteList")
    private ContentProvider contentProvider;*/
		/*@ManyToOne
		@JoinColumn (name="idContentProvider", referencedColumnName = "id")
		private ContentProvider contentProvider;*/
	public Artiste() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Artiste(String email,String nom,String prenom,String nArtistique,	String password) {
		super();
		this.email = email;
		
		this.nom = nom;
		this.prenom = prenom;
		this.nArtistique=nArtistique;
		this.password = password;
		
	}
	public Artiste(String name, String type, byte[] picByte) {

		this.name = name;
		this.type = type;
		this.picByte = picByte;

	}
	
	
	/*@OneToMany(mappedBy = "artiste", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("artiste")
    private List<Chanson> chansons = new ArrayList<Chanson>();*/
	
	
	@ManyToOne
	@JoinColumn(name = "idlabel", referencedColumnName = "id")
	private Label label;
	
	@OneToMany(mappedBy = "artiste", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("artiste")
    private List<Chanson> chansons = new ArrayList<Chanson>();

	
	
	@OneToMany(mappedBy = "art", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("art")
    private List<Album> albums = new ArrayList<Album>();
	

	@ManyToOne
	private Details details;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Historique> historiques;


	public Artiste(Integer id, Date cdate, Date udate, String nom, String prenom, byte[] image, String picture,
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

	public Artiste(Integer id, String nom) {
		super(id, nom);
		// TODO Auto-generated constructor stub
	}

	public Artiste(String nom, String prenom, String email, String password) {
		super(nom, prenom, email, password);
		// TODO Auto-generated constructor stub
	}

	public Artiste(String email, String password) {
		super(email, password);
		// TODO Auto-generated constructor stub
	}

	public ContentProvider getContentProvider() {
		return contentProvider;
	}

	public void setContentProvider(ContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}

	
	
	/*@ManyToOne
   	@JoinColumn (name="idlabelArtiste", referencedColumnName = "id")
   	private Label label;*/

	
}
