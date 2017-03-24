package test.sourcecontrol;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.LsRemoteCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.TransportException;
//import com.bosap.gisp.exceptions.GitCloneException;

import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.SshSessionFactory;

import test.sourcecontrol.support.GitExcutionException;
import test.sourcecontrol.support.GitShhConfigSessionFactory;
import test.sourcecontrol.support.SourceControlExcutionException;



public class GitFileDownloader extends FileDownloader {
	private String originalURL = "";
	private String branchName = "";
	private String completeFileLocation = "";
	
	static String PROTOCOL = "ssh";
	private static List<String> knownHosts;
	static 
	{
		knownHosts = new ArrayList<String>();
		knownHosts.add("github.com");
		knownHosts.add("git.wdf.sap.corp");
		knownHosts.add("github.wdf.sap.corp");
		knownHosts.add("hdbgerrit.wdf.sap.corp");
		knownHosts.add("stash.hybris.com");
		knownHosts.add("gerrit.dhcp.den2.sap.corp");
		
	}
    private static final Map<String, String>  portNumberMap;
    static
    {
    	portNumberMap = new HashMap<String, String>();
    	portNumberMap.put("gerritport", "29418");
    	portNumberMap.put("gitport", "29418");
    	portNumberMap.put("githubport", "22");
    	portNumberMap.put("hdbgerritport", "29418");
    	portNumberMap.put("stashport", "7999");
    }

	public GitFileDownloader(String gitInput, String branchName) throws MalformedURLException {
		System.out.println("branchName :" + branchName);
		URI gitURI;
		this.protocol = PROTOCOL;
        //branchName = (branchName == null || branchName == "")? "master" : branchName;
        gitInput = gitInput.trim();
		branchName = branchName.trim();
		this.branchName = branchName;
		this.originalURL = gitInput;
		gitURI = getURI(gitInput);
		this.hostName = gitURI.getHost();
	    this.filePath = gitURI.getPath();
	    if(!isSupportedHost()){
	    	throw new MalformedURLException(this.hostName + " is not supported host ");
	    }
	    if(!isValidPath()){
	    	throw new MalformedURLException(this.originalURL + " is not a valid git address:  Must be of the format: <host>/<path>.git ");
	    }
	    this.portNumber = getPortNumber();
		
		System.out.println("this.hostName :"+ this.hostName);
		System.out.println("this.portNumber :"+ this.portNumber);
		System.out.println("this.filePath :"+ this.filePath);
		System.out.println("this.protocol :"+ this.protocol);
		System.out.println("this.branchName :"+ this.branchName);
		
		
		
		
		
	}
	
	private boolean isSupportedHost() {
		boolean supportedHost = true;
		if(knownHosts.indexOf(this.hostName) == -1){
			supportedHost = false;
		}
		return supportedHost ;
	}

	private URI getURI(String gitURL) throws MalformedURLException{
		try {
			System.out.println("gitURL :" + gitURL);
			return new URI("http://"+gitURL);
		} catch (URISyntaxException e) {
			System.out.println (e.getCause());
			 String errorMsg= "Invalid Git address. Must be of the format: <host>/<path>.git with a known host: "+knownHosts;
			 System.out.println (errorMsg);
			throw new MalformedURLException(errorMsg);
		}
		
	}
	
	private int getPortNumber() throws MalformedURLException{

		String portConfig = getHostAlias() + "port";
		try{
			this.portNumber = Integer.parseInt(portNumberMap.get(portConfig));
			System.out.println("portNumber is" + this.portNumber);
			return portNumber;
		}
		catch(NumberFormatException e){
			throw new MalformedURLException("Failed to locate git port from "+portConfig);
		}
	}
	
	private String getHostAlias(){
		String hostAlias = this.hostName.split("\\.")[0];
		System.out.println("hostAlias : " + hostAlias); 
		return hostAlias;
		
	}
	
	private boolean isInt(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private boolean isValidPath(){
		boolean resultOfValidation = true;
		String regex = "^(.*)\\.git$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(filePath);
		if (!m.find()) {
			resultOfValidation = false;
		}
		return resultOfValidation;
		
	}
	// Build complete URL base on provided URI
	private void constructCompleteFileLocation(){
		String projectPreFix = "";
		String projectPostFix = this.filePath;
		projectPreFix = projectPreFix + "ssh://";
		if (getHostAlias().equals("github")) {
			projectPreFix = projectPreFix + "git@";
		} else {
			projectPreFix = projectPreFix + "sapbd@";
		}
		//Add hostname
		projectPreFix = projectPreFix + hostName;
		// ssh://<Username>@<Hostname>    + <Portnumber>
		if (!isInt(projectPostFix.split("/")[0])) {
			// Insert port since none was specified
			projectPreFix = projectPreFix + ':'+ portNumber;
		} else {
			// Replace existing port with known one
			projectPreFix= projectPreFix + ":";
			projectPostFix = projectPostFix.replaceAll("^[0-9]+/", Integer.toString(portNumber));
			
		}
		
		// ssh://<Username>@<Hostname>:<Portnumber>  + <Path>
		this.completeFileLocation = projectPreFix + projectPostFix;
		System.out.println("completeFileLocation  :" + completeFileLocation);

	}
	
	
	
	private List<String> getGitRemoteBranchList(String gitRemoteLocation)throws  GitExcutionException, Exception {
		List <String> listOfBranches;
		 System.out.println("completeGitRepoPath : " +  completeFileLocation);

		try{
				//Config a SSH session
				// Jsch could find id_rsa stored under default path, otherwise may need to specify
				// Also need a password and as part of config, need to make sure hosts we support is in known_host file under .ssh folder
				// IMPORTANT: passpharse for ssh is hard-coded in GitShhConfigSessionFactory, neeed to let it pull from config file
				JschConfigSessionFactory sessionFactory = new GitShhConfigSessionFactory();
				SshSessionFactory.setInstance(sessionFactory);
				//Construct a ls-remote command and call it through SSH
				// Throw NoRemoteRepositoryException, JSchException
//				SshSessionFactory.setInstance(sessionFactory);
				
				
				final LsRemoteCommand lsCmd = new LsRemoteCommand(null);
				lsCmd.setHeads(true);
				lsCmd.setRemote(completeFileLocation);
				String branchesString = lsCmd.call().toString();
				System.out.println(branchesString);
				
				String START = "Ref[refs/heads/";
				// loophole a branch could be names as '='
				String END = "=";
				final Pattern pattern = Pattern.compile(Pattern.quote(START) + "(.*?)" + Pattern.quote(END));
				Matcher match = pattern.matcher(branchesString);
				listOfBranches = new ArrayList <String>();
				//Find all matches of branch using Regular Expression
				while (match.find()) {
					listOfBranches.add(match.group().replace(START, "").replace(END, ""));
				}
				int counter =0;
				
				for(String aBranch : listOfBranches){
					System.out.println("Branch :" + aBranch);
					counter++;
				}
				System.out.println("The number of branches : " + counter);
				return listOfBranches;
			
		}
		catch(TransportException  e ){
			    String errMsg = "";
				System.out.println(e.getClass());
				errMsg = "URL Format is valid, failed during run git command check if GTLC has access to the repo: ";  
			    throw new GitExcutionException(errMsg + this.originalURL);

			
		}
			
		
	}
    @Override
	boolean validate() throws MalformedURLException, SourceControlExcutionException {
    	boolean resultOfValidation = true;
    	String errorMsg = "";

		constructCompleteFileLocation();
		
			try {
				 List <String> listOfBranches = getGitRemoteBranchList(completeFileLocation);
				 if(!listOfBranches.contains(branchName)){
					 resultOfValidation = false;
					 errorMsg = this.originalURL + " doesn't have a branch called "  + this.branchName;
					 System.out.println("DEBUG : " + this.branchName);
					 System.out.println("Err  :" + errorMsg);
					 throw new MalformedURLException(errorMsg);
				 }
				
			} catch (GitExcutionException  e) {
				//e.printStackTrace();
				System.out.println("Err" + e.getMessage());
				throw new SourceControlExcutionException( e.getMessage());
			} 
			catch (Exception e) {
				errorMsg = "Failed during accessing the specified repo: " + this.originalURL;
				System.out.println(errorMsg);
				throw new SourceControlExcutionException(errorMsg);
				
				
			    //e.printStackTrace();
			}

		
		return resultOfValidation;
	}

	String retrieveFiles() {
		
		CloneCommand cloneCmd = new CloneCommand();
		cloneCmd.setURI(completeFileLocation);
		try {
			cloneCmd.call();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return branchName;
       
	}
	
	
	
	

}
