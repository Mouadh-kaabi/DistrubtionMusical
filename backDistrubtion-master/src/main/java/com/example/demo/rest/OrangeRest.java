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
import javax.transaction.Transactional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

import com.example.demo.ExcelOrange.StateArtisteOrange;
import com.example.demo.ExcelOrange.StateArtisteOrangeId;
import com.example.demo.ExcelOrange.StateChansonExcel;
import com.example.demo.ExcelOrange.StateChansonExcelById;
import com.example.demo.ExcelOrange.StateExcelOrange;
import com.example.demo.ExcelOrange.StateExcelOrangeId;
import com.example.demo.ExcelOrange.StateExcelRevenuOrange;
import com.example.demo.ExcelOrange.StateExcelRevenuOrangeId;
import com.example.demo.ExcelOrange.StatePlatformeOrangExcel;
import com.example.demo.ExcelOrange.StatePlatformeOrangExcelById;
import com.example.demo.Exception.DateException;
import com.example.demo.Exception.nullException;
import com.example.demo.Interface.OrangeExcel;
import com.example.demo.dao.OrangeDao;
import com.example.demo.excel.ExcelServiceOrange;
import com.example.demo.excel.ExcelServiceTT;
import com.example.demo.payload.response.ResponseMessage;
import com.example.demo.pdf.ExportStatDateOrange;
import com.example.demo.repository.OrangeRepo;




@RestController
@RequestMapping("/orange")

@CrossOrigin(origins = "http://192.168.1.71:4200")
public class OrangeRest {

	@Autowired
	OrangeDao orangeDao ;
	
	@Autowired
	OrangeRepo orangeRepo ;

	
	@Autowired
	ExportStatDateOrange exportStatDateOrange ; 
	
	
	
	@Autowired
	ExcelServiceOrange excelServiceOrange ;
	
	@PostMapping("/uploadExcelOrange")
	public ResponseEntity<ResponseMessage> uploadFileTT(@RequestParam("file") MultipartFile file) {
		String message = "";
		
		if (ExcelServiceTT.hasExcelFormat(file)) {
			try {
				excelServiceOrange.uploadExcelOrange(file);
				
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
	
	@GetMapping("/statDateOrange")
	public List<Object[]> statDate() {
		return orangeDao.statDate();
	}
	
	
	@RequestMapping(path = "/statDate/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateById(@PathVariable("id") Integer id) {
		return orangeRepo.statDateById(id);
	}

	
	
	@GetMapping("/statArtisteOrange")
	public List<Object[]> statArtisteOrange() {
		return orangeDao.statArtisteOrange();
	}
	
	//statArtisteOrangeUnit
	/*@GetMapping("/statArtisteOrangeUnit")
	public List<Object[]> statArtisteOrangeUnit() {
		return orangeDao.statArtisteOrangeUnit();
	}*/
	@GetMapping("/statArtisteOrangeUnit")
	public List<Object[]> statArtisteOrangeUnit(@RequestParam(required = false) String namea) {
	    return orangeDao.statArtisteOrangeUnit(namea);
	}
	//statArtisteTTUnit
	@GetMapping("/statArtisteTTUnit")
	public List<Object[]> StatArtisteTTUnit() {
		return orangeDao.statArtisteTTUnit();
	}
	@RequestMapping(path = "/statArtisteOrange/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statArtisteById(@PathVariable("id") Integer id) {
		return orangeRepo.statArtisteOrangeById(id);
	}
	
	@GetMapping("/statChasnonOrange")
	public List<Object[]> statChansonOrange() {
		return orangeDao.statChansonOrange();
	}
	
	@RequestMapping(path = "/statChansonOrange/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statChansonById(@PathVariable("id") Integer id) {
		return orangeRepo.statChansonOrangeById(id);
	}
	
	@GetMapping("/statPlatformeOrange")
	public List<Object[]> statPlatformeOrange() {
		return orangeDao.statPlatformeOrange();
	}
	
	@RequestMapping(path = "/statPlateformeOrange/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statPlateformeById(@PathVariable("id") Integer id) {
		return orangeRepo.statPlateformeOrangeById(id);
	}
	@GetMapping("/statCategoryOrange")
	public List<Object[]> statCategoryOrange() {
		return orangeDao.statCategoryOrange();
	}
	@RequestMapping(path = "/statCategorieOrange/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statCategorieById(@PathVariable("id") Integer id) {
		return orangeRepo.statCategoryOrangeById(id);
	}
/*	@RequestMapping(path = "/statDate", method = RequestMethod.GET)
	public List<Object[]> statDate() {
		return DetailRepository.statDate();
	}*/
	
	
	
	
	
	
	
	
	@RequestMapping(path = "/StatTotalOrangeExcel/excel", method = RequestMethod.GET)

	public void StatTotalOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<OrangeExcel> detailsArtistes =orangeRepo.statDateOrangeExcel();
		
		StateExcelOrange excel = new StateExcelOrange(detailsArtistes);
		excel.export(response);
	}
	
	
	@RequestMapping(path = "/StatTotalExcel/excel/{id}", method = RequestMethod.GET)

	public void StatTotalOrangeExcel(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<OrangeExcel> detailsArtistes =orangeRepo.statDateByIdExcel(id);
		
		StateExcelOrangeId excel = new StateExcelOrangeId(detailsArtistes);
		excel.export(response);
	}
	
	
	//@RequestMapping(path = "/StatArtisteOrangeExcel/excel", method = RequestMethod.GET)
	
	@GetMapping("/StatArtisteOrangeExcel")
	public void StatArtisteOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<OrangeExcel> detailsArtistes =orangeRepo.statArtisteOrangeExcel();
		
		StateArtisteOrange excel = new StateArtisteOrange(detailsArtistes);
		excel.export(response);
	}
	
	
	@RequestMapping(path = "/StatArtisteOrangeExcel/excel/{id}", method = RequestMethod.GET)

	public void StatArtisteOrangeExcelById(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<OrangeExcel> detailsArtistes =orangeRepo.statArtisteOrangeByIdExcel(id);
		
		StateArtisteOrangeId excel = new StateArtisteOrangeId(detailsArtistes);
		excel.export(response);
	}
	
	@GetMapping("/StateChansonOrangeExcel")
	public void StateChansonOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<OrangeExcel> detailsArtistes =orangeRepo.statChansonOrangeExcel();
		
		StateChansonExcel excel = new StateChansonExcel(detailsArtistes);
		excel.export(response);
	}
	
	@RequestMapping(path = "/StateChansonOrangeExcel/excel/{id}", method = RequestMethod.GET)
	public void StateChansonOrangeExcelById(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<OrangeExcel> detailsArtistes =orangeRepo.statChansonOrangeByIdExcel(id);
		
		StateChansonExcelById excel = new StateChansonExcelById(detailsArtistes);
		excel.export(response);
	}
	
	
	@GetMapping("/StatePlatformeOrangeExcel")
	public void StatePlatformeOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<OrangeExcel> detailsArtistes =orangeRepo.statPlateformeOrangeExcel();
		
		StatePlatformeOrangExcel excel = new StatePlatformeOrangExcel(detailsArtistes);
		excel.export(response);
	}
	
	
	@RequestMapping(path = "/StatePlatformeOrangeExcel/excel/{id}", method = RequestMethod.GET)
	public void StatePlatformeOrangeExcelById(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<OrangeExcel> detailsArtistes =orangeRepo.statPlateformeOrangeByIdExcel(id);
		
		StatePlatformeOrangExcelById excel = new StatePlatformeOrangExcelById(detailsArtistes);
		excel.export(response);
	}
	
	//statcategoryOrangeExcel
	
	@GetMapping("/StateCategOrangeExcel")
	public void StateCategOrangeExcel(HttpServletResponse response) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<OrangeExcel> detailsArtistes =orangeRepo.statcategoryOrangeExcel();
		
		com.example.demo.ExcelOrange.StateCategOrangeExcel excel = new com.example.demo.ExcelOrange.StateCategOrangeExcel(detailsArtistes);
		excel.export(response);
	}
	
	@RequestMapping(path = "/StateCategOrangeExcel/excel/{id}", method = RequestMethod.GET)
	public void StateCategOrangeExcelById(HttpServletResponse response,@PathVariable("id") Integer id ) throws IOException
	{
		System.out.println("Export to excel ...........");
		response.setContentType("application/octet-stream");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		
		String headerKey ="Content-disposit";
		String headerValue = "attachement; filename=totalStateArtiste _"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		List<OrangeExcel> detailsArtistes =orangeRepo.statCategoryOrangeByIdExcel(id);
		
		com.example.demo.ExcelOrange.StateCategOrangeExcelById excel = new com.example.demo.ExcelOrange.StateCategOrangeExcelById(detailsArtistes);
		excel.export(response);
	}
	
	
	
	
	//@RequestMapping(path = "/TotstatDatePdf", method = RequestMethod.GET)
	
	@GetMapping("/TotstatDateOrangePdf")
	public ResponseEntity<InputStreamResource> statDatePdf()
	{
		List<OrangeExcel>  deatils =  orangeRepo.statDateOrangeExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateOrange.StateDatePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	@RequestMapping(path = "/TotstatDateOrangePdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> statPaysTotalPdfByid(@PathVariable("id") Integer id)
	{
	
		List<OrangeExcel>  deatils =  orangeRepo.statDateByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateOrange.StateDatePdfReportBYid(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}	
	
	@GetMapping("/ArtisteDateOrangePdf")
	public ResponseEntity<InputStreamResource> ArtisteDateOrangePdf()
	{
		List<OrangeExcel>  deatils =  orangeRepo.statArtisteOrangeExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateOrange.StateArtistePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	
	@RequestMapping(path = "/ArtisteDateOrangePdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> ArtisteDateOrangePdfByid(@PathVariable("id") Integer id)
	{
	
		List<OrangeExcel>  deatils =  orangeRepo.statArtisteOrangeByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateOrange.StateArtistePdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}	
	
	
	@GetMapping("/ChansonDateOrangePdf")
	public ResponseEntity<InputStreamResource> ChansonDateOrangePdf()
	{
		List<OrangeExcel>  deatils =  orangeRepo.statChansonOrangeExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateOrange.StateChansonPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	@RequestMapping(path = "/ChansonDateOrangePdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> ChansonDateOrangePdfByid(@PathVariable("id") Integer id)
	{
	
		List<OrangeExcel>  deatils =  orangeRepo.statChansonOrangeByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateOrange.StateChansonPdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	
	@GetMapping("/PlatformeDateOrangePdf")
	public ResponseEntity<InputStreamResource> PlatformeDateOrangePdf()
	{
		List<OrangeExcel>  deatils =  orangeRepo.statPlateformeOrangeExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateOrange.StatePlatformePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	

	
	@RequestMapping(path = "/PlatformeDateOrangePdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> PlatformeDateOrangePdfByid(@PathVariable("id") Integer id)
	{
	
		List<OrangeExcel>  deatils =  orangeRepo.statPlateformeOrangeByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateOrange.StatePlatformePdfReportById(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	
	
	
	@GetMapping("/CategDateOrangePdf")
	public ResponseEntity<InputStreamResource> CategDateOrangePdf()
	{
		List<OrangeExcel>  deatils =  orangeRepo.statcategoryOrangeExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateOrange.StateCategoriePdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	
	//statCategoryOrangeByIdExcel
	
	
	@RequestMapping(path = "/CategDateOrangePdf/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> CategDateOrangePdfByid(@PathVariable("id") Integer id)
	{
	
		List<OrangeExcel>  deatils =  orangeRepo.statCategoryOrangeByIdExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateOrange.StateCategoriePdfReportByID(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	
	}
	
	
	

	
	
	@RequestMapping(path = "/HistRevenuOrange", method = RequestMethod.GET)
	public List<Object[]> statRevenu() {
		return orangeRepo.statRevenu();
	}
	
	@RequestMapping(path = "/HistRevenuOrange/by-id/{id}", method = RequestMethod.GET)
	public List<Object[]> HistRevenu(@PathVariable("id") Integer id) {
		return orangeRepo.HistRevenuOrange(id);
	}
	
	//statRevenuExcel
	@GetMapping("/TotRevenuPdf")
	public ResponseEntity<InputStreamResource> statRevenuExcel()
	{
		List<OrangeExcel>  deatils =  orangeRepo.statRevenuExcel();
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateOrange.StateRevenuPdfReport(deatils);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=TotalState.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));		
	}
	
	
	
	
	//HistRevenuOrangeExcel
	@RequestMapping(path = "/HistRevenuOrangeExcel/by-userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> HistRevenuOrangeExcelByid(@PathVariable("id") Integer id)
	{
	
		List<OrangeExcel>  deatils =  orangeRepo.HistRevenuOrangeExcel(id);
		
		//List<details> listDetails = (List) details ;
		ByteArrayInputStream bais = exportStatDateOrange.StateRevenuPdfReportById(deatils);
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
		
		
		List<OrangeExcel> detailsArtistes =orangeRepo.HistRevenuOrangeExcel(id);
		
		StateExcelRevenuOrangeId excel = new StateExcelRevenuOrangeId(detailsArtistes);
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
		
		List<OrangeExcel> detailsArtistes =orangeRepo.statRevenuExcel();
		
		StateExcelRevenuOrange excel = new StateExcelRevenuOrange(detailsArtistes);
		excel.export(response);
	}
	
	
	@RequestMapping(path = "/statDateArtiste/by-id/{id}", method = RequestMethod.GET)
	public List<Object[]> statArtisteByName(@PathVariable("id") String id) {
		return orangeRepo.statDateArtisteByname(id);
	}
	
	
	/*@RequestMapping(path = "/deleteFileByDateOrange/{date1}/{file}", method = RequestMethod.DELETE)
	public void deleteByFichier(@PathVariable java.sql.Date date1,@PathVariable String file) {
		orangeRepo.deleteByDateFichierOrange(date1,file);
	}*/
	
	
	@RequestMapping(path = "/deleteFileByDate/{date1}/{date2}/{file}", method = RequestMethod.DELETE)
	public void deleteByFichier(@PathVariable java.sql.Date date1, @PathVariable java.sql.Date date2,@PathVariable String file) {
		orangeRepo.deleteByDateFichier(date1,date2,file);
	}

	
	@RequestMapping(path = "/statDateChanson/by-nom/{nom}", method = RequestMethod.GET)
	public List<Object[]> statChansonByNom(@PathVariable("nom") String nom) {
		return orangeRepo.statDateChansonByNom(nom);
	}

	
	/*@RequestMapping(path = "/statDateLabel/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateLabelById(@PathVariable("id") Integer id) {
		return orangeRepo.statDateById(id);
	}*/
	
	//statDateLabelById

	@RequestMapping(path = "/statDateLabel/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statDateLabelById(@PathVariable("id") Integer id) {
		return orangeRepo.statDateLabelById(id);
	}
	
	
	@RequestMapping(path = "/statChansonLabel/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statChansonLabelById(@PathVariable("id") Integer id) {
		return orangeRepo.statChansonOrangeByIdLabel(id);
	}
	
	
	@RequestMapping(path = "/statArtisteLabel/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statArtisteByIdLabel(@PathVariable("id") Integer id) {
		return orangeRepo.statArtisteOrangeById(id);
	}
	
	@RequestMapping(path = "/statPlateformeOrangeLabel/by-userId/{id}", method = RequestMethod.GET)
	public List<Object[]> statPlateformeByIdLabel(@PathVariable("id") Integer id) {
		return orangeRepo.statPlateformeOrangeByIdLabel(id);
	}
	
	
	@RequestMapping(path = "/topArtisteOrange", method = RequestMethod.GET)
	public List<Object[]> topArtiste() {
		return orangeRepo.topArtisteOrange();
	}
	
	
	@RequestMapping(path = "/topChansonOrange", method = RequestMethod.GET)
	public List<Object[]> listChanson() {
		return orangeRepo.topChansonOrange();
	}
	
	
	@RequestMapping(path = "/topDateOrange", method = RequestMethod.GET)
	public List<Object[]> topDate() {
		return orangeRepo.topDateOrange();
	}
	
	
	@RequestMapping(path = "/ChartsChansOrange", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsChans() {
		return orangeRepo.ChartsChansOrange();
	}
	
	
	@RequestMapping(path = "/ChartsPartsRevenuOrange/by-userId/{id}", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsPartsRevenuById(@PathVariable("id") Integer id) {
		return orangeRepo.ChartsPartsRevenuOrange(id);
	}
	
	@RequestMapping(path = "/ChartsPaysOrange", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsPays() {
		return orangeRepo.ChartsPaysOrange();
	}
	
	
	
	@RequestMapping(path = "/ChartsDateOrange", method = RequestMethod.GET)
	public List<Map<String, Object>> ChartsDate() {
		return orangeRepo.ChartsDateOrange();
	}
	
	
	
	
	
	
	
}
