class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{}
        "/customer"(resources: 'customer')
       
       "/"(view:"/index")
	   "/customer/list"(view:"/customer/listcustomers",controller:"customer",action:"list")

		"/customer/register"(view:"/customer/displayform",controller:"customer", action:"displayform")
		"/search"(view:"/customer/search")
//		"/customer/editProfile"(view:"/customer/userinfo", controller:"customer", action:"editProfile")
		
		
		//"/customer/${customer.customer_id}/edit"(view:"/customer/userinfo")
		//"/findbyid"( controller:"customer", action:"findByID")
		//"/customers"(view:"/customer/listcustomers",controller:"customer",action:"list")
		
		//"/customer/findByID"(view:"/index", controller:"customer", action:"search")
        "500"(view:'/error')
	}
}
