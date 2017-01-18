package cm

import org.codehaus.groovy.grails.web.json.JSONObject

class ErrorController {
	
	/**
	 * Customized error message
	 * @param typeOfError
	 * @return
	 */
	def handleException(typeOfError){
		def failureSummary = new JSONObject();
		failureSummary.put("type", typeOfError);
		def map = [failure:failureSummary]
		render(view:"/customer/errinfo", model: map);
		return;
		
		
	}

   
	
	
}
