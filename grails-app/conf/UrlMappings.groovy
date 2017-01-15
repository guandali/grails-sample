class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
		"/customers"(view:"/customer/listcustomers",controller:"customer",action:"listcustomers")
		"/register"(view:"/customer/displayform",controller:"customer", action:"displayform")
		"/search"(view:"/customer/search")
		//"/findbyid"( controller:"customer", action:"findByID")
		
		//"/customer/findByID"(view:"/index", controller:"customer", action:"search")
        "500"(view:'/error')
	}
}
