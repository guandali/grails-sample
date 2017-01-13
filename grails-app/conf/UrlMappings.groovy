class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
		"/viewcustomers"(redirect: '/customers/list')
		"/customer"(controller:"customer",action:"list")
        "500"(view:'/error')
	}
}
