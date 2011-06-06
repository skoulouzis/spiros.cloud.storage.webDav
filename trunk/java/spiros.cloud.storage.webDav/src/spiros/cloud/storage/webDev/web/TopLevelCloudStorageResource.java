package spiros.cloud.storage.webDev.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.bradmcevoy.http.Auth;
import com.bradmcevoy.http.CollectionResource;
import com.bradmcevoy.http.PropFindableResource;
import com.bradmcevoy.http.Request;
import com.bradmcevoy.http.Request.Method;
import com.bradmcevoy.http.Resource;

public class TopLevelCloudStorageResource implements PropFindableResource,
		CollectionResource {

	private final CloudResourceFactory resourceFactory;

	public TopLevelCloudStorageResource(
			CloudResourceFactory resourceFactory) {
		this.resourceFactory = resourceFactory;
	}

	@Override
	public Date getCreateDate() {
		// Unknown
		return null;
	}

	@Override
	public Object authenticate(String user, String pwd) {
		// always allow
		return user;
	}

	@Override
	public boolean authorise(Request arg0, Method arg1, Auth arg2) {
		// Always allow
		return true;
	}

	@Override
	public String checkRedirect(Request arg0) {
		// No redirects
		return null;
	}

	@Override
	public Date getModifiedDate() {
		// Unknown
		return null;
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public String getRealm() {
		return CloudResourceFactory.REALM;
	}

	@Override
	public String getUniqueId() {
		return null;
	}

	@Override
	public Resource child(String name) {
		return null;
	}

	@Override
	public List<? extends Resource> getChildren() {
		try {
			List<? extends Resource> nodes = resourceFactory.listRootResources();
			return nodes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
