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
	}

	// Create a new user and save it
	def create(){
		//Angular post ==> request.JSON.
		def rawData = request.JSON;
		println rawData;
		
	   def aCustomer = customerService.createCustomer(rawData);
	   if (aCustomer.validate()){
		   aCustomer.save();
		   println "saved";
		   respond aCustomer;
		  return;
//		    Add return statement, otherwise the control flow will keep going
	   }
	   else {
		   println errors;
		   def typeOfError ="Violated Schema Constraints";
           return handleException(typeOfError);
	   }
	   
	
	}
	
	def getUser(){
		def aCustomer = customerService.getCustomer(params.id);
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
		def typeOfError = "Update failed";
		println  "id is :" + params.id;
		println "params" + params;
		try{
            def rawData = request.JSON;
		    println "rawData is" + rawData;
			def resultCustomer = customerService.editCustomerProfile(rawData);
			respond resultCustomer;
		}
		// Be specific about exception type....
		catch (Exception e){
			return handleException(typeOfError);
		}
	}
	
	def validateEmail(){
		println "------validateEmail------";
		println params.unchecked_mail;
	    def result =  customerService.findUserByEmail(params.unchecked_mail);
		render result;
		//respond [data : obj] as JSON;
		
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
