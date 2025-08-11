package com.example.demo.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ExcelOrange.ExcelTT.StateArtisteTotal;
import com.example.demo.ExcelOrange.ExcelTT.StateCategoryTotal;
import com.example.demo.ExcelOrange.ExcelTT.StateChansonTotal;
import com.example.demo.ExcelOrange.ExcelTT.StateTotalTT;
import com.example.demo.Interface.TTExcel;
import com.example.demo.dao.IvasDao;
import com.example.demo.entities.TunisieTelecom;
import com.example.demo.pdf.ExportStatDateTT;
import com.example.demo.repository.TunisieTelecomeRepo;

@RestController
@RequestMapping("/tt")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class IvasRest {

	
	@Autowired
	IvasDao ivasDao ;
	
	@Autowired
	TunisieTelecomeRepo tunisieTelecomeRepo ;
	
	
	@Autowired
	ExportStatDateTT exportStatDateTT;

	@GetMapping("/allIvas")
	public List<Object[]> getAllIvas() {
		return ivasDao.getAllIvas();
	}

	@GetMapping("/allArtisteIvas")
	public List<Object[]> getAllArtisteIvas() {
		return ivasDao.getAllArtisteIvas();
	}
	@GetMapping("/allChansonIvas")
	public List<Object[]> getAllChanosnIvas() {
		return ivasDao.getAllChanosnIvas();
	}

	@GetMapping("/allCatSubIvas")
	public List<Object[]> getAllCatSubCatIvas() {
		return ivasDao.getAllCatSubCatIvas();
	}
	
	
	
	
	@RequestMapping(path = "/StatTotalIvasExcel", method = RequestMethod.GET)

	public void StatTotalOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statIVASExcel();
		
		StateTotalTT excel = new StateTotalTT(detailsArtistes);
		excel.export(response);
	}
	
	
	@GetMapping("/StatArtisteIvasExcel")
	public void StatArtisteOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statArtisteIvasTTExcel();
		
		StateArtisteTotal excel = new StateArtisteTotal(detailsArtistes);
		excel.export(response);
	}
	
	
	@GetMapping("/StatChansonIvasExcel")
	public void StatChanosnOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statChansonIVASExcel();
		
		StateChansonTotal excel = new StateChansonTotal(detailsArtistes);
		excel.export(response);
	}
	
	
	

	@GetMapping("/StatCatSubCatIvasExcel")
	public void StatCatSubCatTTExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statSouCategoryIvasExcel();
		
		StateCategoryTotal excel = new StateCategoryTotal(detailsArtistes);
		excel.export(response);
	}
	
	
	@GetMapping("/TotstatDateIvasPdf")
	public ResponseEntity<InputStreamResource> statDatePdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statIVASExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateDatePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@GetMapping("/TotstatArtisteIvasPdf")
	public ResponseEntity<InputStreamResource> TotstatArtisteTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statArtisteIvasTTExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateArtistePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@GetMapping("/TotstatChansonIvasPdf")
	public ResponseEntity<InputStreamResource> TotstatChansonTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statChansonIVASExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateChansonPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@GetMapping("/TotstatCatIvasPdf")
	public ResponseEntity<InputStreamResource> TotstatCatTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statSouCategoryIvasExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateCatPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
}
