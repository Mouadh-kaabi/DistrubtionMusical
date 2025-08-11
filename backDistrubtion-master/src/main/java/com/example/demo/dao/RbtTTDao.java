package com.example.demo.dao;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Chanson;
import com.example.demo.entities.RbtTT;
import com.example.demo.repository.RbtTTRepo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.io.Files;

@Service
public class RbtTTDao {

	//RbtTT
	@Autowired
	RbtTTRepo rbtTTRepo ;
	
	public RbtTT addRbt(RbtTT rbtTT) {
		
		//rbtTT.setLabel("Smart");
		//rbtTT.setRelativeValidity(7);
		//rbtTT.setIscopyable(true);
		//rbtTT.setCopyright("smart");
		return rbtTTRepo.save(rbtTT);
	}
	
	public List<RbtTT> getAllRbtTT() {
		return rbtTTRepo.findAll();
	}
	
	
	
	/*public ResponseEntity<?> uploadChanson (MultipartFile file, Integer id) throws JsonParseException , JsonMappingException , Exception
	 {
		Optional<RbtTT> user =  rbtTTRepo.findById(id);
		
		System.out.println("Ok .............");   
		
		if(!user.isPresent())
			return new ResponseEntity<RbtTT>(user.get(), HttpStatus.NOT_FOUND);
		
		
		String folder="C:/uploadRbt/";
		 byte[] bytes = file.getBytes();
		 String imagePath = folder + file.getOriginalFilename();
		 String imageName = file.getOriginalFilename();
		 Path path = Paths.get(imagePath);
		Files.write(bytes, path.toFile());
		
		user.get().setFileName(imageName);
		rbtTTRepo.save(user.get());
     
     return new ResponseEntity<RbtTT>(user.get(),HttpStatus.OK);
	 }*/
	public ResponseEntity<?> uploadRbt (MultipartFile file, Integer id) throws JsonParseException , JsonMappingException , Exception
	 {
		Optional<RbtTT> user = rbtTTRepo.findById(id);
		
		System.out.println("Ok ............."+rbtTTRepo.findById(id));   
		
		if(!user.isPresent())
			return new ResponseEntity<RbtTT>(user.get(), HttpStatus.NOT_FOUND);
		
		
		String folder="C:/uploadRbt/";
		 byte[] bytes = file.getBytes();
		 String imagePath = folder + file.getOriginalFilename();
		 String imageName = file.getOriginalFilename();
		 Path path = Paths.get(imagePath);
		Files.write(bytes, path.toFile());
		
		user.get().setFileName(imageName);
		rbtTTRepo.save(user.get());
     
     return new ResponseEntity<RbtTT>(user.get(),HttpStatus.OK);
	 }
	
	
	public byte[] getRbt(Integer id) throws Exception{
		 
		 Optional<RbtTT> patient =  rbtTTRepo.findById(id);
						
		if(!patient.isPresent())
			return null;
		
		String folder="C:/uploadRbt/";
		//context.getRealPath(folder)
		
		Path path = Paths.get(folder+patient.get().getFileName());
		File file = new File(path.toFile().toString());
		
		if(file.exists())
			return Files.toByteArray(file);
		else
			return null;
	 }
	
}
