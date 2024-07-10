package com.example.Springboot_MariaDB_ModelMapper_Demo;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController 
{
	@Autowired
	EmployeeRepository erepo;
	
	@Autowired 
	ModelMapper model;
	
	@GetMapping("/test")
	public String test()
	{
		return "This is Modelmapper demo test";
	}
	
	@RequestMapping("/save")
	public EmployeeDTO savedata(@RequestBody EmployeeDTO employeeDTO)
	{
		Employee employee=new Employee();
		model.map(employeeDTO, employee);
		erepo.save(employee);
		return employeeDTO;
	}
	
	@RequestMapping("/all")
	public List<EmployeeDTO> allemployee()
	{
	return erepo.findAll().stream().map(employee->model.map(employee,EmployeeDTO.class))
			.collect(Collectors.toList());// changing to the list				
	}
	
	@RequestMapping("/upd/{id}")
	public EmployeeDTO update(@RequestBody EmployeeDTO employeeDTO,@PathVariable int id)
	{
	Employee e=erepo.findById(id).get();
	e.setName(employeeDTO.getName());
	e.setCity(employeeDTO.getCity());
	model.map(e,employeeDTO); // there is another option below this i.e BeanUtils
	//BeanUtils.copyProperties(b,bookdto);
	return employeeDTO;
	}
}
