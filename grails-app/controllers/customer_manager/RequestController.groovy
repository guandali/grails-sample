package customer_manager

import grails.rest.RestfulController;
//import org.ajoberstar.grgit.Grgit
import org.eclipse.jgit.api.LsRemoteCommand

class RequestController extends RestfulController {

    def index() {
		println "index"
	}
	
	def getGitFiles(){
		println "-----------debug-----------------";
		final LsRemoteCommand lsCmd = new LsRemoteCommand(null);

		
	}
	
}
