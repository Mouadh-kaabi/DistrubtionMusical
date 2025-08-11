package com.example.demo.ExcelOrange.ExcelTT;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.Interface.TTExcel;

public class StateCategoryTotal {

	
private XSSFWorkbook workbook ;
	
	private XSSFSheet sheet ;
	
	private List<TTExcel> detailsArtistes ;
	
	public StateCategoryTotal(List<TTExcel> listDetailsArtistes)
	{
		this.detailsArtistes = listDetailsArtistes ; 
		
		workbook = new XSSFWorkbook();
	}
	
	
	private void writeHeaderLine()
	{
		sheet = workbook.createSheet("Total State");
		
		Row row = sheet.createRow(0);
		
		CellStyle style = workbook.createCellStyle();
		
		XSSFFont font = workbook.createFont();
		
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		//createCell(row,0,"Date",style);
		createCell(row,0,"Categorie",style);
		createCell(row,1,"Sub Categorie",style);
		createCell(row,2,"Sub Categorie",style);
		createCell(row,3,"Nombre d'ecoute",style);
		createCell(row,4,"Revenu Golbal Operation",style);
		createCell(row,5,"Tax Telecome",style);
		createCell(row,6,"Total Net de redvance",style);
		createCell(row,7,"Revenu Golbal Smart",style);
	
		createCell(row,8,"Revenu Global cp",style);
		createCell(row,9,"Htva/PartCp",style);
		
		
	}
	
	
	private void createCell(Row row , int columnCount,Object value ,CellStyle style)
	{
		sheet.autoSizeColumn(columnCount);
		org.apache.poi.ss.usermodel.Cell cell = row.createCell(columnCount);
		
		if (value instanceof Double)
		{
			cell.setCellValue((Double) value );
		}else if (value instanceof Boolean)
		{
			cell.setCellValue((Boolean) value );
		}else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}
	
	
	private void writeDataLine ()
	{
		int rowcount = 1 ;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		
		style.setFont(font);
		
		for(TTExcel det : detailsArtistes)
		{
			Row row = sheet.createRow(rowcount++);
			int columnCount = 0 ;
			//createCell(row,columnCount++,det.getDate1().to, style);
			createCell(row,columnCount++,det.getCategory(), style);
			createCell(row,columnCount++,det.getSubCategory(), style);
			createCell(row,columnCount++,det.getNamea(), style);
			
			createCell(row,columnCount++,det.getQuantite(), style);
			createCell(row,columnCount++,det.getTtc(), style);
			createCell(row,columnCount++,det.getTax_telecom(), style);
			createCell(row,columnCount++,det.getTotalnetderedevance(), style);
			createCell(row,columnCount++,det.getPart_smart(), style);
			
			createCell(row,columnCount++,det.getPart_cp(), style);
			createCell(row,columnCount++,det.getHTVA(), style);
			//createCell(row,columnCount++,det.getPart_artiste(), style);
		}
	}
	
	public void export(HttpServletResponse response) throws IOException
	{
		writeHeaderLine();
		writeDataLine();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		
		outputStream.close();
	}
}
