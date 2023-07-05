package com.spb04.config;

import java.io.File;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.spb04.entities.Organization;

@Configuration
//We can use @EnableBatchProcessing annotaion in Spb04CsvDbSpringBatchApplication class
//@EnableBatchProcessing
public class BatchConfig 
{
	//@Autowired
	//private OrgRepo orgRepo;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private DataSource dataSource;
	
	//Item Reader
	@Bean
	@StepScope
	public FlatFileItemReader<Organization> itemReader(@Value("#{jobParameters[fullPathFileName]}") String pathOfFile)
	{
		FlatFileItemReader<Organization> itemReader = new FlatFileItemReader<>();
		//itemReader.setResource(new ClassPathResource("org.csv")); //for static file upload
		itemReader.setResource(new FileSystemResource(new File(pathOfFile))); //for dynamic file upload
		itemReader.setLineMapper(getLineMapper());
		itemReader.setLinesToSkip(1);
		
		return itemReader;
		
		
	}

	private LineMapper<Organization> getLineMapper() 
	{
		DefaultLineMapper<Organization> lineMapper = new DefaultLineMapper<>();
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(
				new String[] {
						"Name",						
						"Founded"						
				});
		lineTokenizer.setIncludedFields(new int[] {1,3});
		
		BeanWrapperFieldSetMapper<Organization> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Organization.class);
		
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		
		return lineMapper;
	}
	
	//Item Processor
	@Bean
	public OrgItemProcessor itemProcessor()
	{
		return new OrgItemProcessor();
	}
	
	
	//Item Writer using JpaItemWriter
	@Bean
	public JpaItemWriter<Organization> itemWriter()
	{		
		JpaItemWriter<Organization> itemWriter = new JpaItemWriter<>();
		itemWriter.setEntityManagerFactory(entityManagerFactory);
		itemWriter.setUsePersist(true);
		return itemWriter;		
	}
	
	
	/*
	//Item Writer using JdbcBatchItemWriter
	@Bean
	public JdbcBatchItemWriter<Organization> itemWriter()
	{
		JdbcBatchItemWriter<Organization> itemWriter = new JdbcBatchItemWriter<>();
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Organization>());
		itemWriter.setSql("insert into organization(name,founded) values(:name,:founded)");
		itemWriter.setDataSource(this.dataSource);
		
		return itemWriter;	
	}
	*/
	
	//Job
	@Bean
	public Job importOrgJob(FlatFileItemReader<Organization> itemReader)
	{
		return this.jobBuilderFactory.get("ORG-IMPORT-JOB")
				.incrementer(new RunIdIncrementer())
				.flow(step1(itemReader))
				.end()
				.build();
				
	}

	@Bean
	public Step step1(FlatFileItemReader<Organization> itemReader) 
	{
		return this.stepBuilderFactory.get("step1")
				.<Organization,Organization>chunk(10)
				.reader(itemReader)
				.processor(itemProcessor())
				.writer(itemWriter())
				.build();
	}
	
	

}
