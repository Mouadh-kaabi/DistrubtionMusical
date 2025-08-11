package com.example.demo.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="details")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)

//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="details_type")

public class Details {

	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Integer id;
	private Date cdate;
	private String namea;
	private String namef;
	private String nomC;
	private String album;
	
	 @Column(name = "date1")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date1;
	
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date2;
	private float uniteprice;
	private Integer quantite;
	private String plateforme;
	private Double netrevenu = 0.0;
	private Double grossrevenu = 0.0;
	private String devise;
	private String label;
	private String upc;
	private String isrc;
	private String content;
	private String producer;
	private String category;
	private String type;
	private String country;
	private Date udate;
	private Double TTC;
	private Double TTC_EUR;
	private Double TTC_USD;
	private Double part_smart;
	private Double tax_telecom;
	private Double part_TTC;
	private Double HTVA;
	private Double part_artiste;
	private Boolean paye = false;
	private String pays;
	private String type_vente;
	private String type_stream;
	private String file;
	private String abonnement;
	private String nom_colonne;
	private String nomfournisseur ;
	private String subCategory ; 
	private Long toneId ;
	
	private String contentProvider ; 
	
	private String packName ;
	
	private String subscriberType ; 
	
	private String toneName ; 
	
	private String channel ; 
	
	private String userInput ;
	
	private String language ;
	
	private Double part_sixD;
	private Double part_TT;
	
	private Double part_smart_globale ; 
	
	private Integer revenu;
	
	

	private Double part_cp;
	
	private Double part_fournisseur;
	
	private Double part_TT_ton;

	private Double part_TT_ins;
	private Double Totalnetderedevance ; 
	
	public Double parSmatHt = 0.0 ;
	
	@OneToMany(mappedBy = "details", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("details")
    private List<Album> albums = new ArrayList<Album>();
	
	@OneToMany(mappedBy = "details", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("details")
    private List<Artiste> artistes = new ArrayList<Artiste>();
	
	
	
	
	@OneToMany(mappedBy = "details", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("details")
    private List<Platforme> platformes = new ArrayList<Platforme>();
	
	@OneToMany(mappedBy = "details", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("details")
    private List<Chanson> chasnons = new ArrayList<Chanson>();
	
	
	@OneToMany(mappedBy = "details", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("details")
    private List<Pays> listpays = new ArrayList<Pays>();
	
	
	@OneToOne(mappedBy="details")
	private Historique historique;


	public String getContentProvider() {
		return contentProvider;
	}


	public void setContentProvider(String contentProvider) {
		this.contentProvider = contentProvider;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getNomfournisseur() {
		return nomfournisseur;
	}


	public void setNomfournisseur(String nomfournisseur) {
		this.nomfournisseur = nomfournisseur;
	}


	public Double getPart_fournisseur() {
		return part_fournisseur;
	}


	public void setPart_fournisseur(Double part_fournisseur) {
		this.part_fournisseur = part_fournisseur;
	}


	public Date getCdate() {
		return cdate;
	}


	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}


	

	


	

	public Date getDate1() {
		return date1;
	}


	public void setDate1(Date date1) {
		this.date1 = date1;
	}


	public Date getDate2() {
		return date2;
	}


	public void setDate2(Date date2) {
		this.date2 = date2;
	}


	public float getUniteprice() {
		return uniteprice;
	}


	public void setUniteprice(float uniteprice) {
		this.uniteprice = uniteprice;
	}


	public Integer getQuantite() {
		return quantite;
	}


	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}




	public Double getNetrevenu() {
		return netrevenu;
	}


	public void setNetrevenu(Double netrevenu) {
		this.netrevenu = netrevenu;
	}


	public Double getGrossrevenu() {
		return grossrevenu;
	}


	public double setGrossrevenu(Double grossrevenu) {
		return this.grossrevenu = grossrevenu;
	}


	


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public String getUpc() {
		return upc;
	}


	public void setUpc(String upc) {
		this.upc = upc;
	}


	public String getIsrc() {
		return isrc;
	}


	public void setIsrc(String isrc) {
		this.isrc = isrc;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getProducer() {
		return producer;
	}


	public void setProducer(String producer) {
		this.producer = producer;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public Date getUdate() {
		return udate;
	}


	public void setUdate(Date udate) {
		this.udate = udate;
	}


	public Double getTTC() {
		return TTC;
	}


	public void setTTC(Double tTC) {
		TTC = tTC;
	}


	public Double getTTC_EUR() {
		return TTC_EUR;
	}


	public void setTTC_EUR(Double tTC_EUR) {
		TTC_EUR = tTC_EUR;
	}


	public Double getTTC_USD() {
		return TTC_USD;
	}


	public void setTTC_USD(Double tTC_USD) {
		TTC_USD = tTC_USD;
	}


	public Double getPart_smart() {
		return part_smart;
	}


	public void setPart_smart(Double part_smart) {
		this.part_smart = part_smart;
	}


	public Double getTax_telecom() {
		return tax_telecom;
	}


	public void setTax_telecom(Double tax_telecom) {
		this.tax_telecom = tax_telecom;
	}


	public Double getPart_TTC() {
		return part_TTC;
	}


	public void setPart_TTC(Double part_TTC) {
		this.part_TTC = part_TTC;
	}


	public Double getHTVA() {
		return HTVA;
	}


	public void setHTVA(Double hTVA) {
		HTVA = hTVA;
	}


	public Double getPart_artiste() {
		return part_artiste;
	}


	public void setPart_artiste(Double part_artiste) {
		this.part_artiste = part_artiste;
	}


	public Boolean getPaye() {
		return paye;
	}


	public void setPaye(Boolean paye) {
		this.paye = paye;
	}


	public String getPays() {
		return pays;
	}


	public void setPays(String pays) {
		this.pays = pays;
	}


	public String getType_vente() {
		return type_vente;
	}


	public void setType_vente(String type_vente) {
		this.type_vente = type_vente;
	}


	public String getType_stream() {
		return type_stream;
	}


	public void setType_stream(String type_stream) {
		this.type_stream = type_stream;
	}


	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}


	public String getAbonnement() {
		return abonnement;
	}


	public void setAbonnement(String abonnement) {
		this.abonnement = abonnement;
	}


	public String getNom_colonne() {
		return nom_colonne;
	}


	public void setNom_colonne(String nom_colonne) {
		this.nom_colonne = nom_colonne;
	}


	public String getSubCategory() {
		return subCategory;
	}


	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}


	public Long getToneId() {
		return toneId;
	}


	public void setToneId(Long toneId) {
		this.toneId = toneId;
	}


/*	public String getContentProvider() {
		return contentProvider;
	}


	public void setContentProvider(String contentProvider) {
		this.contentProvider = contentProvider;
	}*/


	public String getPackName() {
		return packName;
	}


	public void setPackName(String packName) {
		this.packName = packName;
	}


	public String getSubscriberType() {
		return subscriberType;
	}


	public void setSubscriberType(String subscriberType) {
		this.subscriberType = subscriberType;
	}


	public String getToneName() {
		return toneName;
	}


	public void setToneName(String toneName) {
		this.toneName = toneName;
	}


	public String getChannel() {
		return channel;
	}


	public void setChannel(String channel) {
		this.channel = channel;
	}


	public String getUserInput() {
		return userInput;
	}


	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public Double getPart_sixD() {
		return part_sixD;
	}


	public void setPart_sixD(Double part_sixD) {
		this.part_sixD = part_sixD;
	}


	public Double getPart_TT() {
		return part_TT;
	}


	public void setPart_TT(Double part_TT) {
		this.part_TT = part_TT;
	}


	public Double getPart_smart_globale() {
		return part_smart_globale;
	}


	public void setPart_smart_globale(Double part_smart_globale) {
		this.part_smart_globale = part_smart_globale;
	}


	public Integer getRevenu() {
		return revenu;
	}


	public void setRevenu(Integer revenu) {
		this.revenu = revenu;
	}


	public Double getPart_cp() {
		return part_cp;
	}


	public void setPart_cp(Double part_cp) {
		this.part_cp = part_cp;
	}


	public Double getPart_TT_ton() {
		return part_TT_ton;
	}


	public void setPart_TT_ton(Double part_TT_ton) {
		this.part_TT_ton = part_TT_ton;
	}


	public Double getPart_TT_ins() {
		return part_TT_ins;
	}


	public void setPart_TT_ins(Double part_TT_ins) {
		this.part_TT_ins = part_TT_ins;
	}





	public Double getTotalnetderedevance() {
		return Totalnetderedevance;
	}


	public void setTotalnetderedevance(Double totalnetderedevance) {
		Totalnetderedevance = totalnetderedevance;
	}


	public List<Album> getAlbums() {
		return albums;
	}


	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}


	public List<Artiste> getArtistes() {
		return artistes;
	}


	public void setArtistes(List<Artiste> artistes) {
		this.artistes = artistes;
	}


	

	public List<Platforme> getPlatformes() {
		return platformes;
	}


	public void setPlatformes(List<Platforme> platformes) {
		this.platformes = platformes;
	}


	public List<Chanson> getChasnons() {
		return chasnons;
	}


	public void setChasnons(List<Chanson> chasnons) {
		this.chasnons = chasnons;
	}


	public List<Pays> getListpays() {
		return listpays;
	}


	public void setListpays(List<Pays> listpays) {
		this.listpays = listpays;
	}


	public Historique getHistorique() {
		return historique;
	}


	public void setHistorique(Historique historique) {
		this.historique = historique;
	}


	public String getNamea() {
		return namea;
	}


	public void setNamea(String namea) {
		this.namea = namea;
	}


	public String getNamef() {
		return namef;
	}


	public void setNamef(String namef) {
		this.namef = namef;
	}


	public String getNomC() {
		return nomC;
	}


	public void setNomC(String nomC) {
		this.nomC = nomC;
	}


	public String getAlbum() {
		return album;
	}


	public void setAlbum(String album) {
		this.album = album;
	}


	public String getPlateforme() {
		return plateforme;
	}


	public void setPlateforme(String plateforme) {
		this.plateforme = plateforme;
	}


	public String getDevise() {
		return devise;
	}


	public void setDevise(String devise) {
		this.devise = devise;
	}


	public Double getParSmatHt() {
		return parSmatHt;
	}


	public void setParSmatHt(Double parSmatHt) {
		this.parSmatHt = parSmatHt;
	}


	 
	
	
	
	
	
	
	
}
