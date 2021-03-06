package spiros.cloud.storage.webDev.web;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import nl.uva.vlet.exception.VlException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spiros.cloud.storage.SimpleVRCatalogue;
import spiros.cloud.storage.Util;
import spiros.cloud.storage.resources.IResourceEntry;
import spiros.cloud.storage.resources.ResourceEntry;
import spiros.cloud.storage.resources.ResourceFileEntry;
import spiros.cloud.storage.resources.ResourceFolderEntry;

import com.bradmcevoy.common.Path;
import com.bradmcevoy.http.Resource;
import com.bradmcevoy.http.ResourceFactory;

public class CloudResourceFactory implements ResourceFactory {

	private Logger log = LoggerFactory.getLogger(CloudResourceFactory.class);

	public static final String REALM = "MyCompany";

	private SimpleVRCatalogue catalogue;

	public CloudResourceFactory() throws URISyntaxException, VlException, IOException {
		catalogue = new SimpleVRCatalogue();
//		Util.initTestCatalouge();
	}

	@Override
	public Resource getResource(String host, String p) {

		Path path1 = Path.path(p).getStripFirst();
		log.debug("getResource: to Path" + path1);

		if (path1.isRoot() || path1.equals("")) {
			return new TopLevelCloudStorageResource(this);
		} else {

			try {
				return getResource(path1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	private void debug(String msg) {
		System.err.println(this.getClass().getSimpleName() + ": " + msg);
	}

	private Resource getResource(Path path) throws IOException,
			ClassNotFoundException {
		
		ResourceEntry entry = (ResourceEntry) catalogue.getResourceEntryByLRN(path.toString());
		if(entry instanceof ResourceFolderEntry){
			List<ResourceEntry> children = ((ResourceFolderEntry)entry).getChildren();
			for(ResourceEntry e: children){
				log.debug("Children: "+e.getLRN());
			}
		}
		
		return new CloudResource(catalogue, entry);
	}

	public List<? extends Resource> listRootResources() throws IOException,
			ClassNotFoundException {
		ArrayList<ResourceEntry> topLevel = catalogue
				.getTopLevelResourceEntries();
		ArrayList<Resource> topResources = new ArrayList<Resource>();
		for (int i = 0; i < topLevel.size(); i++) {
			if (topLevel.get(i) instanceof ResourceFolderEntry) {
				topResources.add(new CloudDirResource(catalogue, topLevel
						.get(i)));
			} else if (topLevel.get(i) instanceof ResourceFileEntry) {
				topResources.add(new CloudFileResource(catalogue, topLevel
						.get(i)));
			} else {
				topResources.add(new CloudResource(catalogue, topLevel.get(i)));
			}
		}
		return topResources;
	}
}
