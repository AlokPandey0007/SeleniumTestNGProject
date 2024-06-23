package NAGPSelenium.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import NAGPSelenium.Helpers.TestLogger;

public class PropertyReader {

	Properties properties;

	// constructor which accepts the relative path of the property file apart from
	// base project path
	public PropertyReader(String relativeFilePath) {
		String basePath = GeneralUtilities.GetProjectPath();

		String completeFilePath = basePath + relativeFilePath;

		File file = new File(completeFilePath);

		FileInputStream configFileReader = null;

		try {
			TestLogger.info("Reading property file from path " + completeFilePath);
			configFileReader = new FileInputStream(file);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		properties = new Properties();

		try {

			properties.load(configFileReader);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// returning instance of property file.
	public Properties GetProperty() {
		return properties;
	}

	// Reading property file based on key
	public String ReadProperty(String key) {
		TestLogger.info("Reading value from property file for key " + key);
		return properties.getProperty(key);
	}

	// this method is specific to reading framework configuration
	public static String ReadConfiguration(String key) {
		TestLogger.info("Reading config property for key " + key);
		return new PropertyReader("/src/main/resources/Config.Properties").ReadProperty(key);
	}

}
