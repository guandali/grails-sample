package test.sourcecontrol;

import java.net.MalformedURLException;



public class FileDownloaderFactory {

	public FileDownloaderFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static FileDownloader getFileDownloder(String typeOfFile, String resourceUrl, String typeRelatedInformation) throws MalformedURLException {
		FileDownloader tempDownloader = null;
		if(typeOfFile.equals("GIT")){
			tempDownloader = new GitFileDownloader(resourceUrl,typeRelatedInformation );
		}

		// We can create more if we have implementation 
		else {
			throw new IllegalArgumentException();
		}
		return tempDownloader;
		
	}

}
