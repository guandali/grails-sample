package cm

import grails.transaction.Transactional

@Transactional
class CustomerService {
	
	def getCustomer(id){
		return Customer.read(id);
		
	}

    def getCustomersList() {
		return Customer.list();
    }
	

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
		if(!params.address.equals(target.address) && params.address != null && params.address != ''){
			target.address = params.address;
		}
		if(!params.school_name.equals(target.school_name)&& params.school_name != null ){
			println "school name changed"
			target.school_name = params.school_name;
			println target.school_name;
			
		}
		if(!params.customer_first_name.equals(target.customer_first_name) && params.customer_first_name != null && params.customer_first_name != '' ){
			println "customer_first_name changed"
			target.customer_first_name = params.customer_first_name;
			println target.customer_last_name;
			
		}
		if(!params.customer_last_name.equals(target.customer_last_name) && params.customer_last_name != null && params.customer_last_name != '' ){
			println "customer_last_name changed"
			target.customer_last_name = params.customer_last_name;
			println target.customer_last_name;
			
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
