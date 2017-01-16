package cm
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONObject
class CustomerController {
	Integer uid = 10001;

	
	def getUniqueID(){
		return uid++;
	}
    def index() {
       
    }
	def list(){
		// List all instances 
		def list = Customer.list();
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
		
		def result = Customer.findByCustomer_id(params.q);
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
		println "createuser";
		println params;
		def aCustomer = new Customer(
			customer_id:                        getUniqueID(),
			customer_first_name:                params.customer_first_name,
			customer_last_name:                 params.customer_last_name,
			company_name:                       params.company_name,
			address:                            params.address,
			email_address:                      params.email_address,
			sign_up_date:                       new Date()
		);
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
		def aCustomer = Customer.get(params.id);
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
			def target = Customer.findByCustomer_id(params.id);
			if(!target.address.equals(params.address)){
				target.address = params.address;
			}
			if(!target.company_name.equals(params.company_name)){
				println "company name changed"
				target.company_name = params.company_name;
				println target.company_name;
				
			}
             target.save(flush: true, failOnError: true)
		
		}
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
