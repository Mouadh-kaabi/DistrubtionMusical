package com.example.demo.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.BelieveExcel.StateArtisteExcelBelieve;
import com.example.demo.BelieveExcel.StateChansonExcelBelieve;
import com.example.demo.BelieveExcel.StateChansonExcelBelieveById;
import com.example.demo.BelieveExcel.StateExcelBelieve;
import com.example.demo.BelieveExcel.StateExcelBelieveById;
import com.example.demo.BelieveExcel.StateFournisseurExcelBelieve;
import com.example.demo.BelieveExcel.StateRevenuExcelBelieve;
import com.example.demo.BelieveExcel.StateRevenuExcelBelieveById;
import com.example.demo.ExcelOrange.StateExcelOrangeId;
import com.example.demo.ExcelOrange.StateExcelRevenuOrange;
import com.example.demo.ExcelOrange.StateExcelRevenuOrangeId;
import com.example.demo.Exception.DateException;
import com.example.demo.Exception.nullException;
import com.example.demo.Interface.BelieveExcel;
import com.example.demo.Interface.DeezerExcel;
import com.example.demo.Interface.OrangeExcel;
import com.example.demo.dao.BelieveDao;
import com.example.demo.deezerExcel.StateArtisteExcelDeezer;
import com.example.demo.deezerExcel.StateExcelDeezer;
import com.example.demo.deezerExcel.StateFournisseurExcelDeezer;
import com.example.demo.excel.ExcelServiceBelieve;
import com.example.demo.excel.ExcelServiceTT;
import com.example.demo.payload.response.ResponseMessage;
import com.example.demo.pdf.ExportStatDateBelieve;
import com.example.demo.repository.BelieveRepo;

@RestController
@RequestMapping("/believe")
@CrossOrigin(origins = "http://192.168.1.111:4200")
public class BelieveRest {

	@Autowired
	ExcelServiceBelieve excelServiceBelieve ;
	
	@Autowired
	BelieveDao believeDao ; 
	
	
	@Autowired
	BelieveRepo believeRepo ;
	
	@Autowired
	ExportStatDateBelieve exportStatDateBelieve ;
	
	
	@GetMapping("/all")
	public List<Object[]> getAllDateDeezer() {
		return believeDao.getAllDateDeezer();
	}

	
	
	
	@GetMapping("/allArtiste")
	public List<Object[]> getAllArtistBelieve() {
		return believeDao.getAllArtistBelieve();
	}

	@GetMapping("/allChanson")
	public List<Object[]> getAllChansonBelieve() {
		return believeDao.getAllChansonBelieve();
	}

	@GetMapping("/allPaltforme")
	public List<Object[]> getAllPlatformeBelieve() {
		return believeDao.getAllPlatformeBelieve();
	}
	

	@PostMapping("/uploadExcelBelieve")
	public ResponseEntity<ResponseMessage> uploadFileTT(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (ExcelServiceTT.hasExcelFormat(file)) {
			try {
				excelServiceBelieve.uploadExcelBelieve(file);
				
				message = "l'importation et terminé avec succes: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (DateException | ParseException | InvalidFormatException | nullException e) {
				message = "echec d'importation: " + e.getMessage();
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}
		message = "echec d'importation: le fichier n'est pas de type excel !";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}
	
	
	
	@RequestMapping(path = "/StatTotalBelieveExcel", method = RequestMethod.GET)

	public void StatTotalDeezerExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<BelieveExcel> detailsArtistes =believeRepo.statDateBelieveExcel();
		
		StateExcelBelieve excel = new StateExcelBelieve(detailsArtistes);
		excel.export(response);
	}
	
	@RequestMapping(path = "/StatTotalBelieveExcel/excel/{id}", method = RequestMethod.GET)

	public void StatTotalOrangeExcel(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<BelieveExcel> detailsArtistes =believeRepo.statDateByIdExcel(id);
		
		StateExcelBelieveById excel = new StateExcelBelieveById(detailsArtistes);
		excel.export(response);
	}
	
	
	@RequestMapping(path = "/StatArtisteBelieveExcel", method = RequestMethod.GET)

	public void StatArtisteDeezerExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<BelieveExcel> detailsArtistes =believeRepo.statArtisteBeeliveExcel();
		
		StateArtisteExcelBelieve excel = new StateArtisteExcelBelieve(detailsArtistes);
		excel.export(response);
	}
	
	

	
	//statChansonBelieveExcel
	
	@RequestMapping(path = "/StatChansonBelieveExcel", method = RequestMethod.GET)

	public void StatADeezerExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<BelieveExcel> detailsArtistes =believeRepo.statChansonBelieveExcel();
		
		StateChansonExcelBelieve excel = new StateChansonExcelBelieve(detailsArtistes);
		excel.export(response);
	}
	
	
	@RequestMapping(path = "/StatChansonBelieveExcel/excel/{id}", method = RequestMethod.GET)

	public void StatArtisteDeezerExcelById(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<BelieveExcel> detailsArtistes =believeRepo.statChansonByIdExcel(id);
		
		StateChansonExcelBelieveById excel = new StateChansonExcelBelieveById(detailsArtistes);
		excel.export(response);
	}
	
	@RequestMapping(path = "/StatFournisseurDeezerExcel", method = RequestMethod.GET)

	public void StatFournisseurDeezerExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<BelieveExcel> detailsArtistes =believeRepo.statPlateformeBelieveExcel();
		
		StateFournisseurExcelBelieve excel = new StateFournisseurExcelBelieve(detailsArtistes);
		excel.export(response);
	}
	
	
	@RequestMapping(path = "/StatFournisseurDeezerExcel/excel/{id}", method = RequestMethod.GET)

	public void StatFournisseurDeezerExcelById(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<BelieveExcel> detailsArtistes =believeRepo.statPlateformeByIdExcel(id);
		
		StateChansonExcelBelieveById excel = new StateChansonExcelBelieveById(detailsArtistes);
		excel.export(response);
	}
	
	@GetMapping("/TotstatDateBelievePdf")
	public ResponseEntity<InputStreamResource> statDatePdf()
	{
		List<BelieveExcel>  deatils =  believeRepo.statDateBelieveExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateBelieve.StateDatePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	//statDateByIdExcel
	@RequestMapping(path = "/statDateBeleiveByIdExcel/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> statDateBeleiveByIdExcel(@PathVariable("id") Integer id)
	{
	
		List<BelieveExcel>  deatils =  believeRepo.statDateByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateBelieve.StateDatePdfReportByIdPdf(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}	
	
	
	
/*	@RequestMapping(path = "/TotstatDateBelievePdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> statPaysTotalPdfByid(@PathVariable("id") Integer id)
	{
	
		List<BelieveExcel>  deatils =  believeRepo.statDateByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateBelieve.StateDatePdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}	*/
	
	
	@GetMapping("/TotstatArtisteBelievePdf")
	public ResponseEntity<InputStreamResource> TotstatArtisteDeezerPdf()
	{
		List<BelieveExcel>  deatils =  believeRepo.statArtisteBeeliveExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateBelieve.StateArtistePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@GetMapping("/TotstatChansonBelievePdf")
	public ResponseEntity<InputStreamResource> TotstatChansonDeezerPdf()
	{
		List<BelieveExcel>  deatils =  believeRepo.statChansonBelieveExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateBelieve.StateChansonPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	//statChansonByIdExcel
	@RequestMapping(path = "/TotstatChansonBelievePdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> statPaysTotalPdfByid(@PathVariable("id") Integer id)
	{
	
		List<BelieveExcel>  deatils =  believeRepo.statChansonByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateBelieve.StateChansonPdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	
	@GetMapping("/TotstatPlatformeBelievePdf")
	public ResponseEntity<InputStreamResource> TotstatPlatformeBelievePdf()
	{
		List<BelieveExcel>  deatils =  believeRepo.statPlateformeBelieveExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateBelieve.StatePlatformePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	
	
	
	//statPlateformeByIdExcel
	
	@RequestMapping(path = "/TotstatPlatformeBelievePdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> TotstatPlatformeBelievePdfByid(@PathVariable("id") Integer id)
	{
	
		List<BelieveExcel>  deatils =  believeRepo.statPlateformeByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateBelieve.StatePlatformePdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	
	@RequestMapping(path = "/statDateBelieve/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateById(@PathVariable("id") Integer id) {
		return believeRepo.statDateById(id);
	}
	
	@RequestMapping(path = "/statDateBelievePlatforme/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateByIdPlatforme(@PathVariable("id") Integer id) {
		return believeRepo.statDateByIdPlatforme(id);
	}
	
	
	@RequestMapping(path = "/statChansonBelieve/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statChansonById(@PathVariable("id") Integer id) {
		return believeRepo.statChansonById(id);
	}
	
	@RequestMapping(path = "/statArtisteBelieve/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statArtisteById(@PathVariable("id") Integer id) {
		return believeRepo.statArtisteById(id);
	}
	
	
	@RequestMapping(path = "/statPlateformeBelieve/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statPlateformeById(@PathVariable("id") Integer id) {
		return believeRepo.statPlateformeById(id);
	}
	
	
	
	
	
	@RequestMapping(path = "/HistRevenuBelieve", method = RequestMethod.GET)
	public List<Object[]> statRevenu() {
		return believeRepo.statRevenuBeleive();
	}
	
	@RequestMapping(path = "/HistRevenuBelieve/by-id/{id}", method = RequestMethod.GET)
	public List<Object[]> HistRevenu(@PathVariable("id") Integer id) {
		return believeRepo.HistRevenuBelieve(id);
	}
	
	
	
	@GetMapping("/TotRevenuPdf")
	public ResponseEntity<InputStreamResource> statRevenuExcel()
	{
		List<BelieveExcel>  deatils =  believeRepo.statRevenuBeleiveExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais =exportStatDateBelieve.StateRevenuPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	
	
	//HistRevenuOrangeExcel
	@RequestMapping(path = "/TotRevenuPdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> HistRevenuOrangeExcelByid(@PathVariable("id") Integer id)
	{
	
		List<BelieveExcel>  deatils =  believeRepo.HistRevenuBelieveExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateBelieve.StateRevenuPdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	
	
	@RequestMapping(path = "/HistRevenuOrangeExcel/excel/{id}", method = RequestMethod.GET)

	public void HistRevenuOrangeExcel(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<BelieveExcel> detailsArtistes =believeRepo.HistRevenuBelieveExcel(id);
		
		StateRevenuExcelBelieveById excel = new StateRevenuExcelBelieveById(detailsArtistes);
		excel.export(response);
	}
	
	//statRevenuExcel
	
	@GetMapping("/statRevenuExcel")
	public void statRevenuExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<BelieveExcel> detailsArtistes =believeRepo.statRevenuBeleiveExcel();
		
		StateRevenuExcelBelieve excel = new StateRevenuExcelBelieve(detailsArtistes);
		excel.export(response);
	}
	
	
	@RequestMapping(path = "/statPaysBeleieve", method = RequestMethod.GET)
	public List<Object[]> statPaysDeezer() {
		return believeRepo.statPaysBelieve();
	}
	
	
	@RequestMapping(path = "/statPaysBeleieve/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statPaysDeezer(@PathVariable("id") Integer id) {
		return believeRepo.statPayBeleive(id);
	}
	
	
	@RequestMapping(path = "/deleteFileByDate/{date1}/{date2}/{file}", method = RequestMethod.DELETE)
	public void deleteByFichier(@PathVariable java.sql.Date date1, @PathVariable java.sql.Date date2,@PathVariable String file) {
		believeRepo.deleteByDateFichier(date1,date2,file);
	}
	
	
	@RequestMapping(path = "/statDateLabelBelieve/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateLabelById(@PathVariable("id") Integer id) {
		return believeRepo.statDateLabelById(id);
	}
	
	@RequestMapping(path = "/statChansonBelieveLabel/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statChansonByIdLabel(@PathVariable("id") Integer id) {
		return believeRepo.statChansonByIdLabel(id);
	}
	
	@RequestMapping(path = "/statArtisteBelieveLabel/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statArtisteByIdLabel(@PathVariable("id") Integer id) {
		return believeRepo.statArtisteByIdLabel(id);
	}
	
	@RequestMapping(path = "/statPlateformeBelieveLabel/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statPlateformeByIdLabel(@PathVariable("id") Integer id) {
		return believeRepo.statPlateformeByIdLabel(id);
	}
	
	
	@RequestMapping(path = "/topArtisteBelive", method = RequestMethod.GET)
	public List<Object[]> topArtiste() {
		return believeRepo.topArtisteBelieve();
	}
	
	
	@RequestMapping(path = "/topChansonBelive", method = RequestMethod.GET)
	public List<Object[]> listChanson() {
		return believeRepo.topChansonBelieve();
	}
	
	
	@RequestMapping(path = "/topDateBelieve", method = RequestMethod.GET)
	public List<Object[]> topDate() {
		return believeRepo.topDateBelieve();
	}
	
	
	@RequestMapping(path = "/ChartsChansBelieve", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsChans() {
		return believeRepo.ChartsChansBelieve();
	}
	
	
	@RequestMapping(path = "/ChartsPartsRevenuBelieve/by-userId/{id}", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsPartsRevenuById(@PathVariable("id") Integer id) {
		return believeRepo.ChartsPartsRevenuBeleive(id);
	}
	
	
	
	@RequestMapping(path = "/ChartsDateBelieve", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsDate() {
		return believeRepo.ChartsDateBelieve();
	}
}
