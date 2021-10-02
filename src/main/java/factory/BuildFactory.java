package factory;

import dataProvider.ConfigDataProvider;
import dataProvider.ExcelDataProvider;

public class BuildFactory {

	public static ConfigDataProvider ConfigObject() {		
		ConfigDataProvider config = new ConfigDataProvider();
		return config;
	}
	
	public static ExcelDataProvider ExcelObject() {		
		ExcelDataProvider excel = new ExcelDataProvider();
		return excel;
	}
	
}
