package spiros.cloud.storage.webDev.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import spiros.cloud.storage.SimpleVRCatalogue;
import spiros.cloud.storage.resources.IResourceEntry;
import spiros.cloud.storage.resources.ResourceEntry;
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

public class CloudDirResource extends CloudResource implements
		com.bradmcevoy.http.FolderResource {	

	public CloudDirResource(SimpleVRCatalogue catalogue, IResourceEntry entry) {
		super(catalogue, entry);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CollectionResource createCollection(String newName)
			throws NotAuthorizedException, ConflictException {
		throw new RuntimeException("Not Implemented yet");
	}

	@Override
	public Resource child(String name) {
		return new CloudResource(getCatalogue(),  ((ResourceFolderEntry) getNodeEntry()).getChildByLRN(name));
	}

	@Override
	public List<? extends Resource> getChildren() {
		List<ResourceEntry> children =  ((ResourceFolderEntry) getNodeEntry()).getChildren();
		List<Resource> list = new ArrayList<Resource>();
		for (IResourceEntry r : children) {
			list.add(new CloudResource(getCatalogue(), r));
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
		if ( getNodeEntry().getMetadata() == null) {
			return null;
		}
		return  getNodeEntry().getMetadata().getLength();
	}

	@Override
	public String getContentType(String accepts) {
		return "";
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

}
