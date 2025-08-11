package com.example.demo.entities;

import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class RbtTT {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer id;
	
	private String titrechanson ;
	
	private String artistePrincipale;
	
	private String fileName ;
	@Lob

	private byte[] picByte;
	
	public String name;
	
	private String genre;
	
	
	private String type;
	
	
	
	
	@ManyToMany()
	private List<Operateur> operateurs;
	
	
	
	@ManyToOne
   	@JoinColumn (name="idartisteChasnon", referencedColumnName = "id")
   	private Artiste artiste;


	@ManyToOne()
	
	private ContentProvider contentProvider;
	
	/*private Integer id;
	
	private String contentFr;
	private String contentArb;
	
	private String artisteFr;
	private String artisteArb;
	
	private String albumFr;
	private String albumArb;
	
	private String genre ;
	
	private String cat ;
	
	
	private String label ;
	
	private Date validity;
	
	public Number relativeValidity;
	
	public boolean iscopyable ;
	
	public String copyright ;
	
	public float activationPrice;
	
	public String name;
	protected String type;
	@Lob
	protected byte[] picByte;
	
	private String content ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContentFr() {
		return contentFr;
	}

	public void setContentFr(String contentFr) {
		this.contentFr = contentFr;
	}

	public String getContentArb() {
		return contentArb;
	}

	public void setContentArb(String contentArb) {
		this.contentArb = contentArb;
	}

	public String getArtisteFr() {
		return artisteFr;
	}

	public void setArtisteFr(String artisteFr) {
		this.artisteFr = artisteFr;
	}

	public String getArtisteArb() {
		return artisteArb;
	}

	public void setArtisteArb(String artisteArb) {
		this.artisteArb = artisteArb;
	}

	public String getAlbumFr() {
		return albumFr;
	}

	public void setAlbumFr(String albumFr) {
		this.albumFr = albumFr;
	}

	public String getAlbumArb() {
		return albumArb;
	}

	public void setAlbumArb(String albumArb) {
		this.albumArb = albumArb;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	public Number getRelativeValidity() {
		return relativeValidity;
	}

	public void setRelativeValidity(Number relativeValidity) {
		this.relativeValidity = relativeValidity;
	}

	public boolean isIscopyable() {
		return iscopyable;
	}

	public void setIscopyable(boolean iscopyable) {
		this.iscopyable = iscopyable;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public float getActivationPrice() {
		return activationPrice;
	}

	public void setActivationPrice(float activationPrice) {
		this.activationPrice = activationPrice;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public RbtTT() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RbtTT(Integer id, String contentFr, String contentArb, String artisteFr, String artisteArb, String albumFr,
			String albumArb, String genre, String cat, String label, Date validity, Number relativeValidity,
			boolean iscopyable, String copyright, float activationPrice, String name, String type, byte[] picByte,
			String content) {
		super();
		this.id = id;
		this.contentFr = contentFr;
		this.contentArb = contentArb;
		this.artisteFr = artisteFr;
		this.artisteArb = artisteArb;
		this.albumFr = albumFr;
		this.albumArb = albumArb;
		this.genre = genre;
		this.cat = cat;
		this.label = label;
		this.validity = validity;
		this.relativeValidity = relativeValidity;
		this.iscopyable = iscopyable;
		this.copyright = copyright;
		this.activationPrice = activationPrice;
		this.name = name;
		this.type = type;
		this.picByte = picByte;
		this.content = content;
	}
	*/
	
	
	/*public RbtTT(Integer id, String titrechanson, String genre, Set<Operateur> operateurs) {
		super();
		this.id = id;
		this.titrechanson = titrechanson;
		this.genre = genre;
		this.operateurs = operateurs;
	}
	
	public RbtTT(Integer id, String titrechanson,String artistePrincipale , String genre, Set<Operateur> operateurs) {
		super();
		this.id = id;
		this.titrechanson = titrechanson;
		this.artistePrincipale = artistePrincipale ;
		this.genre = genre;
		this.operateurs = operateurs;
	}
*/




	public Integer getId() {
		return id;
	}





	public void setId(Integer id) {
		this.id = id;
	}





	public String getTitrechanson() {
		return titrechanson;
	}





	public void setTitrechanson(String titrechanson) {
		this.titrechanson = titrechanson;
	}





	public String getFileName() {
		return fileName;
	}





	public void setFileName(String fileName) {
		this.fileName = fileName;
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





	public String getGenre() {
		return genre;
	}





	public void setGenre(String genre) {
		this.genre = genre;
	}





	public String getType() {
		return type;
	}





	public void setType(String type) {
		this.type = type;
	}





	





	public List<Operateur> getOperateurs() {
		return operateurs;
	}





	public void setOperateurs(List<Operateur> operateurs) {
		this.operateurs = operateurs;
	}





	public RbtTT(String name, String type, byte[] picByte) {
		super();
		this.name = name;
		this.type = type;
		this.picByte = picByte;
	}





	public RbtTT() {
		super();
		// TODO Auto-generated constructor stub
	}





	/*public RbtTT(Integer id, String titrechanson, String fileName, byte[] picByte, String name, String genre,
			String type, Set<Operateur> operateurs) {
		super();
		this.id = id;
		this.titrechanson = titrechanson;
		this.fileName = fileName;
		this.picByte = picByte;
		this.name = name;
		this.genre = genre;
		this.type = type;
		this.operateurs = operateurs;
	}*/





	public String getArtistePrincipale() {
		return artistePrincipale;
	}





	public void setArtistePrincipale(String artistePrincipale) {
		this.artistePrincipale = artistePrincipale;
	}





	public ContentProvider getContentProvider() {
		return contentProvider;
	}





	public void setContentProvider(ContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}





	public Artiste getArtiste() {
		return artiste;
	}





	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}
	
	
	
}

