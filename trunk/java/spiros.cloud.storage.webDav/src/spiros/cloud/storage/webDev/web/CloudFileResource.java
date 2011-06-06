package spiros.cloud.storage.webDev.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import spiros.cloud.storage.SimpleVRCatalogue;
import spiros.cloud.storage.resources.IResourceEntry;

import com.bradmcevoy.http.Auth;
import com.bradmcevoy.http.CollectionResource;
import com.bradmcevoy.http.FileItem;
import com.bradmcevoy.http.PropFindableResource;
import com.bradmcevoy.http.Range;
import com.bradmcevoy.http.Request;
import com.bradmcevoy.http.Resource;
import com.bradmcevoy.http.Request.Method;
import com.bradmcevoy.http.exceptions.BadRequestException;
import com.bradmcevoy.http.exceptions.ConflictException;
import com.bradmcevoy.http.exceptions.NotAuthorizedException;

public class CloudFileResource implements PropFindableResource, com.bradmcevoy.http.FileResource {
	
	private IResourceEntry resourceEntry;
	private SimpleVRCatalogue catalogue;

	public CloudFileResource(SimpleVRCatalogue catalogue, IResourceEntry resourceEntry) {
		this.resourceEntry = resourceEntry;
		this.catalogue = catalogue;
	}

	@Override
	public Object authenticate(String user, String arg1) {
		return user;
	}

	@Override
	public boolean authorise(Request arg0, Method arg1, Auth arg2) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String checkRedirect(Request arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getModifiedDate() {
		return new Date(resourceEntry.getMetadata().getModifiedDate());
	}

	@Override
	public String getName() {
		return resourceEntry.getLRN();
	}

	@Override
	public String getRealm() {
		return "Realm";
	}

	@Override
	public String getUniqueId() {
		return resourceEntry.getUID();
	}

	@Override
	public void copyTo(CollectionResource collectionResource, String name) {
		throw new RuntimeException("Not Implemented yet. Args: CollectionResource: "+collectionResource+", name: "+name);
	}

	@Override
	public void delete() {
		throw new RuntimeException("Not Implemented yet");
	}

	@Override
	public Long getContentLength() {
		return resourceEntry.getMetadata().getLength();
	}

	@Override
	public String getContentType(String accepts) {
		throw new RuntimeException("Not Implemented yet: Args: accepts: "+accepts);
	}

	@Override
	public Long getMaxAgeSeconds(Auth auth) {
		throw new RuntimeException("Not Implemented yet: Args: auth: "+auth);
	}

	@Override
	public void sendContent(OutputStream out, Range range,
			Map<String, String> params, String contentType) throws IOException,
			NotAuthorizedException, BadRequestException {
		throw new RuntimeException("Not Implemented yet");
	}

	@Override
	public void moveTo(CollectionResource arg0, String arg1)
			throws ConflictException {
		throw new RuntimeException("Not Implemented yet");
	}

	@Override
	public String processForm(Map<String, String> arg0,
			Map<String, FileItem> arg1) throws BadRequestException,
			NotAuthorizedException {
		throw new RuntimeException("Not Implemented yet");
	}

	@Override
	public Date getCreateDate() {
		return new Date(this.resourceEntry.getMetadata().getCreateDate());
	}
	
}
