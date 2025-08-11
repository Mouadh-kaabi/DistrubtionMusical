package com.example.demo.dao;

import java.time.Duration;
import java.time.LocalDateTime;
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
import com.example.demo.entities.ERole;
import com.example.demo.entities.Message;
import com.example.demo.entities.Role;
import com.example.demo.repository.AdminRepo;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.rest.SendEmail;

@Service
public class AdminDao {

	@Autowired
	RoleRepo roleRepo;

	@Autowired
	AdminRepo adminRepo;

	@Autowired
	CryptDAO cryptDAO;
	
	
	@Autowired
	SendEmail sendEmail ;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	/*
	 * @Autowired PasswordEncoder encoder;
	 */
	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
	public Admin ajouterAdmin(Admin pt) {

		// Optional<Role> r = roleRepo.findByName(ERole.ADMIN);
		// pt.setRoles((Set<Role>) r.get());
		// pt.setPassword(cryptDAO.encryption(pt.getPassword()));
		// pt.setStatus(false);
		adminRepo.save(pt);

		return pt;
	}

	public List<Admin> getAllAdmins() {
		return adminRepo.findAll();
	}

	public Admin getAdmin(Integer id) {

		return adminRepo.findById(id).get();

	}

	public Admin updateAdmin(Admin admin) {
		if (admin.getId() != 0) {
			Admin pt = adminRepo.findById(admin.getId()).get();
			if (pt != null) {
				adminRepo.save(admin);
			}
		}
		return admin;
	}
	
	
	public List<Admin> getAdminByEmail(String email)
	{
		return adminRepo.findByemail(email);
	}

	

public ResponseEntity<Message> forgotPassword(String email) throws MessagingException {

		List<Admin> lu = adminRepo.findByemail(email);

		if (lu.isEmpty()) {
			System.out.println("email not found");
			return new ResponseEntity<Message>(new Message("invalid"), HttpStatus.OK);
		}
		
		if (lu.size() > 1) {
			return new ResponseEntity<Message>(new Message("invalid"), HttpStatus.OK);
		}
		
		Admin user = lu.get(0);
		user.setToken(generateToken());
		user.setToken_creation_date(LocalDateTime.now());

		user = adminRepo.save(user);
		
		sendEmail.sendLinkResetPwdAdmin(user.getEmail(),user.getToken(), "Restaurer votre mot de passe", user.getNom(), user.getPrenom());
		
		return new ResponseEntity<Message>(new Message(user.getToken()), HttpStatus.OK);
	}


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

	List<Admin> lu = adminRepo.findByToken(token);

	if (lu.isEmpty()) {
		return new ResponseEntity<Message>(new Message("Invalid token."), HttpStatus.NOT_FOUND);
	}

	LocalDateTime tokenCreationDate = lu.get(0).getToken_creation_date();

	if (isTokenExpired(tokenCreationDate)) {
		return new ResponseEntity<Message>(new Message("Token expired."), HttpStatus.NOT_FOUND);
	}

	Admin user = lu.get(0);

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


}
