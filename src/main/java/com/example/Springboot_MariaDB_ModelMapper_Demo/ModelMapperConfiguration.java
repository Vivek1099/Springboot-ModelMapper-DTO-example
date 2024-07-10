package com.example.Springboot_MariaDB_ModelMapper_Demo;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration 
{
	@Bean
	ModelMapper mymodelmapper()
	{
		return new ModelMapper();
	}
}
