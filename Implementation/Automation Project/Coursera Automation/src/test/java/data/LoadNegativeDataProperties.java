package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadNegativeDataProperties {
	static public Properties loginData = getLoginNegativeData(System.getProperty("user.dir")+"\\src\\test\\java\\Properties\\LoginNegativeData.properties");
	
	static private Properties getLoginNegativeData(String filePath) {
		Properties pro = new Properties();
		try {
			FileInputStream stream = new FileInputStream(filePath);
			try {
				pro.load(stream);
			} catch (IOException e) {
				System.out.println("Cannot read from the properties file");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		}
		return pro;
	}
}
