package cm

import grails.transaction.Transactional

@Transactional
class CustomerService {
	//May use other data type, upon request 
	Integer uid = 100000;
	
	def getUniqueID(){
		return uid++;
	}
	
	def getCustomer(params){
		return Customer.read(params.id);
		
	}

    def getCustomersList() {
		return Customer.list();
    }
	
//	def getCustomerByID(params){
//		return Customer.findByCustomer_id(params.q);
//		println "getCustomerByID";	
//	}
	
	def createCustomer(data){
	 return new Customer(
			customer_first_name:                data.customer_first_name,
			customer_last_name:                 data.customer_last_name,
			school_name:                        data.school_name,
			address:                            data.address,
			email_address:                      data.email_address,
			sign_up_date:                       new Date()
		);
		
	}
	
	/* Function editCustomerProfile
	 * Modify a existing customer's profile (school_name, address ONLY)
	 * 
	 */
	def editCustomerProfile(params) {
		println "editCustomerProfile";
		def target = Customer.get(params.id);
		println "---------DB---"+ target.school_name;
		if(!target.address.equals(params.address) || params.address != null){
			target.address = params.address;
		}
		if(!target.school_name.equals(params.school_name) || params != null){
			println "school name changed"
			target.school_name = params.school_name;
			println target.school_name;
			
		}
		 target.save(flush: true, failOnError: true);
		 return target;

	}
	
	/* Function getCustomerByName 
	 * Find a or more than customer by: 
	 * customer_first_name + customer_last_name
	 * 
	 * 
	 */
	def getCustomerByName(){}
	
	
	
	def findUserByEmail(email){
		if (Customer.findByEmail_address(email) == null){
			return 'true';	
		}
		return 'false';
		


	}
	
	
}
