package cm
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONObject
class CustomerController {
	
	def customerService;

	
    def index() {
       
    }
	def list(){
		def list = customerService.getCustomersList();
		[list: list];
	}
	
	//Load a user for form
	def displayform(){
		println"@CustomerControllerdisplayform()";

	}
	
	/** findbyid 
	 * Use dynamic finder to find a GORM instance by customer_id
	 * Check if it is valid 
	 * @return a valid customer instance
	 */
	def findbyid(){
		
		def result = customerService.getCustomerByID(params);
		if (result == null) {
			println "result is" + result.getClass();
			redirect url: "/search";
			return;
		}
		println result.customer_last_name ;
		//Reuse list page
		render (view:"/customer/list", model: [list: result]);
	}

	// Create a new user and save it
	def create(){
		def aCustomer = customerService.createCustomer(params);
	  // Validate format 
	   if (aCustomer.validate()){
		   aCustomer.save();
		   println "saved";
		   println aCustomer.customer_id;
		  render (view:"/customer/list", model: [list: aCustomer]);
		  return;
		   // Add return statement, otherwise the control flow will keep going
	   }
	   else {
		   def typeOfError ="Violated Schema Constraints";
           return handleException(typeOfError);
	   }
	   
	
	}
	/* load 
	 * load a user based on request 
	 */
	def load (){
		def aCustomer = customerService.getCustomer(params);
		if (aCustomer == null){
			def typeOfError = "Cannot Acees This Customer"
			return handleException(typeOfError);
		}	
		render(view:"/customer/load", model: [aCustomer: aCustomer]);
	}
	
	/*
	 * editProfile
	 * Allow user to modify existing profile
	 * Only part of profile could be modified including: address
	 * 
	 */
	def edit(){
		def typeOfError = "Update failed";
		println params;
        try{
			customerService.editCustomerProfile(params);
		}
		// Be specific about exception type....
		catch (Exception e){
			return handleException(typeOfError);
		}
		finally {
			redirect(controller:"customer", action:"list");
			
		}
		
		
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
		render(view:"/customer/errinfo", model: map);
		return;
		
		
	}
	
	
}
