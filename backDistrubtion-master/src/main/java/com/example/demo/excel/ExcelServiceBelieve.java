package com.example.demo.excel;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.example.demo.entities.Believe;
import com.example.demo.entities.Deezer;
import com.example.demo.entities.Details;
import com.example.demo.entities.Devise;
import com.example.demo.repository.BelieveRepo;
import com.example.demo.repository.DeezerRepo;
import com.example.demo.repository.DetailRepo;
import com.example.demo.repository.DeviseRepo;



@Service
public class ExcelServiceBelieve {

	 private static final Map<String, String> fileExtensionMap;

	    static {
	        fileExtensionMap = new HashMap<String, String>();
	        //excel type
			fileExtensionMap.put("csv", "text/csv");

	        fileExtensionMap.put("xls", "application/vnd.ms-excel");
	        fileExtensionMap.put("xlt", "application/vnd.ms-excel");
	        fileExtensionMap.put("xla", "application/vnd.ms-excel");
	        fileExtensionMap.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	        fileExtensionMap.put("xltx", "application/vnd.openxmlformats-officedocument.spreadsheetml.template");
	        fileExtensionMap.put("xlsm", "application/vnd.ms-excel.sheet.macroEnabled.12");
	        fileExtensionMap.put("xltm", "application/vnd.ms-excel.template.macroEnabled.12");
	        fileExtensionMap.put("xlam", "application/vnd.ms-excel.addin.macroEnabled.12");
	        fileExtensionMap.put("xlsb", "application/vnd.ms-excel.sheet.binary.macroEnabled.12");
	    }

	    @Autowired
	    DetailRepo believeRepository;

	    @Autowired
	    DeviseRepo deviseRepository;
	    
	   
	    
	    private static int quantite;
	    private static float value;
	    private static double TTC;
	    private static double part_smart;
	    private static double tax_telecom;
	    private static double part_TTC;
	    private static double HTVA;
	    private static double part_artiste;
	    private static int nbr_ecoute;
	    private static double valeur_ttc_eur;
	    private static double valeur_ttc;
	    private static String nomColonne;

	    public void uploadExcelBelieve(MultipartFile file) throws ParseException, InvalidFormatException, DateException, nullException {
	        try {
	        	System.out.println(file.getContentType()) ;
	        	file.getInputStream() ;

	        	List<Details> details = excelBelieveToDetails(file.getInputStream());
	        	believeRepository.saveAll(details);
	        	//detailRepo.saveAll(details);
	            nomColonne = file.getOriginalFilename();

	        } catch (IOException e) {
	        	e.printStackTrace();
	            throw new RuntimeException("fail to store excel data: " + e.getMessage());
	        }
	    }

	    public static boolean hasExcelFormat(MultipartFile file) {

	        if (!fileExtensionMap.containsValue(file.getContentType())) {
	            return false;
	        }

	        return true;
	    }

	    public List<Details> excelBelieveToDetails(InputStream is) throws IOException, InvalidFormatException, ParseException, DateException, nullException {

	        Workbook workbook = WorkbookFactory.create(is);
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
	            Devise devise = new Devise();

	            currentRow.getCell(5).setCellType(CellType.STRING);
	            currentRow.getCell(1).setCellType(CellType.STRING);
	            currentRow.getCell(14).setCellType(CellType.NUMERIC);
	            currentRow.getCell(20).setCellType(CellType.NUMERIC);
	            currentRow.getCell(6).setCellType(CellType.STRING);
	            currentRow.getCell(8).setCellType(CellType.STRING);
	            if(currentRow.getCell(7)!=null)
	            currentRow.getCell(7).setCellType(CellType.STRING);
	            if(currentRow.getCell(9)!=null)
	            currentRow.getCell(9).setCellType(CellType.STRING);
	            currentRow.getCell(12).setCellType(CellType.STRING);
	            currentRow.getCell(3).setCellType(CellType.STRING);
	            currentRow.getCell(13).setCellType(CellType.STRING);
	            currentRow.getCell(15).setCellType(CellType.STRING);

	            if(!currentRow.getCell(5).getStringCellValue().isEmpty()){
	                String nom_artiste;
	                switch (currentRow.getCell(5).getStringCellValue()) {
	                    case "Lotfi_Bouchnak":
	                        nom_artiste = "Lotfi Bouchnak";
	                        break;

	                    case "Saber El Rebai":
							nom_artiste = "OGITREV FILMS";
							break;

						case "Saber Rebai":
							nom_artiste = "OGITREV FILMS";
							break;


	                    case "Ines Feat Kafon":
	                        nom_artiste = "In-s Feat Kafon";
	                        break;

	                    case  "Mortadha Ftiti":
		    				nom_artiste = "MORTADHA FTITI";
		    			break;
	                    default:
	                        nom_artiste = currentRow.getCell(5).getStringCellValue();
	                        break;
	                }

	                details.setNamea(nom_artiste);

	            }

	            else throw new nullException("le nom d'artiste est doit etre non vide verifiez " +
	                    "la ligne " + (currentRow.getRowNum()+1));


	            if(!currentRow.getCell(1).getStringCellValue().isEmpty()){
	                details.setPlateforme(currentRow.getCell(1).getStringCellValue());
	            }

	            else throw new nullException("le platforme est doit etre non vide verifiez " +
	                    "la ligne " + (currentRow.getRowNum()+1));
	            
	            
	//new nbr ecoute col 14 
	            nbr_ecoute=((int) currentRow.getCell(14).getNumericCellValue()); 
	            details.setQuantite(nbr_ecoute);
	            
	//new revenu net col 20            
	            valeur_ttc_eur=currentRow.getCell(20).getNumericCellValue();

	//ne titre de la sortie col 6
	            if(currentRow.getCell(6)!=null && !currentRow.getCell(6).getStringCellValue().isEmpty()){
	               if(currentRow.getCell(7)!=null)
	            	if(currentRow.getCell(6).getStringCellValue().equals(currentRow.getCell(7).getStringCellValue())){
	                    details.setAlbum("Single");
	                }
	                else {
	                    details.setAlbum(currentRow.getCell(6).getStringCellValue());
	                }
	            }
	            else 
	            	 details.setAlbum("Single");

	//new upc col 8
	            if(!currentRow.getCell(8).getStringCellValue().isEmpty()){
	                details.setUpc(currentRow.getCell(8).getStringCellValue());
	            }
	            else throw new nullException("la valeur de Upc est doit etre non vide verifiez " +
	                    "la ligne " + (currentRow.getRowNum()+1));

	// to complete       
	            if(currentRow.getCell(7)!=null && !currentRow.getCell(7).getStringCellValue().isEmpty()){
	                String c = currentRow.getCell(7).getStringCellValue();
	                details.setContent(c);
	            }
	            else throw new nullException("Le nom de chanson doit etre non vide verifiez " +
	                    "la ligne " + (currentRow.getRowNum()+1));
	            
	//new isrc col 
	            if(currentRow.getCell(9) != null && !currentRow.getCell(9).getStringCellValue().isEmpty()){
	                details.setIsrc(currentRow.getCell(9).getStringCellValue());
	            }

	            else throw new nullException("la valeur de Isrc est doit etre non vide verifiez " +
	                    "la ligne " + (currentRow.getRowNum()+1));
	//new type sortie col 12
	            if(!currentRow.getCell(12).getStringCellValue().isEmpty()){
	                details.setType(currentRow.getCell(12).getStringCellValue());
	            }
	            else throw new nullException("le type de sortie est doit etre non vide verifiez " +
	                    "la ligne " + (currentRow.getRowNum()+1));
	            
	//new mois de reporting col 0
	            
	            if(!currentRow.getCell(0).getDateCellValue().toString().isEmpty()){

	                details.setDate1(new Date(currentRow.getCell(0).getDateCellValue().getTime()));

	                Calendar cal = Calendar.getInstance();
	                cal.setTime(currentRow.getCell(0).getDateCellValue());
	                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	                details.setDate2(new Date(cal.getTime().getTime()));

	            }
	            else throw new nullException("la date fin est doit etre non vide verifiez " +
	                    "la ligne " + (currentRow.getRowNum()+1));

	/*            if(!currentRow.getCell(19).getDateCellValue().toString().isEmpty()){
	                if(believeRepository.getDetailsByDate1andFile(new Date(currentRow.getCell(19).getDateCellValue().getTime()),"Believe")==0){
	                    details.setDate1(new Date(currentRow.getCell(19).getDateCellValue().getTime()));
	                }
	                else throw new DateException("Revenue de mois "+formaterrr.format(new Date(currentRow.getCell(19).getDateCellValue().getTime()))+" existe deja !! verifiez vos date !");
	            }

	            else throw new nullException("la date debut est doit etre non vide verifiez " +
	                    "la ligne " + (currentRow.getRowNum()+1));*/


	// pays/region new col  3
	            if(!currentRow.getCell(3).getStringCellValue().isEmpty()){
	                details.setPays(currentRow.getCell(3).getStringCellValue());
	            }
	            else throw new nullException("le pays est doit etre non vide verifiez " +
	                    "la ligne " + (currentRow.getRowNum()+1));

	//type de vente new col 13            
	            if(!currentRow.getCell(13).getStringCellValue().isEmpty()){
	                details.setType_vente(currentRow.getCell(13).getStringCellValue());
	            }

	            else throw new nullException("le type de vente doit etre non vide verifiez " +
	                    "la ligne " + (currentRow.getRowNum()+1));

	            /*if(!(currentRow.getCell(22)==null)){
	            currentRow.getCell(22).setCellType(CellType.STRING);
	            details.setType_stream(currentRow.getCell(22).getStringCellValue());
	            }

	            else throw new nullException("le type de stream doit etre non vide verifiez " +
	                    "la ligne " + (currentRow.getRowNum()+1));*/

	            
	 //new devise col 15        
	            
	            if(!currentRow.getCell(15).getStringCellValue().isEmpty()){
	                details.setDevise(currentRow.getCell(15).getStringCellValue());
	            }

	            else throw new nullException("la valeur de devise doit etre non vide verifiez " +
	                    "la ligne " + (currentRow.getRowNum()+1));

	            details.setNetrevenu(valeur_ttc_eur);
	            details.setTTC_EUR(valeur_ttc_eur);

	            //System.out.println("getCours ------>"+deviseRepository.getCourParDate(details.getDate1(), details.getDate2()));
	            try {
	            	deviseRepository.getCourParDate(details.getDate1(), details.getDate2());
	            }
	            catch (Exception e) {
	            	throw new DateException("Existe plusieur devise pour mois "+details.getDate1());
	            }
	            
	            if(deviseRepository.getCourParDate(details.getDate1(), details.getDate2())!=null){
	                details.setTTC(valeur_ttc_eur * deviseRepository.getCourParDate(details.getDate1(), details.getDate2()));//getCoursDate(details.getDate1(), details.getDate2())));
	            }
	            else throw new DateException("Devise du mois "+details.getDate1()+" et "+details.getDate2()+" est introuvable sur la table devise !! Merci de completer le tableau Cours Devise !");


	            //System.out.println( "information ligne " + details.getContent() + " / " + details.getPays() + " / "+details.getNamea() +" date1 = "+details.getDate1()+" ttc = "+details.getTTC()+" ttc_eur ="+details.getTTC_EUR());

	            valeur_ttc=details.getTTC();
	            details.setPart_artiste(valeur_ttc/2);
	            details.setPart_smart(valeur_ttc/2);

	            details.setFile("Believe");
	            details.setCdate(new Date(Calendar.getInstance().getTime().getTime()));
	 
	             details.setNom_colonne(nomColonne);
	            //System.out.println("nom colonne ----->"+nomColonne);
	            tutorials.add(details);
	            
	            System.out.println(details);
	            
	            System.out.println(tutorials);
	            
	            String nomartiste = details.getNamea();
		    	if(nomartiste =="OGITREV FILMS" )
					
				{
					details.setContent("Awled El Ghoul");
				}
	            
	          //  String nomartiste = details.getNamea();
				
		    	details.setPlateforme("Believe");
	        }
	       
	        workbook.close();

	        return tutorials;

	    }

}
