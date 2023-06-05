package com.spb03.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spb03.entities.Emp;
import com.spb03.helper.Helper;
import com.spb03.service.EmpService;

@RestController
@RequestMapping("/emps")
public class ExcelToDBController 
{
	@Autowired
	private EmpService empService;
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadEmpToDb(@RequestParam("file") MultipartFile file) throws IOException
	{
		if(Helper.checkExcelFormat(file))
		{
			this.empService.saveEmp(file);
			return ResponseEntity.ok(Map.of("message","file uploaded successfully and data is saved to DB..."));
			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file...");
	}
	
	@GetMapping
	public ResponseEntity<List<Emp>> getAllEmp()
	{
		List<Emp> allEmp = this.empService.gellAllEmp();
		return ResponseEntity.ok(allEmp);
	}

}
