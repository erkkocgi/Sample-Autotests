package ee.cgi.sample.helpers;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


	/**
	 * Class where the helper functions are defined.
	 * It provides helper function for deleting the folder and
	 * returning timestamp.
	 * TODO: method for deleting test results using deleteFolder method
	 */

public class UtilTest {

	/**
	 * Deletes the folder.
	 *
	 * @param tempFolder defines the folder to be deleted
	 */

	public static void deleteFolder(String tempFolder) {
		File tempTestFolder = new File("./" + "target" + File.separator + tempFolder);
		if (tempTestFolder.exists() && tempTestFolder.isDirectory()) {
			try {
				FileUtils.deleteDirectory(tempTestFolder);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method returns the current timestamp of the given DateFormat
	 *
	 * @return timestamp
	 */

	public static String getDate(){
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Tallinn"));
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Tallinn"));
		String timestamp = dateFormat.format(cal.getTimeInMillis());
		return timestamp;
	}

}
