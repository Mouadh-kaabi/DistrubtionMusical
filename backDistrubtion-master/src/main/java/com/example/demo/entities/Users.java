package com.example.demo.entities;

import java.io.File;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity

@Table(	name = "users", 
uniqueConstraints = { 

	@UniqueConstraint(columnNames = "email") 
})
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public class Users {

	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Integer id;
	private Date cdate;
	private Date udate;
	protected String nom;
	protected String prenom;
	private byte[] image;
	private String picture ; 	
	public String name;
	protected String type;
	@Lob
	protected byte[] picByte;
	private File[] files;
	private Boolean pro = false;

	protected String nArtistique;
	private String phone;
	
	private String token ;
	protected String nomContent ;
	protected String nomFournissuer ;
	
	@Size(max = 50)
	@Email
	protected String email;
	protected String password;
	private Date date;
	private String nationnalite;
	private String cin;
	private Date datecin;
	
	@Lob

	private byte[] contrat;
	private String pictureContrat ;
	private double part =0.0 ;
	private double retenu = 0.0;
	private String proposition;
	
	private String con ; 
	 private String url;
	 
	 private String nameContrat ;
	 private String typeContrat ; 
	 
	 private String contratpdf ;
	 
	 private LocalDateTime token_creation_date;
	 
	 
	 
	 
	 /*	@ManyToOne
		@JoinColumn (name="idrole", referencedColumnName = "idRole")
		private Role role;*/
	/*@OneToMany(fetch = FetchType.LAZY)
	    @JsonIgnoreProperties("user")
	    private Set<Role> roles = new HashSet<>();*/
	 
	 
	 @ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(	name = "user_roles", 
					joinColumns = @JoinColumn(name = "user_id"), 
					inverseJoinColumns = @JoinColumn(name = "role_id"))
		private Set<Role> roles = new HashSet<>();
	 
	 /*	@ManyToOne(fetch = FetchType.LAZY)
		@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
		private Set<Role> roles = new HashSet<>();*/
	 
		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	    @JsonIgnoreProperties("user")
	    private List<Communication> communications = new ArrayList<Communication>();


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


		public Date getUdate() {
			return udate;
		}


		public void setUdate(Date udate) {
			this.udate = udate;
		}


		public String getNom() {
			return nom;
		}


		public void setNom(String nom) {
			this.nom = nom;
		}


		public String getPrenom() {
			return prenom;
		}


		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}


		public byte[] getImage() {
			return image;
		}


		public void setImage(byte[] image) {
			this.image = image;
		}


		public String getPicture() {
			return picture;
		}


		public void setPicture(String picture) {
			this.picture = picture;
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


		public File[] getFiles() {
			return files;
		}


		public void setFiles(File[] files) {
			this.files = files;
		}


		public Boolean getPro() {
			return pro;
		}


		public void setPro(Boolean pro) {
			this.pro = pro;
		}


		public String getnArtistique() {
			return nArtistique;
		}


		public void setnArtistique(String nArtistique) {
			this.nArtistique = nArtistique;
		}


		public String getPhone() {
			return phone;
		}


		public void setPhone(String phone) {
			this.phone = phone;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public Date getDate() {
			return date;
		}


		public void setDate(Date date) {
			this.date = date;
		}


		public String getNationnalite() {
			return nationnalite;
		}


		public void setNationnalite(String nationnalite) {
			this.nationnalite = nationnalite;
		}


		public String getCin() {
			return cin;
		}


		public void setCin(String cin) {
			this.cin = cin;
		}


		public Date getDatecin() {
			return datecin;
		}


		public void setDatecin(Date datecin) {
			this.datecin = datecin;
		}


		public byte[] getContrat() {
			return contrat;
		}


		public void setContrat(byte[] contrat) {
			this.contrat = contrat;
		}


		public String getPictureContrat() {
			return pictureContrat;
		}


		public void setPictureContrat(String pictureContrat) {
			this.pictureContrat = pictureContrat;
		}


		public double getPart() {
			return part;
		}


		public void setPart(double part) {
			this.part = part;
		}


		public double getRetenu() {
			return retenu;
		}


		public void setRetenu(double retenu) {
			this.retenu = retenu;
		}


		public String getProposition() {
			return proposition;
		}


		public void setProposition(String proposition) {
			this.proposition = proposition;
		}


		public String getCon() {
			return con;
		}


		public void setCon(String con) {
			this.con = con;
		}


		public String getUrl() {
			return url;
		}


		public void setUrl(String url) {
			this.url = url;
		}


		public String getNameContrat() {
			return nameContrat;
		}


		public void setNameContrat(String nameContrat) {
			this.nameContrat = nameContrat;
		}


		public String getTypeContrat() {
			return typeContrat;
		}


		public void setTypeContrat(String typeContrat) {
			this.typeContrat = typeContrat;
		}


		public String getContratpdf() {
			return contratpdf;
		}


		public void setContratpdf(String contratpdf) {
			this.contratpdf = contratpdf;
		}


		


	


		public Set<Role> getRoles() {
			return roles;
		}


		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}


		public List<Communication> getCommunications() {
			return communications;
		}


		public void setCommunications(List<Communication> communications) {
			this.communications = communications;
		}


		public Users() {
			super();
			// TODO Auto-generated constructor stub
		}


	

		


		

		

		

		@Override
		public String toString() {
			return "Users [id=" + id + ", cdate=" + cdate + ", udate=" + udate + ", nom=" + nom + ", prenom=" + prenom
					+ ", image=" + Arrays.toString(image) + ", picture=" + picture + ", name=" + name + ", type=" + type
					+ ", picByte=" + Arrays.toString(picByte) + ", files=" + Arrays.toString(files) + ", pro=" + pro
					+ ", nArtistique=" + nArtistique + ", phone=" + phone + ", email=" + email + ", password="
					+ password + ", date=" + date + ", nationnalite=" + nationnalite + ", cin=" + cin + ", datecin="
					+ datecin + ", contrat=" + Arrays.toString(contrat) + ", pictureContrat=" + pictureContrat
					+ ", part=" + part + ", retenu=" + retenu + ", proposition=" + proposition + ", con=" + con
					+ ", url=" + url + ", nameContrat=" + nameContrat + ", typeContrat=" + typeContrat + ", contratpdf="
					+ contratpdf + ", roles=" + roles + ", communications=" + communications + "]";
		}


		public Users(Integer id, Date cdate, Date udate, String nom, String prenom, byte[] image, String picture,
				String name, String type, byte[] picByte, File[] files, Boolean pro, String nArtistique, String phone,
				String email, String password, Date date, String nationnalite, String cin, Date datecin, byte[] contrat,
				String pictureContrat, double part, double retenu, String proposition, String con, String url,
				String nameContrat, String typeContrat, String contratpdf, Set<Role> roles,
				List<Communication> communications) {
			super();
			this.id = id;
			this.cdate = cdate;
			this.udate = udate;
			this.nom = nom;
			this.prenom = prenom;
			this.image = image;
			this.picture = picture;
			this.name = name;
			this.type = type;
			this.picByte = picByte;
			this.files = files;
			this.pro = pro;
			this.nArtistique = nArtistique;
			this.phone = phone;
			this.email = email;
			this.password = password;
			this.date = date;
			this.nationnalite = nationnalite;
			this.cin = cin;
			this.datecin = datecin;
			this.contrat = contrat;
			this.pictureContrat = pictureContrat;
			this.part = part;
			this.retenu = retenu;
			this.proposition = proposition;
			this.con = con;
			this.url = url;
			this.nameContrat = nameContrat;
			this.typeContrat = typeContrat;
			this.contratpdf = contratpdf;
			this.roles = roles;
			this.communications = communications;
		}


		public Users(String email, String password) {
			super();
			this.email = email;
			this.password = password;
		}


		public Users(String nom, String prenom, String email, String password) {
			super();
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.password = password;
		}


		public Users(String nom, String prenom, String phone, String email, String password) {
			super();
			this.nom = nom;
			this.prenom = prenom;
			this.phone = phone;
			this.email = email;
			this.password = password;
		}


		public Users(Integer id, String nom) {
			super();
			this.id = id;
			this.nom = nom;
		}


		public String getNomContent() {
			return nomContent;
		}


		public void setNomContent(String nomContent) {
			this.nomContent = nomContent;
		}


		public String getNomFournissuer() {
			return nomFournissuer;
		}


		public void setNomFournissuer(String nomFournissuer) {
			this.nomFournissuer = nomFournissuer;
		}


		public String getToken() {
			return token;
		}


		public void setToken(String token) {
			this.token = token;
		}


		public LocalDateTime getToken_creation_date() {
			return token_creation_date;
		}


		public void setToken_creation_date(LocalDateTime token_creation_date) {
			this.token_creation_date = token_creation_date;
		}


		
		
		
	 
	 
	/* @OneToMany(mappedBy="user")
		private Set<Chanson>chansons ; */
		
		
		
}
