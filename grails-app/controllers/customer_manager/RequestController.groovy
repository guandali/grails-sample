package customer_manager


import grails.rest.RestfulController;
//import org.ajoberstar.grgit.Grgit

import test.sourcecontrol.FileDownloader
import test.sourcecontrol.FileDownloaderFactory
import test.sourcecontrol.support.SourceControlExcutionException;

class RequestController extends RestfulController {

    def index() {
		println "index"
	}
	
	def upload(){
		println "------------------------------------";
		String typeOfFile = params.typeOfFile;
		String resourceURL = params.resourceURL;
		String typeRelatedInformation = params.typeRelatedInformation;
		FileDownloaderFactory factory = new FileDownloaderFactory();
		try {
			FileDownloader fileDownloader =  factory.getFileDownloder(typeOfFile, resourceURL, typeRelatedInformation);
			fileDownloader.validate();
			fileDownloader.retrieveFiles()
			
		}catch(MalformedURLException | 	SourceControlExcutionException e ){
		   // println e.printStackTrace();
		   System.out.println("Exception: using FileDownloader");
	       println "exception information :" + e.getMessage();
		}
		
	}
	
	
}
