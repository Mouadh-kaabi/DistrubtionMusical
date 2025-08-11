package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Album;
import com.example.demo.entities.Artiste;
import com.example.demo.entities.Chanson;
import com.example.demo.repository.AlbumRepo;
import com.example.demo.repository.ArtisteRepo;
import com.example.demo.repository.ChasnonRepo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class ChansonDao {

	@Autowired
	ChasnonRepo ChasnonRepo;

	@Autowired
	AlbumRepo albumRepo;

	@Autowired

	ArtisteRepo artisteRepo;
	


	

	public ResponseEntity<Chanson> addChonson(Chanson c) {

		// ChasnonRepo.save(c);
		System.out.println(c.getAlb().getId());
		Optional<Album> album = albumRepo.findById(c.getAlb().getId());

		if (album.isPresent()) {
			c.setAlb(album.get());

			ChasnonRepo.save(c);

			return new ResponseEntity<Chanson>(c, HttpStatus.OK);
		}
		/*
		 * else { ChasnonRepo.save(c);
		 * 
		 * 
		 * }
		 */

		return new ResponseEntity<Chanson>(c, HttpStatus.NOT_FOUND);
	}

	public Chanson add(Chanson chanson) {
		return ChasnonRepo.save(chanson);
	}

	public List<Chanson> getAllchanson() {
		return ChasnonRepo.findAll();
	}

	public Chanson getChanson(Integer id) {

		return ChasnonRepo.findById(id).get();

	}

	public Chanson updateChanson(Chanson chanson) {
		if (chanson.getId() != 0) {
			Chanson pt = ChasnonRepo.findById(chanson.getId()).get();
			if (pt != null) {
				ChasnonRepo.save(chanson);
			}
		}
		return chanson;
	}

	
public ResponseEntity<Chanson> addConsultaionDetails(Chanson c) {
		
		Optional<Artiste> doctor = artisteRepo.findById(c.getArtiste().getId());
		System.out.println(c.getArtiste().getId());
		if(doctor.isPresent()) {
			c.setArtiste(doctor.get());
			//Speciality speciality = specialityRepository.save(c.getSpeciality());
		//	c.setSpeciality(speciality);
		//	cDetailsRepository.save(c);
			
			return new ResponseEntity<Chanson>(c, HttpStatus.OK);
		}
			
		return new ResponseEntity<Chanson>(c, HttpStatus.NOT_FOUND);
	}









	
}
