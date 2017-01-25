import cm.Customer

class BootStrap {

    def init = { servletContext ->
		new Customer(
			customer_first_name:"Larry", 
			customer_last_name:"Li",
			school_name:"ubc",
			address:"cambie st",
			email_address:"lli@sap.com"
			
			).save()
		
		
    }
    def destroy = {
    }
}
