package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Album;
import com.example.demo.entities.FTP;
import com.example.demo.repository.FtpRepo;

@Service
public class FtpDao {

	@Autowired
	FtpRepo ftpRepo ;
	
	
	public FTP addFtp(FTP alb) {
		return ftpRepo.save(alb);
	}

	public List<FTP> getAllFTP() {
		return ftpRepo.findAll();
	}

	public FTP getFTP(Integer id) {

		return ftpRepo.findById(id).get();

	}

	public FTP updateFTP(FTP ftp) {
		if (ftp.getId() != 0) {
			FTP pt = ftpRepo.findById(ftp.getId()).get();
			if (pt != null) {
				ftpRepo.save(ftp);
			}
		}
		return ftp;
	}
}
