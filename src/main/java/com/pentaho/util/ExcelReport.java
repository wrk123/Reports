package com.pentaho.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.pentaho.reporting.engine.classic.core.DataFactory;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.ReportProcessingException;
import org.pentaho.reporting.engine.classic.core.modules.misc.datafactory.sql.DriverConnectionProvider;
import org.pentaho.reporting.engine.classic.core.modules.misc.datafactory.sql.SQLReportDataFactory;
import org.pentaho.reporting.engine.classic.core.wizard.RelationalAutoGeneratorPreProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@PropertySource("classpath:application.properties")
public class ExcelReport extends AbstractReportGenerator{

private static final String QUERY_NAME = "ReportQuery";
	
	@Autowired 
	Environment env;
	
	@Override
	public MasterReport getReportDefinition() {
		final MasterReport report = new MasterReport();	    
		report.setQuery(QUERY_NAME);
	    report.addPreProcessor(new RelationalAutoGeneratorPreProcessor());
	    return report;
	}

	@Override
	public DataFactory getDataFactory() {
		
		final DriverConnectionProvider sampleDriverConnectionProvider = new DriverConnectionProvider();
		    sampleDriverConnectionProvider.setDriver(env.getProperty("spring.datasource.driver-class-name"));
		    sampleDriverConnectionProvider.setUrl(env.getProperty("spring.datasource.url"));
		    sampleDriverConnectionProvider.setProperty("user", env.getProperty("spring.datasource.username"));
		    sampleDriverConnectionProvider.setProperty("password",env.getProperty("spring.datasource.password"));

		    final SQLReportDataFactory dataFactory = new SQLReportDataFactory(sampleDriverConnectionProvider);
		    dataFactory.setQuery(QUERY_NAME,"select customerName, city, state, postalCode, country from customers order by UPPER(customerName)");

		    return dataFactory;
		
	}

	@Override
	public Map<String, Object> getReportParameters() {
		return null;
	}
	
	
   	 public static void report() throws IOException, ReportProcessingException
	  {
	    // Create an output filename
	    final File outputFilename = new File(ExcelReport.class.getSimpleName() + ".ods");
	    // Generate the report
	    	new ExcelReport().generateReport(AbstractReportGenerator.OutputType.EXCEL, outputFilename);
	    // Output the location of the file
	    System.out.println("Generated the report [" + outputFilename.getAbsolutePath() + "]");
	  }
	
}
