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




//import com.bosap.gisp.exceptions.GitCloneException;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.LsRemoteCommand;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.SshSessionFactory;

public class GitFileDownloader extends FileDownloader {
	
	String branchName = "";
	String completeFileLocation = "";
	
	static String PROTOCOL = "ssh";
	List<String> knownHosts = new ArrayList<String>();
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

	public GitFileDownloader(String gitURL, String branchName) throws MalformedURLException {
		//branchName = "";
		System.out.println("branchName :" + branchName);
		knownHosts.add("github.com");
		knownHosts.add("git.wdf.sap.corp");
		knownHosts.add("github.wdf.sap.corp");
		knownHosts.add("hdbgerrit.wdf.sap.corp");
		knownHosts.add("stash.hybris.com");
		knownHosts.add("gerrit.dhcp.den2.sap.corp");
		URI gitURI;
		this.protocol = PROTOCOL;
        this.branchName = (branchName == null || branchName == "")? "master" : branchName;
		gitURL = gitURL.trim();
		branchName = branchName.trim();
		
		gitURI = getURI(gitURL);
		System.out.println ("gitURI ::" + gitURI);
		this.hostName = gitURI.getHost();
	    this.filePath = gitURI.getPath();
	    this.portNumber = getPortNumber();
		
		System.out.println("this.hostName :"+ this.hostName);
		System.out.println("this.portNumber :"+ this.portNumber);
		System.out.println("this.filePath :"+ this.filePath);
		System.out.println("this.protocol :"+ this.protocol);
		System.out.println("this.branchName :"+ this.branchName);
		
		
		
		
		
	}
	
	private URI getURI(String gitURL) throws MalformedURLException{
		try {
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
	
	
	
	private List getGitRemoteBranchList(String gitRemoteLocation)throws  MalformedURLException, GitCloneException, Exception {
		List <String> listOfBranches;
		 System.out.println("completeGitRepoPath : " +  completeFileLocation);

		try{
				//Config a SSH session
				// Jsch could find id_rsa stored under default path, otherwise may need to specify
				// Also need a password and as part of config, need to make sure hosts we support is in known_host file under .ssh folder
				// IMPORTANT: passpharse for ssh is hard-coded in GitShhConfigSessionFactory, neeed to let it pull from config file
				JschConfigSessionFactory sessionFactory = new GitShhConfigSessionFactory()
				SshSessionFactory.setInstance(sessionFactory);
				//Construct a ls-remote command and call it through SSH
				// Throw NoRemoteRepositoryException, JSchException
				
				
				
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
				System.out.println("The number of branches : " + counter;);
				return listOfBranches;
			
		}
		catch(TransportException  e ){
			String errMsg = "";

				println e.getClass();
			    println e.getCause()
				println e.getMessage()
				errMsg = "URL Format is valid, failed during run git command check if GTLC has access to the repo: ";
                
			
			throw new GitExcutionException(errMsg + gitRemoteLocation);

			
		}
			
		
	}
    @Override
	boolean validate() throws MalformedURLException {
		String regex = "^(.*)\\.git$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(filePath);
		if (!m.find()) {
			throw new MalformedURLException("Invalid Git address. Must be of the format: <host>/<path>.git");
		}
		constructCompleteFileLocation();
		
		return false;
	}

	String retrieveFiles() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}