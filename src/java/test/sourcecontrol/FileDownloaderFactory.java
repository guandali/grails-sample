package test.sourcecontrol;

import java.net.MalformedURLException;

import com.bosap.gisp.PropertyConfig;



public class FileDownloaderFactory {

	public FileDownloaderFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static FileDownloader getFileDownloder(String typeOfFile, String resourceUrl, String typeRelatedInformation, PropertyConfig propertyConfig) throws MalformedURLException {
		FileDownloader tempDownloader = null;
		if(typeOfFile.equals("GIT")){
			tempDownloader = new GitFileDownloader(resourceUrl,typeRelatedInformation, propertyConfig);
		}

		// We can create more if we have implementation 
		else {
			throw new IllegalArgumentException("Unknown Source Control System");
		}
		return tempDownloader;
		
	}

}
