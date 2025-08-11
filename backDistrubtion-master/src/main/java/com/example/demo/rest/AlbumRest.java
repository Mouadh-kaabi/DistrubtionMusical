package com.example.demo.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.AlbumDao;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Album;
import com.example.demo.entities.Artiste;
import com.example.demo.repository.AlbumRepo;
@CrossOrigin(origins = "http://192.168.1.111:4200")
@RestController
@RequestMapping("/album")

public class AlbumRest {

	
	@Autowired
	AlbumDao albumDao ;
	
	@Autowired
	AlbumRepo albumRepo ; 
	
	/*@PostMapping("/inscriptionAdmin")
	public Admin inscrpition(@RequestBody Admin ad) {
	return adminDAO.ajouterAdmin(ad);
	}*/
	
	
	/*@PutMapping("/update")
	public Album updateAlbum(@RequestBody Album album) {
		return albumDao.updateAlbum(album);
	}*/

	
	
	@RequestMapping(path = "/updateAlbum", method = RequestMethod.PUT)
	public void update(@RequestBody Album u) {
		albumRepo.save(u);
 	} 
	
	@RequestMapping("/by-id/{id}")
	public Album getAlbum(@PathVariable(value = "id") Integer id) {
		return albumDao.getAlbum(id);
	}

	@RequestMapping("/all")
	public List<Album> getAllAlbum() {
		return albumDao.getAllAlbum();
	}

	@PostMapping("/ajouterAlbum")
	public 	Album addAlbum(@RequestBody Album alb)
	{
		return albumDao.addAlbum(alb);
	}
	
	
	@DeleteMapping("/deletealbum/{id}")
	public void deleteAlbum(@PathVariable("id") Integer id) {
		albumRepo.deleteById(id);
	}
	
	@RequestMapping(path = "/uploadCover/{id}", method = RequestMethod.PUT)
	public org.springframework.http.ResponseEntity.BodyBuilder uplaodImage(@RequestParam("coverFile") MultipartFile files,@PathVariable("id") Integer id) throws IOException {
		System.out.println("Original Image Byte Size - " + files.getBytes().length);
		Album img = new Album(files.getOriginalFilename(), files.getContentType(), compressBytes(files.getBytes()));
		albumRepo.savenew(files.getOriginalFilename(), compressBytes(files.getBytes()), files.getContentType(), id);
		return null;
		}
	
	
	
	
	@RequestMapping(path = "/uploadIn", method = RequestMethod.PUT)
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile files) throws IOException {
		System.out.println("Original Image Byte Size - " + files.getBytes().length);
		Album img = new Album(files.getOriginalFilename(), files.getContentType(), compressBytes(files.getBytes()));
		albumRepo.savenewIn(files.getOriginalFilename(), compressBytes(files.getBytes()), files.getContentType());
		return null;
		}
	
/*	@RequestMapping(path = "/"
			+ "/{id}", method = RequestMethod.GET)
	public Album getImage(@PathVariable("id") Integer id) throws IOException {
		Optional<Album> retrievedImage = albumRepo.findById(id);
		System.out.println("img "+retrievedImage);
			if(retrievedImage.get().getType()!=null) {
				Album img = new Album(retrievedImage.get().getName(), retrievedImage.get().getType(),
						decompressBytes(retrievedImage.get().getPicByte()));
				return img;
			}
		else return	retrievedImage.get();

	}*/
	
	
	
	@RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
	public Album getImage(@PathVariable("id") Integer id) throws IOException {
		Optional<Album> retrievedImage = albumRepo.findById(id);
		System.out.println("img "+retrievedImage);
			if(retrievedImage.get().getType()!=null) {
				Album img = new Album(retrievedImage.get().getName(), retrievedImage.get().getType(),
						decompressBytes(retrievedImage.get().getPicByte()));
				return img;
			}
		else return	retrievedImage.get();

	}
	
	
	
	
	
	public static byte[] compressBytes(byte[] data) {

		Deflater deflater = new Deflater();
 
		deflater.setInput(data);

		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];

		while (!deflater.finished()) {

			int count = deflater.deflate(buffer);

			outputStream.write(buffer, 0, count);

		}

		try {

			outputStream.close();

		} catch (IOException e) {

		}

		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();

	}

// uncompress the image bytes before returning it to the angular application

	public static byte[] decompressBytes(byte[] data) {

		Inflater inflater = new Inflater();

		inflater.setInput(data);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];

		try {

			while (!inflater.finished()) {

				int count = inflater.inflate(buffer);

				outputStream.write(buffer, 0, count);

			}

			outputStream.close();

		} catch (IOException ioe) {

		} catch (DataFormatException e) {

		}

		return outputStream.toByteArray();

	}
	
	
	@RequestMapping(path = "/deleteAlbum/{id}", method = RequestMethod.DELETE)
	public void deleteByExcludedId(@PathVariable("id") Integer id) {
		albumRepo.deleteById(id);
	}
	
	
}
