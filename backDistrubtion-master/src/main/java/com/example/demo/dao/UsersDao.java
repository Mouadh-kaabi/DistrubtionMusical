package com.example.demo.dao;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Message;
import com.example.demo.entities.Users;
import com.example.demo.repository.UserRepo;
import com.example.demo.rest.SendEmail;

@Service
public class UsersDao {

	
	@Autowired
	UserRepo userRepo ;
	
	@Autowired
	SendEmail sendEmail ;
	
	
	/*public Users getUserId(String email, String msg) {
		List<Users> users = new ArrayList<Users>();
		if(msg.equals("email")) {
			users = userRepo.findByEmai(email);
		} else {
			users = userRepo.findByEmai(email);
		}
		
		if(users.size() == 1)
		{
			return users.get(0);
		}
		else {
			return null;
		}
	}*/
	
	
	
	public ResponseEntity<Message> forgotPassword(String email) throws MessagingException {

		Optional<Users> lu = userRepo.findByEmail(email);

	/*	if (lu.isEmpty()) {
			System.out.println("email not found");
			return new ResponseEntity<Message>(new Message("invalid"), HttpStatus.OK);
		}*/
		
		if (lu.hashCode() > 1) {
			return new ResponseEntity<Message>(new Message("invalid"), HttpStatus.OK);
		}
		
		Users user = lu.get();
		user.setToken(generateToken());
		user.setToken_creation_date(LocalDateTime.now());

		user = userRepo.save(user);
		
		sendEmail.sendLinkResetPwd(user.getEmail(),user.getToken(), "Restaurer votre mot de passe", user.getNom(), user.getPrenom());
		
		return new ResponseEntity<Message>(new Message(user.getToken()), HttpStatus.OK);
	}
	
	private String generateToken() {
		StringBuilder token = new StringBuilder();

		return token.append(UUID.randomUUID().toString())
				.append(UUID.randomUUID().toString()).toString();
	}
}
