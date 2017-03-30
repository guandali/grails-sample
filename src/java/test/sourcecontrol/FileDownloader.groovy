package test.sourcecontrol;

import java.io.File;
import java.net.MalformedURLException;
import com.bosap.gisp.PropertyConfig;
import test.sourcecontrol.support.SourceControlExcutionException;

public abstract class FileDownloader {
	PropertyConfig propertyConfig;
	String hostName = "";
	int portNumber = 0;
	String filePath = "";
	String protocol = "";

	public FileDownloader(PropertyConfig propertyConfig) {
		this.propertyConfig = propertyConfig;
		resolveConfig();
	
	}
	abstract void resolveConfig();
	abstract boolean validate() throws MalformedURLException, SourceControlExcutionException;
	abstract File retrieveFiles() throws SourceControlExcutionException;
}
