package dataProvider;


import java.util.HashMap;
import java.util.Map;
import pojo.ExcelPojo;

public class ExcelDataSetter {
	
	static ExcelPojo xlsPojo;
	static Map<String, ExcelPojo> metaData;
	
	public static Map<String, ExcelPojo> dataSetterByKey(String[][] excelData)  {
		metaData = new HashMap<String, ExcelPojo>();
		for(int i = 0; i < excelData.length; i++)	{
			xlsPojo = new ExcelPojo(); 
			xlsPojo.setFirstName(excelData[i][1]);
			xlsPojo.setAddress(excelData[i][2]);
			xlsPojo.setGender(excelData[i][3]);
			xlsPojo.setFreq(excelData[i][4]);
			xlsPojo.setRating(excelData[i][5]);
			
			metaData.put(excelData[i][0], xlsPojo);
		}
		return metaData;
	}
	
	public static Map<String, ExcelPojo> dataSetterForMsg(String[][] excelData)  {
		metaData = new HashMap<String, ExcelPojo>();
		for(int i = 0; i < excelData.length; i++)	{
			xlsPojo = new ExcelPojo(); 
			xlsPojo.setInput(excelData[i][1]);
			xlsPojo.setOutput(excelData[i][2]);
			
			metaData.put(excelData[i][0], xlsPojo);
		}
		return metaData;
	}
}
