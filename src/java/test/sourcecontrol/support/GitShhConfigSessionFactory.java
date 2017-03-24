package test.sourcecontrol.support;

import org.eclipse.jgit.errors.UnsupportedCredentialItem;
import org.eclipse.jgit.transport.CredentialItem;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.CredentialsProviderUserInfo;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig;
import org.eclipse.jgit.transport.URIish;

import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class GitShhConfigSessionFactory extends JschConfigSessionFactory  {
	
	public GitShhConfigSessionFactory() {
		super();
	}


	
	@Override
	protected void configure(OpenSshConfig.Host hc, Session session) {
		CredentialsProvider provider = new CredentialsProvider() {
			@Override
			public boolean isInteractive() {
				return false;
			}
	
			@Override
			public boolean supports(CredentialItem... items) {
				return true;
			}
	
			@Override
			public boolean get(URIish uri, CredentialItem... items) throws UnsupportedCredentialItem {
				for (CredentialItem item : items) {
					//Set passphrase here! 
					((CredentialItem.StringType) item).setValue("123456");
				}
				return true;
			}
		};
		UserInfo userInfo = new CredentialsProviderUserInfo(session, provider);
		session.setUserInfo(userInfo);
	}


}
