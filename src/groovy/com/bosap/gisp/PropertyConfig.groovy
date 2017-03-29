
package com.bosap.gisp;

import org.springframework.web.context.support.ServletContextResource

import grails.util.Holders
import com.bosap.gisp.exceptions.NoSuchConfigException

public class PropertyConfig {
	//application.properties
	
	String adminemail
	String applicationemail
	String contactemail
	String mailpostfix
	String helppage
	String gispip
	String getscanrequestsservice
	String getscanstatsservice
	String getallanalystservice
	String assignAnalystsToRequest
	String getapprovalformservice
	String aproveApprovalFormService
	String saveApprovalFormService
	String approveScanStepService
	
	//String ipApprovalSteps
	//String exportApprovalSteps
	String progressupload
	String progresscodecenter1
	String progresscodecenter2
	String progressprotex1
	String progressprotex2
	String progressprotex3
	String progressapproved
	String requestprogress
	String delimiter
	String supportedUploadTypes
	String nexusurl
	
	String nexushttpport
	String nexushttpsport
	String gerritport
	String gitport
	String githubport
	String hdbgerritport
	String productiongitport
	String stashport
	
	String gispScripts
	String knowngithosts
	//String shipnotship
	String environmenttype
	String proddownloadlocation
	//String devdownloadlocation
	
	//cc-constants.properties
	String codecenterserver
	//String username
	//String password 
	//String defaultlicense 
	String applicationname 
	//String mobileapplicationname 
	String applicationversion 
	String componentversionprefix 
	String approver 
	String approver2 
	//String databaseaddress 
	//String databaseaddressproduction 
	//String databasename 
	//String databaseschema 
	//String databaseusername 
	//String databasepassword 
	//String programidname 
	String requestername 
	String engcontactname 
	String productnamename 
	String productversionname 
	String previousname 
	String protexscanname 
	String protexscanvalue 
	String exportscanname 
	String exportscanvalue 
	String srcshippedname 
	String ignorePreviousScanAID
	String ignorePreviousScanOptionValue
	String isBackLogProcessAID
	String rtcDateAID
	String devStartDateAID
	String devEndDateAID
	String deliveryIsSoldAID
	String codetreeAID
	String deliveryModeAID
	String scantypename 
	String scantypecomments 
	String scantypevalue 
	String totalmbname  
	//String totalsrcmbname 
	//String totalsloccountname 
	//String protexprojectname 
	//String protexprojectid 
	String componentcodetree 
	String componentcodetreeId
	//String componentprid 
	String componentprogramname 
	String protexservername 
	//String onetimescangroup 
	//String totalcodematchesname 
	//String totalstringsearchesname 
	//String totalpattermatchesname 
	String requiredapprovals 
	String actualapprovalname 
	//String protexapprovalname 
	String protexworkflow 
	String exportworkflow 
	String workflowlegend 
	String allvalidapplications 
 
	
	
	//protex-constants.properties
	String  protexusername
	String protexpassword
	//String serverport
	String destinationfolderbasepath
	String destinationfoldernobase
	String destinationfolder
	String sshusername
	String sshpassword
	//String sizecommand
	//String sloccommand
	//String lastUsedProtexServer
	String blacklistedprotexservers
	String getprojectidservice
	String updatepathservice
	String cloneprojectservice
	String createprojectservice
	String scanstatsservice
	String logerrorservice
	String bomComponentsService
	String projectCodeTreeService
	String scanProjectService
	String getRequestFormService
	//codecentersdk.properties
	String createRequestAttachmentByFilePath
	String createRequestAttachmentByContent
	
	//trackingsystem.properties
	String getAttributeAllowedValues
	String getComponentAttributeValue
	String getUserIdForName
	String getComponentByName
	String getComponents
	String getComponentsProduction
	String getPermittedUsersForRequest
	String getInboundBomSnapshotService
	String updateProtexBomService
	String updateInboundBomSnapshotService
	String updateInboundBomSnapshotRequestStatus
	
	String createComponent
	String createRequest
	
	String updateComponentAttribute
	String createRequestAttachment
	String queryIsBackLogProcessByRequestId
	String getScanRequestByProgramId
	String getRequestIdForComponentApplication
	String getComponentByNameVersion
	String updateBomSnapShot
	String removeUserRoleFromRequest
	String addUserRoleToRequest
	String createUser
	String getBomSnapShotByRequest
	String programRepositoryId
	String componentprogramId
	String applicationid
	
	String ccSDKUrl
	String tsUrl
	String protexUrl
	String trackingSystemBomProcessUrl
	String checkViewBomConditions
	String getPMs
	 
	//inbound.properties
	String inboundUrl
	String getAllLicense
	String getImproperLicense
	String createRequestsForComponentCollection
	String getSuggestedComponents
	String getSuggestedComponentVersion
	String setSuggestedComponentLicenses
	String updateGenericKBComponentIdLicensesByName
	
	String gispUtilsUrl
	String sendEmail
	
	String siriusServiceUrl
	String getSuggestProgramDeliveriesByName 
	
	def synchronized getConfig(String prop) throws IOException, NoSuchConfigException{
		def v = Holders.config.getProperty(prop)
		if(v == null){
			throw new NoSuchConfigException("Unknown configuration: '"+prop+"'")
		}
		return v
	}

	def synchronized getMultiValueArray(String prop) throws IOException, NoSuchConfigException{
		String  v = getConfig(prop)
		String  d = getConfig(delimiter)
		v.tokenize(d)
	}
	
	
}
