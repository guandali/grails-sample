package customer_manager


import grails.converters.JSON
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
		String message = "";
		String fileName = ""
		boolean isSuccess = true;
		String typeRelatedInformation = params.typeRelatedInformation;
		
		try {

			
			
		}catch(IllegalArgumentException |MalformedURLException | SourceControlExcutionException e ){
		   isSuccess = false;
		   message = e.getMessage();
		   // println e.printStackTrace();
		   System.out.println("Exception: using FileDownloader");
	       println "exception information :" + e.getMessage();
		}
		def result = [success: isSuccess, name: fileName,  message: message]
		render result as JSON

		
		
		
	}
	
	
}
