package cm
import org.grails.datastore.gorm.*
import grails.rest.*

/*
 * Expose Customer class as a REST resource 
 */
@Resource(uri='/customers')
class Customer {
	String customer_first_name;
	String customer_last_name;
	String school_name;
	String address; 
	String email_address;
	Date sign_up_date;
	

    static constraints = {
		customer_first_name                blank: false
		customer_last_name                 blank: false
		school_name                        nullable:true
	    address                            blank: false
		email_address                      blank:false, email:true, unique: true
		sign_up_date                       blank:false

		
    }
}
