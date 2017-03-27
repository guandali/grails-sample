package test.sourcecontrol;

import java.io.File;
import java.net.MalformedURLException;

import test.sourcecontrol.support.SourceControlExcutionException;

public abstract class FileDownloader {
	String hostName = "";
	int portNumber = 0;
	String filePath = "";
	String protocol = "";

	public FileDownloader() {
	
	}
	
	abstract boolean validate() throws MalformedURLException, SourceControlExcutionException;
	abstract File retrieveFiles() throws SourceControlExcutionException;

}
