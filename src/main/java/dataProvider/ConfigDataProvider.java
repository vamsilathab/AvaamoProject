package dataProvider;

import java.io.FileInputStream;
import java.util.Base64;
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
		String url=prop.getProperty("AvaamoBotPageUrl");
		return url;
	}
		
	public String getDownloadUrl() {
		return prop.getProperty("DownloadMotorPolicyUrl");
	}
	
			
}
