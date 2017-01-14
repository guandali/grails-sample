package cm
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
class CustomerController {
	
    Integer uniqueID = 2000;
	
	def getUniqueID(){
		return ++ uniqueID;
	}
    def index() {
		println "@CustomerController index()";
		render "Hello World";
    }
	def listcustomers(){
		println"@CustomerController.listcustomers()";
		//Customer sample_customer = createSampleCustomers();
		//println sample_customer.customer_first_name;
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
	//Methods for testing =
	def createSampleCustomers(){
		println"@CustomerController.createSampleCustomers()";
		Customer c0 = new Customer (		
		customer_id:                        1,
		customer_first_name:                'Larry',
		customer_last_name:                 'Li',
	    adddress:                           '#930 Cambie',
		email_address:                      'coliguanda@gmail.com',
		sign_up_date:                       new Date()
		);
	 println c0.sign_up_date;
	 c0.save();
	 return c0;
	
	}
	// Create a new user and save it
	def createauser(){
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
	   }
	   else {
		   println "violated schema constraints"
	   }
	   return;
	   
	   
		
	
		
	}
	
	
}
