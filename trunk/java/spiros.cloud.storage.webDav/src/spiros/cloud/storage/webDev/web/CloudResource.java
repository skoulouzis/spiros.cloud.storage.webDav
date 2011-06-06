package spiros.cloud.storage.webDev.web;

import java.util.Date;

import spiros.cloud.storage.SimpleVRCatalogue;
import spiros.cloud.storage.resources.IResourceEntry;

import com.bradmcevoy.http.Auth;
import com.bradmcevoy.http.PropFindableResource;
import com.bradmcevoy.http.Request;
import com.bradmcevoy.http.Request.Method;
import com.bradmcevoy.http.Resource;

public class CloudResource implements PropFindableResource, Resource {

	private IResourceEntry nodeEntry;
	private SimpleVRCatalogue catalogue;

	public CloudResource(SimpleVRCatalogue catalogue, IResourceEntry entry) {
		this.nodeEntry = entry;
		this.catalogue = catalogue;
	}

	@Override
	public Object authenticate(String user, String pwd) {
		return user;
	}

	@Override
	public boolean authorise(Request arg0, Method arg1, Auth arg2) {
		return true;
	}

	@Override
	public String checkRedirect(Request arg0) {
		return null;
	}

	@Override
	public Date getModifiedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return nodeEntry.getLRN();
	}

	@Override
	public String getRealm() {
		return "relm";
	}

	@Override
	public String getUniqueId() {
		return "" + nodeEntry.hashCode();
	}

	@Override
	public Date getCreateDate() {
		return new Date(nodeEntry.getMetadata().getCreateDate());
	}

	private void debug(String msg) {
		System.err.println(this.getClass().getSimpleName() + ": " + msg);
	}

}
