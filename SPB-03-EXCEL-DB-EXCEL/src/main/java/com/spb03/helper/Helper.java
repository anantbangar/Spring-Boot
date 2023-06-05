package com.spb03.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.spb03.entities.Emp;

public class Helper 
{
	//check file is Excel file or not
	public static boolean checkExcelFormat(MultipartFile file)
	{
		String contentType = file.getContentType();
		
		if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//convert Excel to List of Emp
	public static List<Emp> convertExcelToListOfEmp(InputStream inputStream) throws IOException
	{
		List<Emp> empList=new ArrayList<>();
		
		Workbook workbook= new XSSFWorkbook(inputStream);
		
		try 
		{			
			Sheet sheet = workbook.getSheet("Sheet1");
			
			int rowNumber=0;			
			Iterator<Row> rows = sheet.iterator();
			
			while(rows.hasNext())
			{
				Row row = rows.next();
				
				if(rowNumber==0)
				{
					rowNumber++;
					continue;
				}
				
				Iterator<Cell> cells = row.iterator();				
				int cellId=0;
				
				Emp emp=new Emp();
				
				while(cells.hasNext())
				{
					Cell cell = cells.next();
					
					switch (cellId) 
					{
						case 0: 
							break;
						case 1:
							emp.setFirstName(cell.getStringCellValue());
							break;
						case 2:
							emp.setLastName(cell.getStringCellValue());	
							break;
						case 3:
							emp.setGender(cell.getStringCellValue());
							break;
						case 4:
							emp.setCountry(cell.getStringCellValue());
							break;
						case 5:
							emp.setDate(cell.getStringCellValue());
							break;
							
						default:
							break;						
					}
					cellId++;
				}
				empList.add(emp);
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			workbook.close();
			
		}
		
		return empList;
		
	}
	
	public static String[] HEADERS= 
	{
		"id",
		"country",
		"date",
		"first_name",
		"gender",
		"last_name"
	};
	
	public static String SHEET_NAME="emp_data";
	
	public static ByteArrayInputStream convertDataToExcel(List<Emp> empList) throws IOException
	{
		Workbook workbook= new XSSFWorkbook();
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		
		try 
		{		
			Sheet sheet = workbook.createSheet(SHEET_NAME);			
			Row headerRow = sheet.createRow(0);
			
			CellStyle cellStyle = workbook.createCellStyle();
			
			Font font = workbook.createFont();
			font.setFontHeightInPoints((short)12);
			font.setBold(true);
			
			cellStyle.setFont(font);
			
			for(int i=0;i<HEADERS.length;i++)
			{
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(HEADERS[i]);
				cell.setCellStyle(cellStyle);
			}
			
			int rowIndex=1;
			
			for(Emp emp : empList)
			{
				Row dataRow = sheet.createRow(rowIndex);
				
				dataRow.createCell(0).setCellValue(emp.getId());
				dataRow.createCell(1).setCellValue(emp.getCountry());
				dataRow.createCell(2).setCellValue(emp.getDate());
				dataRow.createCell(3).setCellValue(emp.getFirstName());
				dataRow.createCell(4).setCellValue(emp.getGender());
				dataRow.createCell(5).setCellValue(emp.getLastName());
				
				rowIndex++;				
			}
			
			workbook.write(outputStream);
			return new ByteArrayInputStream(outputStream.toByteArray());
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Fail to download excell from db...");
			return null;
		}
		finally 
		{
			workbook.close();
			outputStream.close();
			
		}
		
	}
	
	

}
