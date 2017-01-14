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
        "500"(view:'/error')
	}
}
