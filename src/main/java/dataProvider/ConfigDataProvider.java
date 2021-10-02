package dataProvider;

import java.io.FileInputStream;
import java.util.Properties;


public class ConfigDataProvider {
	 Properties prop;
	 FileInputStream configFile;
	
	 public ConfigDataProvider(){
		try {
			 configFile = new FileInputStream("./Config/Config.properties");
			 prop = new Properties();
			 prop.load(configFile);
		} 
		catch (Exception e) {
			System.out.println("Error details: "+e.getMessage());
		}		
	}
	
	public  String getApplicationUrl() {
		String url=prop.getProperty("welcomePageUrl");
		return url;
	}
	
	public  String getFindFlightsUrl() {
		String url=prop.getProperty("findFlightsUrl");
		return url;
	}
	
	public  String getPurchaseUrl() {
		String url=prop.getProperty("purchaseUrl");
		return url;
	}
	
	public  String getConfirmationUrl() {
		String url=prop.getProperty("confirmationUrl");
		return url;
	}
	
	
	
			
}
