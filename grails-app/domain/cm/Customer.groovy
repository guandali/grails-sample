package cm


class Customer {
	Integer customer_id;
	String customer_first_name;
	String customer_last_name;
	String company_name;
	String adddress; 
	String email_address;
	Date sign_up_date;
	

    static constraints = {
		customer_id                        blank: false, unique: true
		customer_first_name                blank: false
		customer_last_name                 blank: false
		company_name                       nullable:true
	    adddress                           blank: false
		email_address                      blank:false, email:true
		sign_up_date                       blank:false
		
		
		
		
		
    }
}
