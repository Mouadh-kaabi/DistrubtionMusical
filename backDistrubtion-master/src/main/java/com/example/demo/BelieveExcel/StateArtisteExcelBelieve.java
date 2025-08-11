package com.example.demo.BelieveExcel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.Interface.BelieveExcel;
import com.example.demo.Interface.DeezerExcel;

public class StateArtisteExcelBelieve {

	private XSSFWorkbook workbook;

	private XSSFSheet sheet;

	private List<BelieveExcel> detailsArtistes;

	public StateArtisteExcelBelieve(List<BelieveExcel> listDetailsArtistes) {
		this.detailsArtistes = listDetailsArtistes;

		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Total State");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();

		XSSFFont font = workbook.createFont();

		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		createCell(row, 0, "Nom Artiste", style);

		createCell(row, 1, "Nombre d'ecoute", style);
		createCell(row, 2, "Revenu Golbal Operation (Eur)", style);
		createCell(row, 3, "Revenu Golbal Operation (Tnd)", style);
		createCell(row, 4, "Revenu Golbal Smart (Tnd)", style);

		createCell(row, 5, "Part Artiste", style);

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		org.apache.poi.ss.usermodel.Cell cell = row.createCell(columnCount);

		if (value instanceof Double) {
			cell.setCellValue((Double) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeDataLine() {
		int rowcount = 1;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);

		style.setFont(font);

		for (BelieveExcel det : detailsArtistes) {
			Row row = sheet.createRow(rowcount++);
			int columnCount = 0;
			createCell(row, columnCount++, det.getNamea(), style);

			createCell(row, columnCount++, det.getQuantite(), style);
			createCell(row, columnCount++, det.getTTC_EUR(), style);
			createCell(row, columnCount++, det.getTtc(), style);
			createCell(row, columnCount++, det.getPart_smart(), style);

			createCell(row, columnCount++, det.getPart_artiste(), style);
		}
	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLine();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();
	}

}
