package com.example.demo.entities;

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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;






@Entity(name="chanson")
public class Chanson {

	
	




	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date cdate;
	private String nom;
	private String sou_nom;
	private String artistePrincipale ;
	
	private String genre;
	private String souGenre;
	private String format ; 
	private Date dateSortieOrigiinale;
	private String producteur ;
	private String copyright ;
	private Date annedeProduction;
	private String code;
	private Date datec;
	private String type;
	private String rbt_src;
	private String featuring;
	private Date udate;
	
	private String plat ; 
	
	private String fileName ;
	@Lob

	private byte[] picByte;
	
	public String name;
	

//	private String type;
	
	
	
	/*@ManyToOne
   	@JoinColumn (name="idArt", referencedColumnName = "id")
   	private Artiste artiste;*/
	
	//@ManyToOne(cascade = CascadeType.ALL)
	
/*	@ManyToOne
   //	@JoinColumn (name="idart", referencedColumnName = "id")
	private  Artiste artiste;*/
	
	
	
	/*@ManyToOne
	private Artiste art;*/
	
	@ManyToOne
   	@JoinColumn (name="idartisteChasnon", referencedColumnName = "id")
   	private Artiste artiste;


	@ManyToOne()
	
	private ContentProvider contentProvider;
	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}




	public Chanson(String type, String name,byte[] picByte) {
		super();
		this.type = type;
		
		this.name = name;
		this.picByte = picByte;
	}




	@ManyToOne
   	@JoinColumn (name="idChasnonalbum", referencedColumnName = "id")
   	private Album alb;
	
	
	
	@ManyToMany(mappedBy="chonsons", cascade = CascadeType.ALL)
	private Set<Operateur> operateurs;
	
	
	@ManyToMany()
	private List<Platforme> platformes;
	
	@ManyToOne
	private Details details;
	
	
	@OneToMany(mappedBy = "chanson", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("chanson")
    private List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();
	

	/*public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}*/
	
	
	

	



	public String getSou_nom() {
		return sou_nom;
	}



	public void setSou_nom(String sou_nom) {
		this.sou_nom = sou_nom;
	}



	public String getArtistePrincipale() {
		return artistePrincipale;
	}



	public void setArtistePrincipale(String artistePrincipale) {
		this.artistePrincipale = artistePrincipale;
	}



	public String getSouGenre() {
		return souGenre;
	}



	public void setSouGenre(String souGenre) {
		this.souGenre = souGenre;
	}



	public String getFormat() {
		return format;
	}



	public void setFormat(String format) {
		this.format = format;
	}



	public Date getDateSortieOrigiinale() {
		return dateSortieOrigiinale;
	}



	public void setDateSortieOrigiinale(Date dateSortieOrigiinale) {
		this.dateSortieOrigiinale = dateSortieOrigiinale;
	}



	public String getProducteur() {
		return producteur;
	}



	public void setProducteur(String producteur) {
		this.producteur = producteur;
	}



	public String getCopyright() {
		return copyright;
	}



	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}



	public Date getAnnedeProduction() {
		return annedeProduction;
	}



	public void setAnnedeProduction(Date annedeProduction) {
		this.annedeProduction = annedeProduction;
	}



	


	public Artiste getArtiste() {
		return artiste;
	}



	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDatec() {
		return datec;
	}

	public void setDatec(Date datec) {
		this.datec = datec;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRbt_src() {
		return rbt_src;
	}

	public void setRbt_src(String rbt_src) {
		this.rbt_src = rbt_src;
	}

	public String getFeaturing() {
		return featuring;
	}

	public void setFeaturing(String featuring) {
		this.featuring = featuring;
	}

	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Chanson(Integer id, Date cdate, String nom, String genre, String code, Date datec, String type,
			String rbt_src, String featuring, Date udate, byte[] picByte, String name) {
		super();
		this.id = id;
		this.cdate = cdate;
		this.nom = nom;
		this.genre = genre;
		this.code = code;
		this.datec = datec;
		this.type = type;
		this.rbt_src = rbt_src;
		this.featuring = featuring;
		this.udate = udate;
		this.picByte = picByte;
		this.name = name;
	}

	



	public Album getAlb() {
		return alb;
	}



	public void setAlb(Album alb) {
		this.alb = alb;
	}



	public Set<Operateur> getOperateurs() {
		return operateurs;
	}



	public void setOperateurs(Set<Operateur> operateurs) {
		this.operateurs = operateurs;
	}



	



	public Details getDetails() {
		return details;
	}



	public void setDetails(Details details) {
		this.details = details;
	}



	public List<Fournisseur> getFournisseurs() {
		return fournisseurs;
	}



	public void setFournisseurs(List<Fournisseur> fournisseurs) {
		this.fournisseurs = fournisseurs;
	}






	 
	



		


		
		
		

		public String getPlat() {
			return plat;
		}



		public void setPlat(String plat) {
			this.plat = plat;
		}



		public Chanson(Integer id, String nom, String genre, String souGenre, String type, String featuring,
				String plat) {
			super();
			this.id = id;
			this.nom = nom;
			this.genre = genre;
			this.souGenre = souGenre;
			this.type = type;
			this.featuring = featuring;
			this.plat = plat;
		}



		
		
		
		public List<Platforme> getPlatformes() {
			return platformes;
		}



		public void setPlatformes(List<Platforme> platformes) {
			this.platformes = platformes;
		}



		public Chanson(
				) {
			super();
			this.id = id;
			this.nom = nom;
			this.artistePrincipale=artistePrincipale;
			this.platformes = platformes;
			this.featuring = featuring;
			this.genre = genre;
			this.souGenre = souGenre;
			this.format = format;
			
			
		}



	


		/*public void chanson(Integer id, Date cdate, String nom, String genre, Date datec, String type, String rbt_src, String featuring, Date udate, Album alb, Artiste art, Set<Platforme> platformes, Set<Operateur> operateurs) {
			this.id = id;
			this.cdate = cdate;
			this.nom = nom;
			this.genre = genre;
			this.datec = datec;
			this.type = type;
			this.rbt_src = rbt_src;
			this.featuring = featuring;
			this.udate = udate;
			this.alb = alb;
			this.art = art;
			this.platformes = platformes;
			this.operateurs = operateurs;
		}*/
	

		public ContentProvider getContentProvider() {
			return contentProvider;
		}



		public void setContentProvider(ContentProvider contentProvider) {
			this.contentProvider = contentProvider;
		}
		
		
		
		
	
}
