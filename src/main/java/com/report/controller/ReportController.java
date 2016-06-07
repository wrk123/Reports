package com.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:application.properties")
public class ReportController {

	
	@Autowired
	
	@RequestMapping(value="/report/order/{fromDate}/{toDate}",method=RequestMethod.GET)
	public @ResponseBody String likeBlog(@PathVariable(value="fromDate") String fromdate,@PathVariable(value="toDate") Long toDate)	throws Exception{
	
		
		return "Report generated";
		
	}
	
}
