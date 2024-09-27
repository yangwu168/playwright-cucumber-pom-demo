package com.orangehrmlive.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JavaHelperLibrary {

	public static final Logger log = LogManager.getLogger(JavaHelperLibrary.class);

	// Java utility helper methods

	public void delay(double inSeconds) {
		try {
			Thread.sleep((long) (inSeconds * 1000));
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}
	}
	
	public String getAbsoluteFilePath(String relativePath) {
		String finalPath = null;
		try {
			File file = new File(relativePath);
			finalPath = file.getAbsolutePath();
			log.info("File Full Path: " + finalPath);
		} catch (Exception e) {
			log.error("Error: ", e);
			assertTrue(false);
		}
		return finalPath;
	}

	public String getCurrentTime() {
		String finalTimeStamp = null;
		try {
			Date date = new Date();
			String tempTime = new Timestamp(date.getTime()).toString();
			finalTimeStamp = tempTime.replace('-', '_').replace(':', '_').replace('.', '_').replace(' ', '_')
					.replaceAll("_", "");

			// log.info("current time: " + tempTime);
			log.info("Timestamp: " + finalTimeStamp);
		} catch (Exception e) {
			log.error("Error: ", e);
			assertTrue(false);
		}

		return finalTimeStamp;
	}

	public void checkDirectory(String inputPath) {
		try {
			File file1 = new File(inputPath);
			String abPath = file1.getAbsolutePath();
			File file2 = new File(abPath);

			if (!file2.exists()) {
				if (file2.mkdirs()) {
					log.info("Directories are created...");
				} else {
					log.info("No directories created as they already exist...");
				}
			}
		} catch (Exception e) {
			log.error("Error: ", e);
			assertTrue(false);
		}
	}

	public boolean isFileDownloaded(String fileDownloadPath, String fileName) {
		try {
			checkDirectory(fileDownloadPath);
			File dir = new File(fileDownloadPath);
			File[] dirContents = dir.listFiles();

			for (int i = 0; i < dirContents.length; i++) {
				if (dirContents[i].getName().equals(fileName)) {
					// File has been found, it can now be deleted:
					dirContents[i].delete();
					return true;
				}
			}
		} catch (Exception e) {
			log.error("Error: ", e);
			assertTrue(false);
		}
		return false;
	}

}
