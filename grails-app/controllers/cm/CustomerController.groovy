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
		println"@CustomerController.listcustomers()";
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
		def failureSummary = new JSONObject();
		println "createuser";
		println params;
		def aCustomer = new Customer(
			customer_id:                        getUniqueID(),
			customer_first_name:                params.customer_first_name,
			customer_last_name:                 params.customer_last_name,
			adddress:                           params.company_name,
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
		   def typeOfError ="violated schema constraints";
		   failureSummary.put("type", typeOfError)
		   println "violated schema constraints"
		   aCustomer.errors.allErrors.each {
			   println it
		   }
		   def map = [failure:failureSummary]
		   render(view:"/customer/errinfo", model: map);
		   return;

	   }
	   
	
	}
	/* load 
	 * load a user based on request 
	 */
	def load (){
		println "-------------------load------------------------"
		println params;
		
		
	}
	
	/*
	 * editProfile
	 * Allow user to modify existing profile
	 * Only part of profile could be modified including: address, email_address
	 * 
	 */
	def editProfile(){
		println params;
		println "editProfile";
		
	}
	
	
}
