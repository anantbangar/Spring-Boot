package com.spb03.normalExcelOperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFromExcel 
{
	public static void main(String[] args) throws InvalidFormatException, IOException 
	{
		//File file=new File("src/main/resources/student.xlsx");
		
		FileInputStream fileInputStream=new FileInputStream("src/main/resources/student.xlsx");
		
		XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		Iterator<Row> rows = sheet.iterator();
		
		while(rows.hasNext())
		{
			Row row = rows.next();
			
			Iterator<Cell> cells = row.iterator();
			
			while(cells.hasNext())
			{
				Cell cell = cells.next();
				
				CellType cellType = cell.getCellType();	
				
				switch (cellType) 
				{
					case NUMERIC :
								System.out.println(cell.getNumericCellValue());
								break;
								
					case STRING :
								System.out.println(cell.getStringCellValue());
								break;				
				}
			}
		}
		
		fileInputStream.close();		
	}
}
