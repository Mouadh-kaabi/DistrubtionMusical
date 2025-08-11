package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Album;
import com.example.demo.repository.AlbumRepo;

@Service
public class AlbumDao {

	@Autowired
	AlbumRepo albumRepo;

	public Album addAlbum(Album alb) {
		return albumRepo.save(alb);
	}

	public List<Album> getAllAlbum() {
		return albumRepo.findAll();
	}

	public Album getAlbum(Integer id) {

		return albumRepo.findById(id).get();

	}

	public Album updateAlbum(Album album) {
		if (album.getId() != 0) {
			Album pt = albumRepo.findById(album.getId()).get();
			if (pt != null) {
				albumRepo.save(album);
			}
		}
		return album;
	}
}
