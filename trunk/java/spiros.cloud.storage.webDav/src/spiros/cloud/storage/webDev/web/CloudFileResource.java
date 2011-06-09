package spiros.cloud.storage.webDev.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import spiros.cloud.storage.SimpleVRCatalogue;
import spiros.cloud.storage.resources.IResourceEntry;

import com.bradmcevoy.common.ContentTypeUtils;
import com.bradmcevoy.http.Auth;
import com.bradmcevoy.http.CollectionResource;
import com.bradmcevoy.http.FileItem;
import com.bradmcevoy.http.PropFindableResource;
import com.bradmcevoy.http.Range;
import com.bradmcevoy.http.Request;
import com.bradmcevoy.http.Request.Method;
import com.bradmcevoy.http.exceptions.BadRequestException;
import com.bradmcevoy.http.exceptions.ConflictException;
import com.bradmcevoy.http.exceptions.NotAuthorizedException;

public class CloudFileResource extends CloudResource implements
		com.bradmcevoy.http.FileResource {

	public CloudFileResource(SimpleVRCatalogue catalogue, IResourceEntry entry) {
		super(catalogue, entry);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void copyTo(CollectionResource collectionResource, String name) {
		throw new RuntimeException(
				"Not Implemented yet. Args: CollectionResource: "
						+ collectionResource + ", name: " + name);
	}

	@Override
	public void delete() {
		throw new RuntimeException("Not Implemented yet");
	}

	@Override
	public Long getContentLength() {
		return  getNodeEntry().getMetadata().getLength();
	}

	@Override
	public String getContentType(String accepts) {
		String mime = getNodeEntry().getMetadata().getMimeType();
		String type = mime;
		if (accepts != null && !accepts.equals("")) {
			type = ContentTypeUtils.findAcceptableContentType(mime, accepts);
		}
		return type;
	}

	private void debug(String msg) {
		System.err.println(this.getClass().getSimpleName() + ": " + msg);
	}

	@Override
	public Long getMaxAgeSeconds(Auth auth) {
		throw new RuntimeException("Not Implemented yet: Args: auth: " + auth);
	}

	@Override
	public void sendContent(OutputStream out, Range range,
			Map<String, String> params, String contentType) throws IOException,
			NotAuthorizedException, BadRequestException {
		throw new RuntimeException("Not Implemented yet");
	}

	@Override
	public void moveTo(CollectionResource rDest, String name)
			throws ConflictException {
		// catalogue.renameEntry();
		throw new RuntimeException("Not Implemented yet. Args, DestName: "
				+ rDest.getName() + " fileName?:" + name);
	}

	@Override
	public String processForm(Map<String, String> arg0,
			Map<String, FileItem> arg1) throws BadRequestException,
			NotAuthorizedException {
		throw new RuntimeException("Not Implemented yet");
	}

}
