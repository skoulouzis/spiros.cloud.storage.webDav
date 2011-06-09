package spiros.cloud.storage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.sun.swing.internal.plaf.metal.resources.metal;

import nl.uva.vlet.data.VAttribute;
import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.exception.VlURISyntaxException;
import nl.uva.vlet.vrs.VComposite;
import nl.uva.vlet.vrs.VNode;
import nl.uva.vlet.vrs.VRSClient;
import spiros.cloud.storage.resources.AccessLocation;
import spiros.cloud.storage.resources.Metadata;
import spiros.cloud.storage.resources.ResourceEntry;
import spiros.cloud.storage.resources.ResourceFileEntry;
import spiros.cloud.storage.resources.ResourceFolderEntry;

public class Util {

	private static SimpleVRCatalogue cat;
	private static VRSClient c;

	public void setVRSClient(VRSClient client) {
		c = client;
	}

	public static void initTestCatalouge() throws Exception {
		if (c == null) {
			c = new VRSClient();
		}

		for (int i = 0; i < Config.CLOUD_LOCATIONS_URI.length; i++) {
			VNode vnode = c.openLocation(Config.CLOUD_LOCATIONS_URI[i]);

			for (VNode n : ((VComposite) vnode).getNodes()) {
				addAllNodes2DB(Config.CLOUD_LOCATIONS_URI[i], n);
			}
		}
	}

	private static void addAllNodes2DB(String cloudLocationsUri, VNode vnode)
			throws Exception {
		String lrn = getLogicalName(cloudLocationsUri, vnode.getVRL()
				.toURIString());
		
		
		ResourceEntry entry = null;
		if (!vnode.getName().startsWith(".")) {
			if (vnode.isComposite()) {
				debug("Composite "+lrn);
				entry = new ResourceFolderEntry(lrn);
				VNode[] nodes = ((VComposite)vnode).getNodes();
				
				List<ResourceEntry> children = nodes2Entries(cloudLocationsUri, nodes);
				
				((ResourceFolderEntry)entry).addChildren(children);
				
				for(VNode n : nodes){
					String childLRN = getLogicalName(cloudLocationsUri, n.getVRL()
							.toURIString());
					debug("\t Children: "+childLRN);
					addAllNodes2DB(cloudLocationsUri, n);
				}
			} else {
				debug("Files "+lrn);
				entry = new ResourceFileEntry(lrn);
				List<AccessLocation> access = getAccessLocation(vnode);
				((ResourceFileEntry)entry).addAccessLocations(access);
			}
			Metadata meta = getNodeMetadata(vnode);
			entry.setMetadata(meta);
		}
		
		if(cat == null){
			cat = new SimpleVRCatalogue();
		}
		if(entry != null){
			cat.registerResourceEntry(entry);
		}
	}

	// private static void addAllNodes2DB(String cloudLocationsUri, VNode vnode)
	// throws VlException, URISyntaxException, IOException {
	// String lrn = getLogicalName(cloudLocationsUri, vnode.getVRL()
	// .toURIString());
	//
	// ResourceEntry entry;
	// if (!vnode.getName().startsWith(".")) {
	// if (vnode.isComposite()) {
	// entry = new ResourceFolderEntry(lrn);
	//
	// VNode[] nodes = ((VComposite) vnode).getNodes();
	//
	// List<ResourceEntry> ch = nodes2Entries(cloudLocationsUri, nodes);
	// ((ResourceFolderEntry) entry).addChildren(ch);
	//
	// debug("Addig children to "+entry.getLRN());
	//
	// for (VNode n : nodes) {
	// addAllNodes2DB(cloudLocationsUri, n);
	// }
	// } else {
	// entry = new ResourceFileEntry(lrn);
	// List<AccessLocation> accessLocations = getAccessLocation(vnode);
	// ((ResourceFileEntry) entry).addAccessLocations(accessLocations);
	// }
	//
	// if (cat == null) {
	// cat = new SimpleVRCatalogue();
	// }
	// debug("Register: "+entry.getLRN());
	//
	// Metadata metadata = getNodeMetadata(vnode);
	// entry.setMetadata(metadata);
	// cat.registerResourceEntry(entry);
	// }
	//
	// }

	private static Metadata getNodeMetadata(VNode vnode) throws VlException {
		Metadata m = new Metadata();
		String[] aNames = vnode.getAttributeNames();

		VAttribute modificationTime = vnode.getAttribute("modificationTime");
		m.setModifiedDate(modificationTime.getLongValue());

		VAttribute createDate = null;
		VAttribute mimeType = null;
		for (String name : aNames) {
			// debug("names: " + name);
			if (name.toLowerCase().contains("create")
					&& name.toLowerCase().contains("time")) {
				createDate = vnode.getAttribute(name);
			}
			if (name.toLowerCase().contains("mimetype")) {
				mimeType = vnode.getAttribute(name);
			}
		}
		if (createDate == null) {
			if (modificationTime.getLongValue() <= System.currentTimeMillis()) {
				createDate = modificationTime;
			} else {
				createDate = new VAttribute(System.currentTimeMillis());
			}
		}

		m.setCreateDate(createDate.getLongValue());

		String strMime = "";
		if (mimeType != null) {
			strMime = mimeType.getStringValue();
		}
		m.setMimeType(strMime);

		m.setLength(vnode.getAttribute("length").getLongValue());

		return m;
	}

	private static List<AccessLocation> getAccessLocation(VNode vnode)
			throws VlURISyntaxException {
		List<AccessLocation> aLoc = new ArrayList<AccessLocation>();
		aLoc.add(new AccessLocation(
				new String[] { vnode.getVRL().toURIString() }));

		return aLoc;
	}

	private static List<ResourceEntry> nodes2Entries(String cloudLocationsUri,
			VNode[] nodes) throws VlURISyntaxException, IOException {
		List<ResourceEntry> entries = new ArrayList<ResourceEntry>();
		for (VNode n : nodes) {
			String lrn = getLogicalName(cloudLocationsUri, n.getVRL()
					.toURIString());
			if (n.isComposite()) {
				entries.add(new ResourceFolderEntry(lrn));
			} else {
				ResourceFileEntry ch = new ResourceFileEntry(lrn);
				List<AccessLocation> access = getAccessLocation(n);
				ch.addAccessLocations(access);
				entries.add(ch);
			}
		}
		return entries;
	}

	private static void debug(String msg) {
		System.err.println(Util.class.getSimpleName() + ": " + msg);
	}

	public static String getLogicalName(String basePath, String absoluteNodePath) {
		if (absoluteNodePath.startsWith(basePath)) {
			return absoluteNodePath.substring(basePath.length());
		} else {
			String[] parts = basePath.split("/");
			String last = parts[parts.length - 1];
			int index = absoluteNodePath.lastIndexOf(last);
			return absoluteNodePath.substring(index + last.length());
		}
	}
}
