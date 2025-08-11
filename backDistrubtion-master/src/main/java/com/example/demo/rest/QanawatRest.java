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
import com.example.demo.dao.QanawatDao;
import com.example.demo.pdf.ExportStatDateTT;
import com.example.demo.repository.TunisieTelecomeRepo;

@RestController
@RequestMapping("/tt")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class QanawatRest {

	
	@Autowired
	QanawatDao qanawatDao ;
	
	@Autowired
	TunisieTelecomeRepo tunisieTelecomeRepo;
	
	@Autowired 
	ExportStatDateTT exportStatDateTT ;
	
	@RequestMapping("/allqanwat")
	public List<Object[]> getAllQanwat() {
		return qanawatDao.getAllQanwat();
	}
	
	@RequestMapping("/allArtistequanwat")
	public List<Object[]> getAllArtisteQuanwat() {
		return qanawatDao.getAllArtisteQuanwat();
	}
	@RequestMapping("/allChansonquanwat")
	public List<Object[]> getAllChansonQuanwat() {
		return qanawatDao.getAllChansonQuanwat();
	}
	@RequestMapping("/allSouCategquanwat")
	public List<Object[]> getAllSouCategoryQuanwat() {
		return qanawatDao.getAllSouCategoryQuanwat();
	}
	
	
	
	
	@RequestMapping(path = "/StatTotalQanawatExcel", method = RequestMethod.GET)

	public void StatTotalOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statquanwatTotalExcel();
		
		StateTotalTT excel = new StateTotalTT(detailsArtistes);
		excel.export(response);
	}
	
	
	@GetMapping("/StatArtisteQanwatExcel")
	public void StatArtisteOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statArtisteQuanwatExcel();
		
		StateArtisteTotal excel = new StateArtisteTotal(detailsArtistes);
		excel.export(response);
	}
	
	
	@GetMapping("/StatChansonQanwatExcel")
	public void StatChanosnOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statChansonQuanwatExcel();
		
		StateChansonTotal excel = new StateChansonTotal(detailsArtistes);
		excel.export(response);
	}

	
	@GetMapping("/StatCatSubCatQanawatExcel")
	public void StatCatSubCatTTExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<TTExcel> detailsArtistes =tunisieTelecomeRepo.statSouCategoryQuanwatExcel();
		
		StateCategoryTotal excel = new StateCategoryTotal(detailsArtistes);
		excel.export(response);
	}
	
	
	@GetMapping("/TotstatDateQanwatPdf")
	public ResponseEntity<InputStreamResource> statDatePdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statquanwatTotalExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateDatePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@GetMapping("/TotstatArtisteQanwatPdf")
	public ResponseEntity<InputStreamResource> TotstatArtisteTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statArtisteQuanwatExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateArtistePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	@GetMapping("/TotstatChansonQanwatPdf")
	public ResponseEntity<InputStreamResource> TotstatChansonTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statChansonQuanwatExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateChansonPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@GetMapping("/TotstatCatQanwatPdf")
	public ResponseEntity<InputStreamResource> TotstatCatTTPdf()
	{
		List<TTExcel>  deatils =  tunisieTelecomeRepo.statSouCategoryQuanwatExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateTT.StateCatPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
}
