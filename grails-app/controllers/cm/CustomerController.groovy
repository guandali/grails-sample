package cm
import grails.converters.*
import grails.rest.*
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONObject


class CustomerController extends RestfulController{
	static responseFormats = ['json', 'xml'];
	def customerService;

	
	def list(){
		//println request;
		println request.contextPath;
		def customerList = customerService.getCustomersList();
		respond customerList;
	}
	
	
	/** findUserByName
	 * Use dynamic finder to find a GORM instance by customer_id
	 * Check if it is valid 
	 * @return a valid customer instance
	 */
	def findCustomer(){
		println "CustomerController.findCustomer";
		println "searchString:" + params.searchString;
		
		def result = customerService.getCustomerByName(params.searchString);
		if (result == null) {
           
		}
		println result.customer_last_name ;
		//Reuse list page
		//render (view:"/customer/list", model: [list: result]);
	}

	// Create a new user and save it
	def create(){
		//println "params" + params;
		//Angular post ==> request.JSON.
		def rawData = request.JSON;
		
		def aCustomer = customerService.createCustomer(rawData);
	  // Validate format 
		aCustomer.validate();
		aCustomer.save();
		respond aCustomer;
//	   if (aCustomer.validate()){
//		   aCustomer.save();
//		   println "saved";
//		  //render (view:"/customer/list", model: [list: aCustomer]);
//		   respond aCustomer;
//		  return;
////		    Add return statement, otherwise the control flow will keep going
//	   }
//	   else {
//		   def typeOfError ="Violated Schema Constraints";
//           return handleException(typeOfError);
//	   }
	   
	
	}
	
	def getUser(){
		println "----------CustomerController.getUser--------------"
		def aCustomer = customerService.getCustomer(params);
		if (aCustomer == null){
			def typeOfError = "Cannot Access This Customer"
			return handleException(typeOfError);
		}	
		respond aCustomer;
	}
	
	/*
	 * editProfile
	 * Allow user to modify existing profile
	 * Only part of profile could be modified including: address
	 * 
	 */
	def editProfile(){
		println "Request: " + request.JSON.toString();
		def typeOfError = "Update failed";
		println  "params :" + params.id;
		
        try{
			def resultCustomer = customerService.editCustomerProfile(params);
			respond resultCustomer;
		}
		// Be specific about exception type....
		catch (Exception e){
			return handleException(typeOfError);
		}
	}
	
	def validateEmail(){
		println params.email;
	    respond customerService.getUserByEmail(params.email);
	}
	
	/**
	 * Customized error message 
	 * @param typeOfError
	 * @return
	 */
	def handleException(typeOfError){	
		def failureSummary = new JSONObject();
		failureSummary.put("type", typeOfError);
		def map = [failure:failureSummary]
		//render(view:"/customer/errinfo", model: map);
		respond failureSummary;
		
		
	}
	
	
}
