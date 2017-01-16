package cm
import java.util.UUID
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
class CustomerController {
	
    Integer uniqueID = 3000;
	
	def getUniqueID(){
		return UUID.randomUUID().toString();
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
		Customer tem_customer = new Customer();
		[tem_customer: tem_customer];
		
		
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
	def createUser(){
		println "createuser";
		render "OK";
		println params;
		println getUniqueID();
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
		   return list();
		   // Add return statement, otherwise the control flow will keep going
	   }
	   else {
		   println "violated schema constraints"
		   aCustomer.errors.allErrors.each {
			   println it
		   }
           return render('/');

	   }
	   return;
	
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
