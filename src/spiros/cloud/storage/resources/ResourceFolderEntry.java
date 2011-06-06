package spiros.cloud.storage.resources;

import java.util.ArrayList;
import java.util.List;

public class ResourceFolderEntry extends ResourceEntry {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2765285570381475790L;
	private List<IResourceEntry> children;
	private String UID;

	public ResourceFolderEntry(String logicalResourceName) {
		super(logicalResourceName);
	}

	public List<IResourceEntry> getChildren() {
		return this.children;
	}

	public void addChildren(List<ResourceEntry> children) {
		if (this.children == null) {
			this.children = new ArrayList<IResourceEntry>();
		}
		this.children.addAll(children);
	}

	public void removeChildren(List<IResourceEntry> children) {
		if (this.children != null && !this.children.isEmpty()) {
			this.children.removeAll(children);
		}
	}

	public void removeAllChildren() {
		if (this.children != null && !this.children.isEmpty()) {
			this.children.clear();
		}
	}

	public void addChild(IResourceEntry child) {
		if(this.children == null){
			this.children = new ArrayList<IResourceEntry>();
		}
		this.children.add(child);
	}

	@Override
	public String getUID() {
		return this.UID;
	}

	@Override
	public void setUID(String UID) {
		this.UID = UID;
	}

	public IResourceEntry getChild(String name) {
		for(IResourceEntry r : children){
			if(r.getLRN().equals(name)){
				return r;
			}
		}
		return null;
		
	}

}
