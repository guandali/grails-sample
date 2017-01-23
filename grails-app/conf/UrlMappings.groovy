class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{}
		

		//"/customers"(resources:'customer', excludes:['delete', 'update'])

       
       "/"(view:"/index")
	   "/signup"(view:"/customer/registerform")
	   "/customers"(view:"/customer/listcustomers",controller:"customer",action:"list", method:"GET")
	   "/customers"(controller:"customer",action:"create", method:"POST")
	   "/customers/search/${searchString}"(controller:"customer",action:"findCustomer", method:"GET")
	   
	   
	   "/customers/${id}"(controller:"customer",action:"getUser", method:"GET")
	   "/customers/${id}"(controller:"customer",action:"editProfile", method:"POST") //Update Customer 
	   
	   //"/customers/search"

		"/search"(view:"/customer/search")
        "500"(view:'/error')
	}
}
