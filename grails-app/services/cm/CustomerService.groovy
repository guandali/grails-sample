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
	
	def getCustomerByID(params){
		return Customer.findByCustomer_id(params.q);
		println "getCustomerByID";	
	}
	
	def createCustomer(params){
	 return new Customer(
			customer_id:                        getUniqueID(),
			customer_first_name:                params.customer_first_name,
			customer_last_name:                 params.customer_last_name,
			company_name:                       params.company_name,
			address:                            params.address,
			email_address:                      params.email_address,
			sign_up_date:                       new Date()
		);
		
	}
	
	/* Function editCustomerProfile
	 * Modify a existing customer's profile (company_name, address ONLY)
	 * 
	 */
	def editCustomerProfile(params) {
		println "editCustomerProfile";
		def target = Customer.get(params.id);
		println "---------DB---"+ target.company_name;
		if(!target.address.equals(params.address) || params.address != null){
			target.address = params.address;
		}
		if(!target.company_name.equals(params.company_name) || params != null){
			println "company name changed"
			target.company_name = params.company_name;
			println target.company_name;
			
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
	
	
}
