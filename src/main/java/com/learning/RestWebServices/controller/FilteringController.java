package com.learning.RestWebServices.controller;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public RBean getBean() {
		return new RBean("v1","v2","v3");
	}
	
	
	@GetMapping("/Dynfiltering")
	public MappingJacksonValue getDynBean() {
		RBean rBean = new RBean("v1","v2","v3");
		MappingJacksonValue mapping =  new MappingJacksonValue(rBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("f1","f2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("myfilter", filter);
		mapping.setFilters(filters);
		return mapping;
	}
}
