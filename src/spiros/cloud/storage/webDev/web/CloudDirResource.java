package spiros.cloud.storage.webDev.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import spiros.cloud.storage.SimpleVRCatalogue;
import spiros.cloud.storage.resources.IResourceEntry;

import com.bradmcevoy.http.Auth;
import com.bradmcevoy.http.CollectionResource;
import com.bradmcevoy.http.PropFindableResource;
import com.bradmcevoy.http.Range;
import com.bradmcevoy.http.Request;
import com.bradmcevoy.http.Request.Method;
import com.bradmcevoy.http.Resource;
import com.bradmcevoy.http.exceptions.BadRequestException;
import com.bradmcevoy.http.exceptions.ConflictException;
import com.bradmcevoy.http.exceptions.NotAuthorizedException;

public class CloudDirResource implements PropFindableResource,
		com.bradmcevoy.http.FolderResource {

	private SimpleVRCatalogue catalogue;
	private IResourceEntry entry;

	public CloudDirResource(SimpleVRCatalogue catalogue, IResourceEntry resourceEntry) {
		this.catalogue = catalogue;
		this.entry = resourceEntry;
	}

	@Override
	public Object authenticate(String user, String arg1) {
		// TODO Auto-generated method stub
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
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRealm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUniqueId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CollectionResource createCollection(String arg0)
			throws NotAuthorizedException, ConflictException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource child(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends Resource> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource createNew(String arg0, InputStream arg1, Long arg2,
			String arg3) throws IOException, ConflictException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void copyTo(CollectionResource arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public Long getContentLength() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContentType(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getMaxAgeSeconds(Auth arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendContent(OutputStream arg0, Range arg1,
			Map<String, String> arg2, String arg3) throws IOException,
			NotAuthorizedException, BadRequestException {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveTo(CollectionResource arg0, String arg1)
			throws ConflictException {
		// TODO Auto-generated method stub

	}

	@Override
	public Date getCreateDate() {
		// TODO Auto-generated method stub
		return null;
	}

}
