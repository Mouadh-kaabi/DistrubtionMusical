package com.example.demo.excel;

import java.io.IOException;
import java.io.InputStream;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.xmlbeans.impl.soap.Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Exception.DateException;
import com.example.demo.Exception.nullException;
import com.example.demo.entities.Details;
import com.example.demo.entities.Orange;
import com.example.demo.repository.DetailRepo;
import com.example.demo.repository.OrangeRepo;




@Service
public class ExcelServiceOrange {

	
	
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

	@Autowired
	DetailRepo detailRepository;

	private static int quantite;
	private static float value;
	private static double TTC;
	private static double part_smart;
	private static double tax_telecom;
	private static double part_TTC;
	private static double HTVA;
	private static double part_artiste;
	private static String nomColonne;

	public void uploadExcelOrange(MultipartFile file)
			throws ParseException, InvalidFormatException, DateException, nullException {
		try {
			List<Details> details = excelOrangeToDetails(file.getInputStream());
			detailRepository.saveAll(details);
			System.err.println();
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

	public List<Details> excelOrangeToDetails(InputStream is)
			throws IOException, InvalidFormatException, ParseException, DateException, nullException {

		Workbook workbook = WorkbookFactory.create(is);

		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();

		List<Details> tutorials = new ArrayList<>();

		/*-------parsing of date from ---------*/
		String fdate1 = (sheet.getRow(2).getCell(0)).getStringCellValue();
		String str1 = fdate1.replaceAll("([^/0-9])", "");
		System.out.println(str1);
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(str1);
		java.sql.Date d1 = new java.sql.Date(date1.getTime());
		System.out.println(d1);

		/*-------parsing of date to ---------*/
		String tdate2 = (sheet.getRow(3).getCell(0)).getStringCellValue();
		String str2 = tdate2.replaceAll("([^/0-9])", "");
		System.out.println(str2);
		Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(str2);
		java.sql.Date d2 = new java.sql.Date(date2.getTime());
		System.out.println(date2);

		List<Details> detailss = detailRepository.getDetailsByDate1andDate2andFile(d1, d2, "Orange");
		SimpleDateFormat formaterrr = new SimpleDateFormat("MMM yyyy");

		if (detailss.isEmpty()) {
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header and the unused columns
				if (rowNumber == 0 || rowNumber == 1 || rowNumber == 2 || rowNumber == 3 || rowNumber == 4 || rowNumber == 5) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				Orange details = new Orange();
				details.setId(null);

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					details.setDate1(d1);
					details.setDate2(d2);

					switch (cellIdx) {
					case 0:
						if (!currentCell.getStringCellValue().isEmpty()) {
							details.setContent(currentCell.getStringCellValue());
							break;
						} else
							throw new nullException("le nom de chanson est doit etre non vide verifiez " + "la ligne "
									+ (currentRow.getRowNum() + 1));
					case 2:
						if (!currentCell.getStringCellValue().isEmpty()) {
							details.setCategory(currentCell.getStringCellValue());
							break;
						} else
							throw new nullException("le nom de categorie est doit etre non vide verifiez " + "la ligne "
									+ (currentRow.getRowNum() + 1));
					case 3:
						if (!currentCell.getStringCellValue().isEmpty()) {
							details.setPlateforme("Orange");
							break;
						} else
							throw new nullException("le nom du plateforme est doit etre non vide verifiez "
									+ "la ligne " + (currentRow.getRowNum() + 1));
					case 4:
						String nom_artiste;
						switch (currentCell.getStringCellValue()) {
						case "Lotfi_Bouchnak":
							nom_artiste = "Lotfi Bouchnak";
							break;

						

						case "Ines Feat Kafon":
							nom_artiste = "In-s Feat Kafon";
							break;
							
						case  "Mortadha Ftiti":
		    				nom_artiste = "MORTADHA FTITI";
		    			break;

						default:
							nom_artiste = currentCell.getStringCellValue();
							break;
						}
						if (!nom_artiste.isEmpty()) {
							details.setNamea(nom_artiste);
							System.out.println(nom_artiste);
							System.out.println(currentCell.getStringCellValue());
							break;
						}

						else
							throw new nullException("le nom artistique est doit etre non vide verifiez " + "la ligne "
									+ (currentRow.getRowNum() + 1));

					case 5:
						if (!(String.valueOf(currentCell.getNumericCellValue()).isEmpty())) {
							details.setUniteprice((float) currentCell.getNumericCellValue());
							value = (float) currentCell.getNumericCellValue();
							break;
						} else
							throw new nullException("le prix unitaire est doit etre non vide verifiez " + "la ligne "
									+ (currentRow.getRowNum() + 1));
					case 6:
						DataFormatter formatter = new DataFormatter();
						String val = "";

						switch (currentCell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							val = String.valueOf(formatter.formatCellValue(currentCell));
							System.out.println("Numeric " + val);
							break;
						case Cell.CELL_TYPE_STRING:
							val = formatter.formatCellValue(currentCell);
							System.out.println("String " + val);
							break;
						}
						if (!(val.isEmpty())) {
							System.out.println("----------->" + " " + val);
							double q = Double.parseDouble(val);

							details.setQuantite((int) q);
							quantite = (int) q;
							details.setTTC((double) (quantite * value)*0.3);
							TTC = (quantite * value *0.3);
							System.out.println(TTC);
							tax_telecom = TTC * 0.059;
							details.setTax_telecom(tax_telecom);
							
							System.out.println(tax_telecom);
							
							details.setHTVA((double) (TTC / 1.19)*0.19);
							HTVA = (TTC / 1.19)*0.19;
							System.out.println(HTVA);
							
							//totalnetredevance ttc-tax telecome 
							details.setPart_TTC((double) (TTC - (tax_telecom+HTVA)));
							part_TTC = (TTC - (tax_telecom+HTVA));
							System.out.println("part_TTC " + part_TTC);
							details.setPart_smart(part_TTC);
							
							/*details.setPart_smart((double) (part_TTC * 0.3));
							part_smart = (part_TTC * 0.3);
							System.out.println(part_TTC);*/

							

							

							

							details.setPart_artiste((double) (part_TTC / 2));
							System.out.println((part_TTC / 2));

							details.setGrossrevenu((double) 0);
							details.setNom_colonne(nomColonne);
							details.setPays("Tunisia");
							System.out.println("nom colonne ----->" + nomColonne);

							break;
						} else
							throw new nullException("le nombre d'ecoute est doit etre non vide verifiez " + "la ligne "
									+ (currentRow.getRowNum() + 1));
						
						
						
								
					default:
						details.setCdate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
						details.setFile("Orange");
						//details.setPlateforme("Orange");
						break;
					}
					
					
					//String nomartiste = details.getNamea();
					String contentname = details.getContent();
					if(contentname ==" Awled El Ghoul" )
						
					{//OGITREV FILMS
						details.setNamea("OGITREV FILMS");
					}
					cellIdx++;
				}
				tutorials.add(details);
			}

			workbook.close();

			return tutorials;
		}

		else
			throw new DateException(
					"Revenue de mois " + formaterrr.format(date1) + " existe deja !! verifiez vos date !");
	}
}
