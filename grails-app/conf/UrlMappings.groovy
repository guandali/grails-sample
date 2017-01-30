//URLs publishes all resources 
class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{}
		

		//"/customers"(resources:'customer', excludes:['delete', 'update'])

       
       "/"(view:"index")
	   "/api/customers"(controller:"customer",action:"list", method:"GET")
	   "/api/customers"(controller:"customer",action:"create", method:"POST")
	   "/api/customers/search/${searchString}"(controller:"customer",action:"findCustomer", method:"GET")
	   
	   
	   "/api/customers/${id}"(controller:"customer",action:"getUser", method:"GET")
	   "/api/customers/${id}"(controller:"customer",action:"editProfile", method:"POST") //Update Customer 
	   
	   "/api/customers/isuniquemail/${unchecked_mail}"(controller:"customer",action:"validateEmail", method:"GET")
	   
	   
	   
	   
	   "/api/utils/checkuniqueemail/${email}"(controller:"customer", action:"validateEmail", method:"GET");

		"/api/search"(view:"/customer/search")
        "500"(view:'/error')
	}
}
