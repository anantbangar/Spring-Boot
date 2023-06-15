package com.spb03.normalExcelOperation;

import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteToExcel 
{
	public static void main(String[] args) 
	{
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("student_details");
		
		Map<String, Object> data=new TreeMap<>();
		data.put("1", new Object[] {"ID","FNAME","LNAME"});
		data.put("2", new Object[] {1,"anant","bangar"});
		data.put("3", new Object[] {2,"shailoo","bangar"});
		data.put("4", new Object[] {3,"gulab","bangar"});
		
		Set<String> keySet = data.keySet();		
		
		int rowNum=0;
		
		for(String key : keySet)
		{
			Object[] objArr = (Object[]) data.get(key);
			
			XSSFRow row = sheet.createRow(rowNum++);
			
			int cellNum=0;
			
			for(Object obj : objArr)
			{
				XSSFCell cell = row.createCell(cellNum++);
				
				if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Integer)
					cell.setCellValue((Integer)obj);
			}
		}
		
		try 
		{
			FileOutputStream fout=new FileOutputStream("src/main/resources/student.xlsx");
			workbook.write(fout);
			fout.close();
			System.out.println("data write to excel file successfully");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		 
		
		
	}

}
