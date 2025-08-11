package com.example.demo.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Artiste;
import com.example.demo.entities.ContentProvider;
import com.example.demo.entities.ERole;
import com.example.demo.entities.Label;
import com.example.demo.entities.Role;
import com.example.demo.entities.Users;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.AdminRepo;
import com.example.demo.repository.ArtisteRepo;
import com.example.demo.repository.ContentProviderRepo;
import com.example.demo.repository.LabelRepo;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.UserDetailsImpl;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
	@Autowired
	  AuthenticationManager authenticationManager;

	  @Autowired
	  UserRepo userrepository;
	  
	 /* @Autowired
	  FournisseurDeezerRepo fournisseurDeezerRepo ;*/
	  
	  @Autowired
	  ContentProviderRepo contentProviderRepo ;
	  @Autowired
	  AdminRepo adminRepo ;
	  
	  @Autowired
	  ArtisteRepo artisteRepo ;

	  @Autowired
	  RoleRepo roleRepository;

	  @Autowired
	  PasswordEncoder encoder;

	  @Autowired
	  JwtUtils jwtUtils;
	  
	  @Autowired
	  SendEmail sendEmail ;
	  
	  @Autowired
	  LabelRepo labelRepo;
	  
	  @PostMapping("/signin")
		public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());
			
			System.out.println("heeloo");
			return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getId(),userDetails.getEmail(),roles));
		}
	  
		@PostMapping("/signupAdmin")
		public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignupRequest signUpRequest) throws MessagingException {
			
			if (adminRepo.existsByEmail(signUpRequest.getEmail())) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Email is already in use!"));
			}

			// Create new user's account
			Admin user = new Admin(
					
					 signUpRequest.getEmail(),
					 
					 encoder.encode(signUpRequest.getPassword()
							  ));

			Set<String> strRoles = signUpRequest.getRole();
			Set<Role> roles = new HashSet<>();

			if (strRoles == null) {
				Role userRole = roleRepository.findByName(ERole.ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
					
					
					default:
						Role userRole = roleRepository.findByName(ERole.ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
					}
				});
			}

			user.setRoles(roles);
			adminRepo.save(user);
			
			

			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}

		
		@PostMapping("/signupArtiste")
		public ResponseEntity<?> registerArtiste(@Valid @RequestBody SignupRequest signUpRequest) throws MessagingException {
			
			if (artisteRepo.existsByEmail(signUpRequest.getEmail())) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Email is already in use!"));
			}

			// Create new user's account
			Artiste user = new Artiste(
					
								 signUpRequest.getEmail(),
								 signUpRequest.getNom(),signUpRequest.getPrenom(),
								 signUpRequest.getnArtistique(),
								 encoder.encode(signUpRequest.getPassword()
										  ));

			Set<String> strRoles = signUpRequest.getRole();
			Set<Role> roles = new HashSet<>();

			if (strRoles == null) {
				Role userRole = roleRepository.findByName(ERole.ARTISTE)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
					
					
					default:
						Role userRole = roleRepository.findByName(ERole.ARTISTE)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
					}
				});
			}
			
			user.setRoles(roles);
			artisteRepo.save(user);
			
			

			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}

		@PostMapping("/signupContentProvider")
		public ResponseEntity<?> registerContentProvider(@Valid @RequestBody SignupRequest signUpRequest) throws MessagingException {
			
			if (contentProviderRepo.existsByEmail(signUpRequest.getEmail())) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Email is already in use!"));
			}

			// Create new user's account
			ContentProvider user = new ContentProvider(
					
					signUpRequest.getEmail(),
					 
					 signUpRequest.getNomContent(),
					
					 encoder.encode(signUpRequest.getPassword()
							  ));


			Set<String> strRoles = signUpRequest.getRole();
			Set<Role> roles = new HashSet<>();

			if (strRoles == null) {
				Role userRole = roleRepository.findByName(ERole.Cp)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
					
					
					default:
						Role userRole = roleRepository.findByName(ERole.Cp)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
					}
				});
			}
			
			user.setRoles(roles);
			userrepository.save(user);
			

			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}
		
		
		//label
		
		
		@PostMapping("/signupLabel")
		public ResponseEntity<?> registerLabel(@Valid @RequestBody SignupRequest signUpRequest) throws MessagingException {
			
			if (labelRepo.existsByEmail(signUpRequest.getEmail())) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Email is already in use!"));
			}

			// Create new user's account
			Label user = new Label(
					
					 signUpRequest.getEmail(),
				
					 encoder.encode(signUpRequest.getPassword()
							  ));

			Set<String> strRoles = signUpRequest.getRole();
			Set<Role> roles = new HashSet<>();

			if (strRoles == null) {
				Role userRole = roleRepository.findByName(ERole.label)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
					
					
					default:
						Role userRole = roleRepository.findByName(ERole.label)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
					}
				});
			}
			
			user.setRoles(roles);
			labelRepo.save(user);
			
			

			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}
		
}
