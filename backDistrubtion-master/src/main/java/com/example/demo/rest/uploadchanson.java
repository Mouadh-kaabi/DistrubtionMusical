package com.example.demo.rest;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Chanson;
import com.example.demo.repository.ChasnonRepo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.io.Files;
@Service
public class uploadchanson {

	@Autowired
	ChasnonRepo chasnonRepo;
	
	public ResponseEntity<?> uploadChanson (MultipartFile file, Integer id) throws JsonParseException , JsonMappingException , Exception
	 {
		Optional<Chanson> user =  chasnonRepo.findById(id);
		
		System.out.println("Ok .............");   
		
		if(!user.isPresent())
			return new ResponseEntity<Chanson>(user.get(), HttpStatus.NOT_FOUND);
		
		
		String folder="C:/upload/";
		 byte[] bytes = file.getBytes();
		 String imagePath = folder + file.getOriginalFilename();
		 String imageName = file.getOriginalFilename();
		 Path path = Paths.get(imagePath);
		Files.write(bytes, path.toFile());
		
		user.get().setFileName(imageName);
		chasnonRepo.save(user.get());
      
      return new ResponseEntity<Chanson>(user.get(),HttpStatus.OK);
	 }
	
	
	public byte[] getChanson(Integer id) throws Exception{
		 
		 Optional<Chanson> patient =  chasnonRepo.findById(id);
						
		if(!patient.isPresent())
			return null;
		
		String folder="C:/upload/";
		//context.getRealPath(folder)
		
		Path path = Paths.get(folder+patient.get().getFileName());
		File file = new File(path.toFile().toString());
		
		if(file.exists())
			return Files.toByteArray(file);
		else
			return null;
	 }
}
