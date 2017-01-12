package cm
import grails.rest.*
import grails.converter.*


class CustomerController extends RestfulController {
    static reponseFormats = ['json', 'xml'];
	CustomerController(){
		super(Customer);
	}
   
    
}
