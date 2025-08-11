package com.example.demo.rest;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Interface.TTExcel;
import com.example.demo.dao.CP_TunisieDao;
import com.example.demo.entities.Artiste;
import com.example.demo.pdf.ExportStatDateTT;
import com.example.demo.repository.TunisieTelecomeRepo;

@RestController
@RequestMapping("/tt")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class CpTunisieRest {

	@Autowired
	CP_TunisieDao cp_TunisieDao ;

	@Autowired
	TunisieTelecomeRepo tunisieTelecomeRepo;
	
	@Autowired
	ExportStatDateTT exportStatDateTT ;
	
	
	@GetMapping("/allCptunis")
	public List<Object[]> getAllCpTunisie() {
		return cp_TunisieDao.getAllCpTunisie();
	}

	@GetMapping("/allCptunisArtiste")
	public List<Object[]> getAllArtisteSpTunisie() {
		return cp_TunisieDao.getAllArtisteSpTunisie();
	}
	@GetMapping("/allCptunisChanson")
	public List<Object[]> getAllChanosnArpuPlus() {
		return cp_TunisieDao.getAllChanosnArpuPlus();
	}
	@GetMapping("/allCptunisCat")
	public List<Object[]> getAllCatSubCatArpuPlus() {
		return cp_TunisieDao.getAllCatSubCatArpuPlus();
	}
	
	
	
	@GetMapping("/TotstatDateCpTuniseExcelPdf")
	public ResponseEntity<InputStreamResource> statDatePdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statCpTunisiExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateDatePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@GetMapping("/TotstatArtisteCpTunisiePdf")
	public ResponseEntity<InputStreamResource> TotstatArtisteTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statArtisteCpTunisieExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateArtistePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@GetMapping("/TotstatChansonCpTunsiPdf")
	public ResponseEntity<InputStreamResource> TotstatChansonTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statChansonCpTunisieExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateChansonPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	@GetMapping("/TotstatCatCpTunsiePdf")
	public ResponseEntity<InputStreamResource> TotstatCatTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statSouCategoryCpTunisieExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateCatPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
}
