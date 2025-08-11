package com.example.demo.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Artiste;
import com.example.demo.entities.ContentProvider;
import com.example.demo.entities.Message;
import com.example.demo.entities.Role;
import com.example.demo.repository.ArtisteRepo;
import com.example.demo.repository.ContentProviderRepo;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.rest.SendEmail;
import java.time.Duration;
@Service

public class ArtisteDao {

	@Autowired
	RoleRepo roleRepo;

	@Autowired
	ArtisteRepo artisteRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
		
	@Autowired
	SendEmail sendEmail ;
	
	@Autowired
	UserRepo userRepo ;
	
	@Autowired
	ContentProviderRepo contentProviderRepo;
	
	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
	
	public Artiste ajouterArtiste(Artiste pt) {

		Optional<Role> r = roleRepo.findByRoleName("ARTISTE");
		pt.setRoles((Set<Role>) r.get());
		// pt.setPassword(cryptDAO.encryption(pt.getPassword()));
		// pt.setStatus(false);
		artisteRepo.save(pt);

		return pt;
	}

	public List<Artiste> getAllArtistes() {
		return artisteRepo.findAll();
	}

	public Artiste getArtiste(Integer id) {

		return artisteRepo.findById(id).get();

	}

	public Artiste updateArtiste(Artiste artiste) {
		if (artiste.getId() != 0) {
			Artiste pt = artisteRepo.findById(artiste.getId()).get();
			if (pt != null) {
				artisteRepo.save(artiste);
			}
		}
		return artiste;
	}
	
	public ResponseEntity<Artiste> changePWD(Integer id, String password) {

		Optional<Artiste> user = artisteRepo.findById(id);
		if(user.isPresent()) {
			System.out.println("hethi password"+password);
			
			this.passwordEncoder = new BCryptPasswordEncoder();
			//user.get().setPassword(password);
			String encodePasswod = this.passwordEncoder.encode(password);
			System.out.println("lhne"+encodePasswod);
			user.get().setPassword(encodePasswod);
			artisteRepo.changePWD(encodePasswod, id);
			
			return new ResponseEntity<Artiste>(user.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Artiste>(new Artiste(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	public ResponseEntity<Message> forgotPassword(String email) throws MessagingException {

		List<Artiste> lu = artisteRepo.findByEmail(email);

		if (lu.isEmpty()) {
			System.out.println("email not found");
			return new ResponseEntity<Message>(new Message("invalid"), HttpStatus.OK);
		}
		
		if (lu.size() > 1) {
			return new ResponseEntity<Message>(new Message("invalid"), HttpStatus.OK);
		}
		
		Artiste user = lu.get(0);
		user.setToken(generateToken());
		user.setToken_creation_date(LocalDateTime.now());

		user = artisteRepo.save(user);
		
		sendEmail.sendLinkResetPwd(user.getEmail(),user.getToken(), "Restaurer votre mot de passe", user.getNom(), user.getPrenom());
		
		return new ResponseEntity<Message>(new Message(user.getToken()), HttpStatus.OK);
	}
	
	/*public ResponseEntity<Message> forgotPassword(String email) throws MessagingException {

		List<Users> lu = userRepo.findByNom(email);

		if (lu.isEmpty()) {
			System.out.println("email not found");
			return new ResponseEntity<Message>(new Message("invalid"), HttpStatus.OK);
		}
		
		if (lu.size() > 1) {
			return new ResponseEntity<Message>(new Message("invalid"), HttpStatus.OK);
		}
		
		Users user = lu.get(0);
		user.setToken(generateToken());
		user.setToken_creation_date(LocalDateTime.now());

		user = userRepo.save(user);
		
		sendEmail.sendLinkResetPwd(user.getEmail(),user.getToken(), "Restaurer votre mot de passe", user.getNom(), user.getPrenom());
		
		return new ResponseEntity<Message>(new Message(user.getToken()), HttpStatus.OK);
	}*/
	
	
	private String generateToken() {
		StringBuilder token = new StringBuilder();

		return token.append(UUID.randomUUID().toString())
				.append(UUID.randomUUID().toString()).toString();
	}
	
	
	private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationDate, now);

		return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
	}
	
	
	
	public ResponseEntity<Message> resetPassword(String token, String password) {

		List<Artiste> lu = artisteRepo.findByToken(token);

		if (lu.isEmpty()) {
			return new ResponseEntity<Message>(new Message("Invalid token."), HttpStatus.NOT_FOUND);
		}

		LocalDateTime tokenCreationDate = lu.get(0).getToken_creation_date();

		if (isTokenExpired(tokenCreationDate)) {
			return new ResponseEntity<Message>(new Message("Token expired."), HttpStatus.NOT_FOUND);
		}

		Artiste user = lu.get(0);

		//user.setPassword(cryptDAO.encryption(password));
		this.passwordEncoder = new BCryptPasswordEncoder();
		//user.get().setPassword(password);
		String encodePasswod = this.passwordEncoder.encode(password);
		System.out.println("lhne"+encodePasswod);
		user.setPassword(encodePasswod);
		user.setToken(null);
		user.setToken_creation_date(null);

		userRepo.save(user);
		return new ResponseEntity<Message>(new Message("success"), HttpStatus.OK);
	}
	
	
	
	public List<Artiste> findArtisteByEmail(String email) {
		return artisteRepo.findByEmail(email);
	}
	
	public Artiste getUserId(String email, String msg) {
		List<Artiste> users = new ArrayList<Artiste>();
		if(msg.equals("email")) {
			users = artisteRepo.findByEmail(email);
		} else {
			users = artisteRepo.findByEmail(email);
		}
		
		if(users.size() == 1)
		{
			return users.get(0);
		}
		else {
			return null;
		}
	}
	
	
	public List<Artiste> getArtisteByEmail(String email)
	{
		return artisteRepo.findByemail(email);
	}
	
	//findBynArtistique
	
	
	public List<Artiste> findBynArtistique(String nArtistique)
	{
		return artisteRepo.findBynArtistique(nArtistique);
	}
	
	
/*public ResponseEntity<Artiste> addConsultaionDetails(Artiste c) {
		
		Optional<ContentProvider> cp = contentProviderRepo.findById(c.getContentProvider().getId());
		System.out.println(c.getContentProvider().getId());
		if(cp.isPresent()) {
			c.setContentProvider(cp.get());
			
			
			artisteRepo.save(c);
			
			return new ResponseEntity<Artiste>(c, HttpStatus.OK);
		}
		return new ResponseEntity<Artiste>(c, HttpStatus.NOT_FOUND);
}*/

}
