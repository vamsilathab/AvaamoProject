package utility;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {
	// private final Logger LOGGER = org.testng.log4testng.Logger.getLogger(Helper.class);
	
	public static String captuteScreenshot(WebDriver driver, String screenshotname) throws IOException {
		String errflpath;
		TakesScreenshot ts = (TakesScreenshot) driver;
		File destFile = new File("./Screenshots/"+screenshotname+System.currentTimeMillis()+".png");
		File srcFile = ts.getScreenshotAs(OutputType.FILE);

		//specify project path instead

		try {
			
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			System.out.println("Error during capturing screenshot"+e.getMessage());
		}
		
		errflpath = destFile.getAbsolutePath();
		
		return errflpath;
	}
    
}
