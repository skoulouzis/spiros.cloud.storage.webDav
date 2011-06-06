package spiros.cloud.storage.webDev.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import nl.uva.vlet.vrms.ResourceFolder;

import spiros.cloud.storage.SimpleVRCatalogue;
import spiros.cloud.storage.resources.IResourceEntry;
import spiros.cloud.storage.resources.ResourceFolderEntry;

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
	private ResourceFolderEntry entry;

	public CloudDirResource(SimpleVRCatalogue catalogue, IResourceEntry resourceEntry) {
		this.catalogue = catalogue;
		this.entry = (ResourceFolderEntry) resourceEntry;
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
		return new Date(entry.getMetadata().getModifiedDate());
	}

	@Override
	public String getName() {
		return entry.getLRN();
	}

	@Override
	public String getRealm() {
		return "realm";
	}

	@Override
	public String getUniqueId() {
		return entry.getUID();
	}

	@Override
	public CollectionResource createCollection(String newName)
			throws NotAuthorizedException, ConflictException {
		throw new RuntimeException("Not Implemented yet");
	}

	@Override
	public Resource child(String name) {
		return new CloudResource(catalogue, entry.getChild(name));
	}

	@Override
	public List<? extends Resource> getChildren() {
		List<IResourceEntry> children = entry.getChildren();
		List<Resource> list = new ArrayList<Resource>();
		for(IResourceEntry r : children){
			list.add(new CloudResource(catalogue, r));
		}
		return list;
	}

	@Override
	public Resource createNew(String arg0, InputStream arg1, Long arg2,
			String arg3) throws IOException, ConflictException {
		throw new RuntimeException("Not Implemented yet");
	}

	@Override
	public void copyTo(CollectionResource arg0, String arg1) {
		throw new RuntimeException("Not Implemented yet");
	}

	@Override
	public void delete() {
		throw new RuntimeException("Not Implemented yet");
	}

	@Override
	public Long getContentLength() {
		return entry.getMetadata().getLength();
	}

	@Override
	public String getContentType(String accepts) {
		throw new RuntimeException("Not Implemented yet. Args: accepts: "+accepts);
	}

	@Override
	public Long getMaxAgeSeconds(Auth arg0) {
		throw new RuntimeException("Not Implemented yet");
	}

	@Override
	public void sendContent(OutputStream arg0, Range arg1,
			Map<String, String> arg2, String arg3) throws IOException,
			NotAuthorizedException, BadRequestException {
		throw new RuntimeException("Not Implemented yet");
	}

	@Override
	public void moveTo(CollectionResource arg0, String arg1)
			throws ConflictException {
		throw new RuntimeException("Not Implemented yet");
	}

	@Override
	public Date getCreateDate() {
		return new Date(entry.getMetadata().getCreateDate());
	}

}
