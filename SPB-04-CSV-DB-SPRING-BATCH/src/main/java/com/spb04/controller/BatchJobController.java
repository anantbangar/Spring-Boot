package com.spb04.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BatchJobController 
{
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job;
	
	private final String TEMP_STORAGE="C:/Users/Anant/Desktop/batch-files/";
	
	@GetMapping("/home")
	public String home()
	{
		return "home";
	}
	
	@PostMapping("/uploadCSV")
	public ResponseEntity<BatchStatus> startBatch(@RequestParam("file") MultipartFile multipartFile) throws IllegalStateException, IOException
	{
		//copy the file to some temp storage in your VM
		
		try 
		{
			String originalFilename = multipartFile.getOriginalFilename();
			
			File fileToUpload=new File(TEMP_STORAGE+originalFilename);
			
			multipartFile.transferTo(fileToUpload);
			
			JobParameters jobParameters =new JobParametersBuilder()
					.addString("fullPathFileName", fileToUpload.getAbsolutePath())
					.addLong("startAt", System.currentTimeMillis()).toJobParameters();
			
			JobExecution jobExecution=this.jobLauncher.run(job, jobParameters);
						
			if(jobExecution.getExitStatus().equals(ExitStatus.COMPLETED))
			{				
				//delete files from TEMP_STORAGE
				Files.deleteIfExists(Paths.get(fileToUpload.getPath()));
			}		
			
			return ResponseEntity.ok(jobExecution.getStatus());
		} 
		catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) 
		{			
			e.printStackTrace();
			return null;
		}
		
	}

}
