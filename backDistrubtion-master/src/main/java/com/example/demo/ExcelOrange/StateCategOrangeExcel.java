package com.example.demo.ExcelOrange;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.Interface.OrangeExcel;

public class StateCategOrangeExcel {

	
private XSSFWorkbook workbook ;
	
	private XSSFSheet sheet ;
	
	private List<OrangeExcel> detailsArtistes ;
	
	public StateCategOrangeExcel(List<OrangeExcel> listDetailsArtistes)
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
		createCell(row,0,"Nom Categorie",style);
		
		createCell(row,1,"Nombre d'ecoute",style);
		createCell(row,2,"Revenu Golbal Operation",style);
		createCell(row,3,"Tax Telecome",style);
		createCell(row,4,"Total Net de redvance",style);
		createCell(row,5,"Revenu Golbal Smart",style);
	
		createCell(row,6,"Htva",style);
		createCell(row,7,"Part Artiste",style);
		
		
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
		
		for(OrangeExcel det : detailsArtistes)
		{
			Row row = sheet.createRow(rowcount++);
			int columnCount = 0 ;
			
			createCell(row,columnCount++,det.getCategory(), style);
			
			
			createCell(row,columnCount++,det.getQuantite(), style);
			createCell(row,columnCount++,det.getTtc(), style);
			createCell(row,columnCount++,det.getTax_telecom(), style);
			createCell(row,columnCount++,det.getPart_TTC(), style);
			createCell(row,columnCount++,det.getPart_smart(), style);
			
			createCell(row,columnCount++,det.getHTVA(), style);
			createCell(row,columnCount++,det.getPart_artiste(), style);
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
