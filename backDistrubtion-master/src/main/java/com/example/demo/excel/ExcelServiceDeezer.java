package com.example.demo.excel;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Exception.DateException;
import com.example.demo.Exception.nullException;

import com.example.demo.entities.Deezer;
import com.example.demo.entities.Details;
import com.example.demo.repository.DeezerRepo;
import com.example.demo.repository.DetailRepo;
import com.example.demo.repository.DeviseRepo;
import com.itextpdf.text.pdf.codec.Base64.InputStream;

@Service

public class ExcelServiceDeezer {

	
	

	@Autowired
	DetailRepo deezerRepo ;

	@Autowired
	DeviseRepo deviseRepository;

	private static final Map<String, String> fileExtensionMap;

	static {
		fileExtensionMap = new HashMap<String, String>();
		// excel type
		fileExtensionMap.put("xls", "application/vnd.ms-excel");
		fileExtensionMap.put("csv", "application/vnd.ms-excel");
		fileExtensionMap.put("xlt", "application/vnd.ms-excel");
		fileExtensionMap.put("xla", "application/vnd.ms-excel");
		fileExtensionMap.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		fileExtensionMap.put("xltx", "application/vnd.openxmlformats-officedocument.spreadsheetml.template");
		fileExtensionMap.put("xlsm", "application/vnd.ms-excel.sheet.macroEnabled.12");
		fileExtensionMap.put("xltm", "application/vnd.ms-excel.template.macroEnabled.12");
		fileExtensionMap.put("xlam", "application/vnd.ms-excel.addin.macroEnabled.12");
		fileExtensionMap.put("xlsb", "application/vnd.ms-excel.sheet.binary.macroEnabled.12");
	}

	private static int quantite;
	private static float value;
	private static double TTC;
	private static double part_smart;
	private static double tax_telecom;
	private static double part_TTC;
	private static double HTVA;
	private static double part_artiste;
	private static double part_fournissuer;
	private static int nbr_ecoute;
	private static double valeur_ttc;
	private static double valeur_ttc_eur;
	private static String nomColonne;
	private String nom_chonson ;
	
	public void uploadExcelDeezer(MultipartFile file)
			throws ParseException, InvalidFormatException, DateException, nullException {
		try {
			List<Details> details = excelDeezerToDetails(file.getInputStream());
			deezerRepo.saveAll(details);
			nomColonne = file.getOriginalFilename();

		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!fileExtensionMap.containsValue(file.getContentType())) {
			return false;
		}

		return true;
	}

	public List<Details> excelDeezerToDetails(java.io.InputStream inputStream)
			throws IOException, InvalidFormatException, ParseException, DateException, nullException {

		Workbook workbook = WorkbookFactory.create(inputStream);

		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();

		List<Details> tutorials = new ArrayList<>();
		SimpleDateFormat formaterrr = new SimpleDateFormat("MMM yyyy");

		int rowNumber = 0;
		while (rows.hasNext()) {

			Row currentRow = rows.next();

			// skip header and the unused columns
			if (rowNumber == 0) {
				rowNumber++;
				continue;
			}

			Details details = new Details();

			if (!(currentRow.getCell(0).getDateCellValue() == null)) {

					
				if (currentRow.getRowNum() == 1) 
				{
					//System.out.println( " currentRow------> " + currentRow.getRowNum());
					if (deezerRepo.getDetailsByDate1andFile(new java.sql.Date(currentRow.getCell(0).getDateCellValue().getTime()), "Deezer") == 0) {
						details.setDate1(new java.sql.Date(currentRow.getCell(0).getDateCellValue().getTime()));
					} else
						throw new DateException("Revenue de mois "
								+ formaterrr.format(new Date(currentRow.getCell(0).getDateCellValue().getTime()))
								+ " existe deja !! verifiez vos date !");
				}
				else {
					details.setDate1(new java.sql.Date(currentRow.getCell(0).getDateCellValue().getTime()));

				}

			} else
				throw new nullException(
						"la date debut est doit etre non vide verifiez la ligne " + (currentRow.getRowNum() + 1));

			if (!(currentRow.getCell(1).getDateCellValue() == null)) {
				details.setDate2(new java.sql.Date(currentRow.getCell(1).getDateCellValue().getTime()));
			} else
				throw new nullException(
						"la date fin est doit etre non vide verifiez " + "la ligne " + (currentRow.getRowNum() + 1));


			// System.out.println("date2------>"+details.getDate2());

			if (!(currentRow.getCell(2) == null)) {
				currentRow.getCell(2).setCellType(CellType.STRING);
				details.setIsrc(currentRow.getCell(2).getStringCellValue());
			}

			else
				throw new nullException("la valeur de Isrc est doit etre non vide verifiez " + "la ligne "
						+ (currentRow.getRowNum() + 1));
			
			if (!(currentRow.getCell(3) == null)) {
			
			currentRow.getCell(3).setCellType(CellType.STRING);
			String nom_artiste;
			switch (currentRow.getCell(3).getStringCellValue()) {
			case "Lotfi_Bouchnak":
				nom_artiste = "Lotfi Bouchnak";
				break;

			case "Saber El Rebai":
				nom_artiste = "Saber Rebai";
				break;

			case "Ines Feat Kafon":
				nom_artiste = "In-s Feat Kafon";
				break;
				
			case  "Mortadha Ftiti":
				nom_artiste = "MORTADHA FTITI";
			break;

			default:
				nom_artiste = currentRow.getCell(3).getStringCellValue();
				break;
			}
				currentRow.getCell(4).setCellType(CellType.STRING);
				if (currentRow.getCell(4) != null && deezerRepo.getFournisseurByArtisteBooleen(nom_artiste, ""+currentRow.getCell(4).getStringCellValue()) != 0) {
				//	System.out.println("Nom fournisseur " + deezerRepository.getFournisseurByArtiste(nom_artiste,currentRow.getCell(4).getStringCellValue()) +" nom artiste " + nom_artiste);
					//details.setNamef(nom_artiste);  
					details.setNamea(deezerRepo.getFournisseurByArtiste(nom_artiste, currentRow.getCell(4).getStringCellValue()));
				} else 
				details.setNamea(nom_artiste);
			}

			else
				throw new nullException("le nom d'artiste est doit etre non vide verifiez " + "la ligne "
						+ (currentRow.getRowNum() + 1));

			if (!(currentRow.getCell(4) == null)) { 
				currentRow.getCell(4).setCellType(CellType.STRING);
				String c = currentRow.getCell(4).getStringCellValue();
				details.setContent(c);
			} else
				throw new nullException("le nom de chanson est doit etre non vide verifiez " + "la ligne "
						+ (currentRow.getRowNum() + 1));

			if (!(currentRow.getCell(5) == null)) {
				currentRow.getCell(5).setCellType(CellType.STRING);
				details.setAlbum(currentRow.getCell(5).getStringCellValue());
			} else
				throw new nullException(
						"l'album est doit etre non vide verifiez " + "la ligne " + (currentRow.getRowNum() + 1));

			if (!(currentRow.getCell(6) == null)) {
				currentRow.getCell(6).setCellType(CellType.STRING);
				details.setUpc(currentRow.getCell(6).getStringCellValue());
			} else
				throw new nullException("la valeur de Upc est doit etre non vide verifiez " + "la ligne "
						+ (currentRow.getRowNum() + 1));

			if (!(currentRow.getCell(7) == null)) {
				currentRow.getCell(7).setCellType(CellType.STRING);
				details.setPays(currentRow.getCell(7).getStringCellValue());
			} else
				throw new nullException(
						"le pays est doit etre non vide verifiez " + "la ligne " + (currentRow.getRowNum() + 1));

			if (!(currentRow.getCell(8) == null)) {
				currentRow.getCell(8).setCellType(CellType.NUMERIC);
				nbr_ecoute = ((int) currentRow.getCell(8).getNumericCellValue());
				details.setQuantite(Math.abs(nbr_ecoute));
			} else
				throw new nullException("le nombre d'ecoute est doit etre non vide verifiez " + "la ligne "
						+ (currentRow.getRowNum() + 1));

			if (!(currentRow.getCell(9) == null)) {
				currentRow.getCell(9).setCellType(CellType.NUMERIC);
				valeur_ttc_eur = currentRow.getCell(9).getNumericCellValue();
				/*
				 * details.setNetrevenu(Math.abs(valeur_ttc_eur));
				 * details.setTTC(Math.abs(valeur_ttc_eur));
				 * details.setPart_artiste(Math.abs(valeur_ttc/2));
				 * details.setPart_smart(Math.abs(valeur_ttc/2));
				 */
			} else
				throw new nullException("la valeur du net Revenue est doit etre non vide verifiez " + "la ligne "
						+ (currentRow.getRowNum() + 1));

			if (!(currentRow.getCell(10) == null)) {
				currentRow.getCell(10).setCellType(CellType.STRING);
				details.setType_stream(currentRow.getCell(10).getStringCellValue());
			} else
				details.setType_stream(null);
			// details.setDevise(currentRow.getCell(23).getStringCellValue());
			
			details.setNetrevenu(Math.abs(valeur_ttc_eur));
			details.setTTC_EUR(Math.abs(valeur_ttc_eur));

			// System.out.println("date1------>"+details.getDate1());
			// System.out.println("date2------>"+details.getDate2());

			// System.out.println("getCours
			// ------>"+deviseRepository.getCourParDate(details.getDate1(),
			// details.getDate2()));
			
			  try {
	            	deviseRepository.getCourParDate(details.getDate1(), details.getDate2());
	            }
	            catch (Exception e) {
	            	throw new DateException("Existe plusieur devise pour mois "+details.getDate1());
	            }
			  
				if (deviseRepository.getCourParDate(details.getDate1(), details.getDate2()) != null) {
				details.setTTC(Math
						.abs(valeur_ttc_eur * deviseRepository.getCourParDate(details.getDate1(), details.getDate2())));// getCoursDate(details.getDate1(),
																														// details.getDate2())));
			} else
				throw new DateException("Devise du mois " + details.getDate1() + " et " + details.getDate2()
						+ " pas existe sur table devise !! Merci de completer le tableau Cours Devise !");

			// System.out.println("cours ---------> "+
			// deviseRepository.getCourParDate(details.getDate1(), details.getDate2()));
			// System.out.println("date1 ---------> "+details.getDate1());
			// System.out.println("date2 ---------> "+details.getDate2());
			// System.out.println("ttc ---------> "+details.getTTC());
			// System.out.println("ttc_eur ---------> "+details.getTTC_EUR());
				
			valeur_ttc = details.getTTC();
			details.setPart_artiste(Math.abs(valeur_ttc / 2));
			details.setPart_cp(Math.abs(valeur_ttc / 2));
			//details.setPart_fournisseur(Math.abs(valeur_ttc / 2);
			details.setPart_smart(Math.abs(valeur_ttc / 2));
			
			details.setFile("Deezer");

			details.setCdate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			details.setNom_colonne(nomColonne);
			String nomartiste = details.getNamea();
			
			if(nomartiste.equalsIgnoreCase( "Wael Jassar")     || nomartiste.equalsIgnoreCase("Samo Zaen") || nomartiste.equalsIgnoreCase("Mohamed Mounir") || nomartiste.equalsIgnoreCase("Mohamed Mounir"))
			{
				System.out.println("hellooooooooooooooo"+nomartiste);
				details.setContentProvider("Egypt linx");
				System.out.println("hellooooooooooooooo"+details.getNamef());
			}
			else if (nomartiste.equalsIgnoreCase( "Sabah")|| nomartiste.equalsIgnoreCase( "Shadia") || nomartiste.equalsIgnoreCase( "Mohamed Kandil")  || nomartiste.equalsIgnoreCase( "Mohamed Abd El Moteleb") || nomartiste.equalsIgnoreCase( "Farid El Atrash"))
				
			{
				details.setContentProvider("IVAS");
			}
			
			
			else if (nomartiste.equalsIgnoreCase( "Ali Riahi") || nomartiste.equalsIgnoreCase( "Chikh El Khattoui") || nomartiste.equalsIgnoreCase( "Elham") ||nomartiste.equalsIgnoreCase( "Ennouri") || nomartiste.equalsIgnoreCase( "Fathya Khayri") || nomartiste.equalsIgnoreCase( "Folklore Tunisien") ||
					nomartiste.equalsIgnoreCase( "Hadhra Tounsiya") ||nomartiste.equalsIgnoreCase( "Hedi Habbouba") ||nomartiste.equalsIgnoreCase( "Hedi Lajmi") ||nomartiste.equalsIgnoreCase( "Ali Riahi") ||nomartiste.equalsIgnoreCase( "Kacem Elkafi") ||nomartiste.equalsIgnoreCase( "Lotfi Hammami") ||
					nomartiste.equalsIgnoreCase( "Mansour Majdoub") ||nomartiste.equalsIgnoreCase( "Mohamed Eleuch") ||nomartiste.equalsIgnoreCase( "Mohamed Jerrari") ||nomartiste.equalsIgnoreCase( "Naama") ||nomartiste.equalsIgnoreCase( "Safoua") ||nomartiste.equalsIgnoreCase( "Safya Chamya")||nomartiste.equalsIgnoreCase( "Tahar Mansour")||nomartiste.equalsIgnoreCase( "Saliha"))
			{
				details.setContentProvider("SAMAKA");
			}
			
			else if (nomartiste.equalsIgnoreCase( "Al Afasy"))
			{
				details.setContentProvider("Al Afasy");
			}
			
			details.setPlateforme("Deezer");
	//		System.out.println("nom colonne ----->" + nomColonne);
			tutorials.add(details);
			
		}

		
		
		
		
		workbook.close();

		return tutorials;

	}

}
