package com.spb04.config;

import org.springframework.batch.item.ItemProcessor;

import com.spb04.entities.Organization;

public class OrgItemProcessor implements ItemProcessor<Organization, Organization> 
{

	@Override
	public Organization process(Organization org) throws Exception 
	{
		return org;
	}

}
