package com.spb03.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spb03.entities.Emp;
import com.spb03.helper.Helper;
import com.spb03.repository.EmpRepo;

@Service
public class EmpService 
{
	@Autowired
	private EmpRepo empRepo;
	
	public void saveEmp(MultipartFile file) throws IOException
	{
		List<Emp> emps = Helper.convertExcelToListOfEmp(file.getInputStream());
		this.empRepo.saveAll(emps);
	}
	
	public List<Emp> gellAllEmp()
	{
		List<Emp> emps = this.empRepo.findAll();
		return emps;
	}
	
	public ByteArrayInputStream getAllEmpForExcell() throws IOException
	{
		List<Emp> allEmp = this.empRepo.findAll();
		ByteArrayInputStream byteArrayInputStream = Helper.convertDataToExcel(allEmp);
		return byteArrayInputStream;
	}

}
