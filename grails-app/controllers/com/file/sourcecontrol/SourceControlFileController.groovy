package com.file.sourcecontrol

import grails.converters.JSON
import test.sourcecontrol.FileDownloader
import test.sourcecontrol.FileDownloaderFactory
import test.sourcecontrol.support.SourceControlExcutionException
import com.bosap.gisp.PropertyConfig

class SourceControlFileController {
	def propertyConfig;

    def index() { 
		println "Connection Testing ";
	}
	
	def getSourceFiles(){
		println "Get Source Files";
		println "*------------------------------------------------------------*";
		def fileType = params.fileType;
		def resourceURL = params.resourceURL;
		def additionalInfo = params.additionalInfo;
		
		def typeRelatedInformation = params.typeRelatedInformation;
		String message = "";
		String fileName = ""
		boolean isSuccess = true;
		
//		try {
			FileDownloaderFactory factory = new FileDownloaderFactory();
			FileDownloader fileDownloader =  factory.getFileDownloder(fileType, resourceURL, additionalInfo, propertyConfig);
			if(!fileDownloader.validate()) isSuccess = false;
			File localFile = fileDownloader.retrieveFiles();
			fileName = localFile.toString();
//		}
//		catch(IllegalArgumentException |MalformedURLException | SourceControlExcutionException e){
//			isSuccess = false;
//			message = e.getMessage();
//			// println e.printStackTrace();
//			System.out.println("Exception: using FileDownloader");
//			println "exception information :" + e.getMessage();
//			
//		}
		def result = [success: isSuccess, name: fileName,  message: message]
		render result as JSON

		
		
		
	}
}
