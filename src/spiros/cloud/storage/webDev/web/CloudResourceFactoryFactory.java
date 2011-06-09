package spiros.cloud.storage.webDev.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import nl.uva.vlet.exception.VlException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bradmcevoy.http.AuthenticationService;
import com.bradmcevoy.http.ResourceFactory;
import com.bradmcevoy.http.ResourceFactoryFactory;
import com.bradmcevoy.http.webdav.DefaultWebDavResponseHandler;
import com.bradmcevoy.http.webdav.WebDavResponseHandler;

public class CloudResourceFactoryFactory implements ResourceFactoryFactory {

	private Logger log = LoggerFactory
			.getLogger(CloudResourceFactoryFactory.class);

	private static AuthenticationService authenticationService;
	private static CloudResourceFactory resourceFactory;

	@Override
	public ResourceFactory createResourceFactory() {
		return resourceFactory;
	}

	@Override
	public WebDavResponseHandler createResponseHandler() {
		return new DefaultWebDavResponseHandler(authenticationService);
	}

	@Override
	public void init() {
		log.debug("init");
		if (authenticationService == null) {
			authenticationService = new AuthenticationService();
			try {
				resourceFactory = new CloudResourceFactory();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// checkInitialData();
			catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (VlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// private void checkInitialData() {
	// List existingDepartments = new ArrayList<VFSNode>();
	// if (existingDepartments == null || existingDepartments.size() == 0) {
	// log.debug("creating initial data");
	// VFSNode d = VFSNode.create("Information Technology");
	// existingDepartments.add(d);
	// d = VFSNode.create("Finance");
	// existingDepartments.add(d);
	// d = VFSNode.create("Human Resources");
	// existingDepartments.add(d);
	// } else {
	// log.debug("database already has department data: "
	// + existingDepartments.size());
	// }
	// }

}
